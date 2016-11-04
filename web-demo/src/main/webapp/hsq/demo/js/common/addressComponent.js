define(function (require, exports, module) {
    var cityInfoes = [{"code" : "000000", "name" : "",
        "cities" : [
            {"code":"340000", "name" : "安徽省"},
            {"code":"110000", "name" : "北京市"},
            {"code":"440000", "name" : "广东省"},
            {"code":"130000", "name" : "河北省"},
            {"code":"410000", "name" : "河南省"},
            {"code":"420000", "name" : "湖北省"},
            {"code":"430000", "name" : "湖南省"},
            {"code":"320000", "name" : "江苏省"},
            {"code":"210000", "name" : "辽宁省"},
            {"code":"370000", "name" : "山东省"},
            {"code":"140000", "name" : "山西省"},
            {"code":"310000", "name" : "上海市"},
            {"code":"510000", "name" : "四川省"},
            {"code":"120000", "name" : "天津市"},
            {"code":"330000", "name" : "浙江省"},
            {"code":"500000", "name" : "重庆市"}]
    },
        {"code" : "340000", "name" : "安徽省",
            "cities" : [
                {"code":"340800", "name": "安庆市"},
                {"code":"340300", "name": "蚌埠市"},
                {"code":"341400", "name": "巢湖市"},
                {"code":"341700", "name": "池州市"},
                {"code":"341100", "name": "滁州市"},
                {"code":"341200", "name": "阜阳市"},
                {"code":"340100", "name": "合肥市"},
                {"code":"340600", "name": "淮北市"},
                {"code":"340400", "name": "淮南市"},
                {"code":"341000", "name": "黄山市"},
                {"code":"341500", "name": "六安市"},
                {"code":"340500", "name": "马鞍山市"},
                {"code":"341300", "name": "宿州市"},
                {"code":"340700", "name": "铜陵市"},
                {"code":"340200", "name": "芜湖市"},
                {"code":"341800", "name": "宣城市"},
                {"code":"341600", "name": "亳州市"}]
        },
        {"code" : "440000", "name" : "广东省",
            "cities" : [
                {"code":"445100", "name": "潮州市"},
                {"code":"441900", "name": "东莞市"},
                {"code":"440600", "name": "佛山市"},
                {"code":"440100", "name": "广州市"},
                {"code":"441600", "name": "河源市"},
                {"code":"441300", "name": "惠州市"},
                {"code":"440700", "name": "江门市"},
                {"code":"445200", "name": "揭阳市"},
                {"code":"440900", "name": "茂名市"},
                {"code":"441400", "name": "梅州市"},
                {"code":"441800", "name": "清远市"},
                {"code":"440500", "name": "汕头市"},
                {"code":"441500", "name": "汕尾市"},
                {"code":"440200", "name": "韶关市"},
                {"code":"440300", "name": "深圳市"},
                {"code":"441700", "name": "阳江市"},
                {"code":"445300", "name": "云浮市"},
                {"code":"440800", "name": "湛江市"},
                {"code":"441200", "name": "肇庆市"},
                {"code":"442000", "name": "中山市"},
                {"code":"440400", "name": "珠海市"}]
        }];

    exports.address = function Address(provinceCode, cityCode) {
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;

        getFullCityName = function (code) {
            var index = code.indexOf("_");
            if (index < 0) return "";
            var cityName = "", provinceCode = code.substr(0, index), cityCode = code.substr(index + 1);
            if (provinceCode == cityCode) {
                var provinces = cityInfoes[0].cities;
                for (var p in provinces) {
                    var province = provinces[p];
                    if (province.code == provinceCode) {
                        return province.name;
                    }
                }
            }
            for (var i in cityInfoes) {
                var province = cityInfoes[i];
                if (provinceCode == province.code) {
                    cityName += province.name;
                    if (cityCode != provinceCode && province.cities != null && province.cities.length > 0) {
                        for (var j in province.cities) {
                            var city = province.cities[j];
                            if (city.code == cityCode) {
                                cityName += city.name;
                            }
                        }
                    }
                    break;
                }
            }
            return cityName;
        },
            refresh = function () {
                var code = !this.provinceCode && !this.cityCode && this.provinceCode + "_" + this.cityCode;
                var value = code && this.getFullCityName(code);
                return "<a href='#common/chooseCity?code=000000'><input id='js_address' type='text' " +
                    "code='" + code + "' readonly='readonly' placeholder='请选择省市' value='" + (value || "") + "' /></a>";
            }
    }

    module.exports = {
        createAddressComp: function (provinceCode, cityCode) {
            return  new Address(provinceCode, cityCode);
        },
        render: function (address) {
            return address.refresh();
        }
    }
})