/**
 * baofoo v0.0.1
 * baofoo.callNative(functionName, args, callback, _arguments)
 * 例：
 * 关键字：baofoosdk
 * iOS请求：http://baofoosdk/" + functionName + "/" + callbackId + "/" + encodeURIComponent(args)
 * iOS回调：baofoo.iOSresultForCallback(callbackId, resultArray)
 * */
(function () {
    var root = this;
    //var previous = root.baofoo;
    var baofoo = baofoo || {};
    baofoo.browser = (function () {
        var u = navigator.userAgent;
        return {
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, // android终端或uc浏览器
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/) // ios终端
        };
    })();
    baofoo.iOSNativeBridge = {
        callbacksCount: 1,
        callbacks: {},
        tempArgs: {},
        resultForCallback: function resultForCallback(callbackId, resultArray) {
            try {
                var callback = baofoo.iOSNativeBridge.callbacks[callbackId];
                var tempArg = baofoo.iOSNativeBridge.tempArgs[callbackId];
                if (!callback) {
                    return;
                }
                var _args;
                if (tempArg) {
                    _args = [tempArg, resultArray];
                    callback.apply(null, _args);
                } else {
                    _args = resultArray;
                    callback(_args);
                }
//			var _args = [tempArg, resultArray];
//			callback.apply(null, _args);
            } catch (e) {
                console.error(e.message);
            }
        },
        call: function call(functionName, args, callback, _arguments) {
            var hasCallback = callback && typeof callback == "function";
            var callbackId = hasCallback ? baofoo.iOSNativeBridge.callbacksCount++ : 0;
            if (hasCallback) {
                baofoo.iOSNativeBridge.callbacks[callbackId] = callback;
                baofoo.iOSNativeBridge.tempArgs[callbackId] = _arguments;
            }
            var iframe = document.createElement("IFRAME");
            if (args) {
                iframe.setAttribute("src", "http://baofoosdk/" + functionName + "/" + callbackId + "/" + encodeURIComponent(args));
            } else {
                iframe.setAttribute("src", "http://baofoosdk/" + functionName + "/" + callbackId);
            }
            document.documentElement.appendChild(iframe);
            iframe.parentNode.removeChild(iframe);
            iframe = null;
        }
    };
    baofoo.androidNativeBridge = (function () {
        return {
            call: function (functionName, args, callback, _arguments) {
                var result = null;
                try {
                    !!args && (result = root.baofoosdk && root.baofoosdk[functionName](args));
                    !args && (result = root.baofoosdk && root.baofoosdk[functionName]());
                } catch (e) {
                    console.error(e.message);
                } finally {
                    _arguments && callback && callback(_arguments, result);
                    !_arguments && callback && callback(result);
                    if (!callback) {
                        return result;
                    }
                }
            }
        };
    })();
    baofoo.callNative = (function () {
        return function (functionName, args, callback, _arguments) {
            if (baofoo.browser.android) {
                console.log("Android=functionName:" + functionName + ",args:" + args);
                return baofoo.androidNativeBridge.call(functionName, args, callback, _arguments);
            } else if (baofoo.browser.ios) {
                console.log("iOS=functionName:" + functionName + ",args:" + args);
                __iOSNativeBridge.call(functionName, args, callback, _arguments);
                return null;
            } else {
                console.log("不支持该浏览渠道");
                return;
            }
        };
    })();
    //iOS用回调方法
    baofoo.iOSresultForCallback = baofoo.iOSNativeBridge.resultForCallback;
    root.baofoo = baofoo;
    // TODO 旧接口
    root.__iOSNativeBridge = baofoo.iOSNativeBridge;
    root.__NativeBridge = {call: baofoo.callNative};
}).call(this);