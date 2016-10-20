define(function (require, exports, module) {
    var template = require('template');
    var util = require('utilformat');
    template.helper('format4dec', function (price) {
        return util.format4dec(price);
    });
    template.helper('formatPrice', function (price) {
        return util.formatPrice(price);
    });
    template.helper('formatPrice2dec', function (price) {
        return util.formatPrice2dec(price);
    });
    exports.compile = function (tpl, data) {
        return template.compile(tpl)(data);
    };
});