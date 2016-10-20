/**
 * Created by YYJ on 2016/2/8.
 */
"use strict";
define(function (require, exports, module) {
    var tpl = require('./loading.html');
    /*var isdummy = function () {
     //return location.hash.indexOf('dummy') !== -1;
     return true;
     };*/
    var loadingshow = function () {
        var h = $("#loadingToast");
        if (h.size() > 0) {
            h.show();
        } else {
            var $h = $(tpl);
            $("body").append($h);
            $("#loadingToast").show();
        }
    };
    var loadinghide = function () {
        $('#loadingToast').hide();
    };
    exports.config = function (opt) {
    };
    exports.request = function (param, param1) {
        /*if (arguments.length > 1) {
         $.extend(param, param1);
         }
         if (param.url && typeof(param.url) == 'object') {
         param.isdummy = true;
         param.dummy = param.url;
         param.url = '/dummy/ok';
         }*/
        loadingshow();
        event && event.preventDefault();
        // 单体测试,是否使用假数据
        if (param.dummy) {
            param.success(param.dummy);
            loadinghide();
            return;
        }
        console.log(param.url + "-->>>:", param.data);
        return $.ajax({
            url: param.url,
            type: "post",
            //async: false,
            dataType: "json",
            data: param.data,
            error: function () {
                alert("您的网络异常，请检查网络后重试");
                loadinghide();
            },
            success: function (json) {
                if (param.isdummy && param.dummy)json = param.dummy;
                console.log(param.url + "<<<--:", json);
                if (json && json.code) {
                    param.handle && param.handle(json);
                    if (!param.handle) {
                        if (json.code < 0) {
                            if (json.code == -10) {
                                baofoo.callNative("jump2Login");
                            } else if (json.code == -30) {
                                $(".js_dialog_content").html(json.message);
                                $('#dialog2').show().on('click', '.js_dialog_confirm', function () {
                                    $('#dialog2').off('click').hide();
                                    baofoo.callNative("callNative", "auth");
                                });
                            } else {
                                $(".js_dialog_content").html(json.message);
                                $('#dialog2').show().on('click', '.js_dialog_confirm', function () {
                                    $('#dialog2').off('click').hide();
                                });
                            }
                        } else if (json.code == 1) {
                            if (json.obj == null)json.obj = {};
                            json.obj.retMsg = json.message;
                            param.success && param.success(json.obj || {});
                        } else {
                        }
                    }
                }
                loadinghide();
            }
        });
    };
});