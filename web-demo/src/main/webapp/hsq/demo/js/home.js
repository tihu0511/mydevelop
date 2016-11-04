define(function (require, exports, module) {
    var tpl = require('html/home.html');
    // var address = require("js/common/address");
    var addressComp = require("js/common/addressComponent");

    return {
        pagedata: {},
        tpl: tpl,
        render: function () {
            // $("#citySelector").html(address.showSelector("330000_330100"));

            // app.provinceCode && app.cityCode && $("#js_address").val(address.getCityName(app.provinceCode + "_" + app.cityCode));
            var address = addressComp.createAddressComp('111', '222');
            var address1 = addressComp.createAddressComp('444', '555');

            $("#cityselector").html(address.refresh());
        }
    }
})