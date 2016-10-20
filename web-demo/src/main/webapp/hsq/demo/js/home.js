define(function (require, exports, module) {
    var tpl = require('html/home.html');
    var address = require("js/common/address");

    return {
        pagedata: {},
        tpl: tpl,
        render: function () {
            $("#citySelector").html(address.showSelector("330000_330100"));

            app.provinceCode && app.cityCode && $("#js_address").val(address.getCityName(app.provinceCode + "_" + app.cityCode));
        }
    }
})