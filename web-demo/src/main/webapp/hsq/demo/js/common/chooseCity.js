define(function (require, exports, module) {
    var tpl = require('html/common/chooseCity.html');
    require('template');
    var address = require('js/common/address');

    return {
        pagedata: {},
        tpl: tpl,
        cityInfo : {},
        preload: function(param,callback){
            

            callback({});
        },
        bind: function () {
            $(".container").on('click', ".citySelector", function(){
                var code = $(this).children("div").attr("data-city");
                var url;
                if ($("#isProvince").val() == "true") {
                    app.provinceCode = code;//省份代码
                    
                    if (address.hasCity(code)) {//省下面是否有城市
                        url = "#common/chooseCity?code=" + code;
                    } else {//省下面没有市，则省和市code一样
                        app.cityCode = code;
                        url = app.back(-1);
                    }
                } else {
                    app.cityCode = code;
                    url = app.back(-2);
                }
                app.go(url);
            });
        },
        render: function () {
            var url = location.href;
            var code = url.substr(url.indexOf("?code=") + 6);
            var isProvince = address.isChooseProvince(code);
            $("#isProvince").val(isProvince);
            var titleText = isProvince ? "选择省份" : "选择城市";
            var title = {
                title: {
                    left: [{type: "back", text: null, onclickType: "js", onclick: "app.back"}],
                    center: [{text: titleText}],
                    right: []
                }
            };
            baofoo.callNative("setWebView", JSON.stringify(title));

            var cities = address.getCity(code);
            $("#cities").html("");
            for (var i in cities) {
                var city = cities[i];
                $("#cities").append("<div class=\"bfui-cell bfui-cell-access citySelector\"><div class=\"bfui-cell-bd\" data-city=\"" + city.code + "\">" + city.name + "</div></div>");
            }
        }
    };
});