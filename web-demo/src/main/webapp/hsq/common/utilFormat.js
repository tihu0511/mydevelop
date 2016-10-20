define(function (require, exports, module) {
    exports.formatPrice = function (num) {
        if (new Number(num || 0) == 0) {
            return "0.00";
        }
        var a = (num || 0).toString().split('.');
        if (a.length == 1) {
            return a[0].replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
        } else {
            return a[0].replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') + '.' + a[1];
        }
    };
    exports.formatPrice2dec = function (num) {
        if ((num || 0) == 0) {
            return "0.00";
        }
        var a = (num || 0).toString().split('.');
        if (a.length == 1) {
            return a[0].replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') + ".00";
        } else {
            return a[0].replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') + '.' + (a[1] + "00").substring(0, 2);
        }
    };
    exports.format4dec = function (num) {
        if ((num || 0) == 0) {
            return "0.0000";
        }
        var a = (num || 0).toString().split('.');
        if (a.length == 1) {
            return a[0].replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') + ".0000";
        } else {
            return a[0].replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') + '.' + (a[1] + "0000").substring(0, 4);
        }
    };
});