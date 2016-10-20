define(function(require, exports, module){
    // require("zepto.js");
    
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
        },
        {"code" : "130000", "name" : "河北省",
            "cities" : [
                {"code":"130600", "name": "保定市"},
                {"code":"130900", "name": "沧州市"},
                {"code":"130800", "name": "承德市"},
                {"code":"130400", "name": "邯郸市"},
                {"code":"131100", "name": "衡水市"},
                {"code":"131000", "name": "廊坊市"},
                {"code":"130300", "name": "秦皇岛市"},
                {"code":"130100", "name": "石家庄市"},
                {"code":"130200", "name": "唐山市"},
                {"code":"130500", "name": "邢台市"},
                {"code":"130700", "name": "张家口市"}]
        },
        {"code" : "410000", "name" : "河南省",
            "cities" : [
                {"code":"410500", "name": "安阳市"},
                {"code":"410600", "name": "鹤壁市"},
                {"code":"410881", "name": "济源市"},
                {"code":"410800", "name": "焦作市"},
                {"code":"410200", "name": "开封市"},
                {"code":"410300", "name": "洛阳市"},
                {"code":"411300", "name": "南阳市"},
                {"code":"410400", "name": "平顶山市"},
                {"code":"411200", "name": "三门峡市"},
                {"code":"411400", "name": "商丘市"},
                {"code":"410700", "name": "新乡市"},
                {"code":"411500", "name": "信阳市"},
                {"code":"411000", "name": "许昌市"},
                {"code":"410100", "name": "郑州市"},
                {"code":"411600", "name": "周口市"},
                {"code":"411700", "name": "驻马店市"},
                {"code":"411100", "name": "漯河市"},
                {"code":"410900", "name": "濮阳市"}]
        },
        {"code" : "420000", "name" : "湖北省",
            "cities" : [
                {"code":"420700", "name": "鄂州市"},
                {"code":"422800", "name": "恩施自治州"},
                {"code":"421100", "name": "黄冈市"},
                {"code":"420200", "name": "黄石市"},
                {"code":"420800", "name": "荆门市"},
                {"code":"421000", "name": "荆州市"},
                {"code":"429005", "name": "潜江市"},
                {"code":"429021", "name": "神农架林区"},
                {"code":"420300", "name": "十堰市"},
                {"code":"421300", "name": "随州市"},
                {"code":"429006", "name": "天门市"},
                {"code":"420100", "name": "武汉市"},
                {"code":"429004", "name": "仙桃市"},
                {"code":"421200", "name": "咸宁市"},
                {"code":"420600", "name": "襄樊市"},
                {"code":"420900", "name": "孝感市"},
                {"code":"420500", "name": "宜昌市"}]
        },
        {"code" : "430000", "name" : "湖南省",
            "cities" : [
                {"code":"430700", "name": "常德市"},
                {"code":"430100", "name": "长沙市"},
                {"code":"431000", "name": "郴州市"},
                {"code":"430400", "name": "衡阳市"},
                {"code":"431200", "name": "怀化市"},
                {"code":"431300", "name": "娄底市"},
                {"code":"430500", "name": "邵阳市"},
                {"code":"430300", "name": "湘潭市"},
                {"code":"433100", "name": "湘西自治州"},
                {"code":"430900", "name": "益阳市"},
                {"code":"431100", "name": "永州市"},
                {"code":"430600", "name": "岳阳市"},
                {"code":"430800", "name": "张家界市"},
                {"code":"430200", "name": "株洲市"}]
        },
        {"code" : "320000", "name" : "江苏省",
            "cities" : [
                {"code":"320400", "name": "常州"},
                {"code":"320800", "name": "淮安"},
                {"code":"320700", "name": "连云港"},
                {"code":"320100", "name": "南京"},
                {"code":"320600", "name": "南通"},
                {"code":"320500", "name": "苏州"},
                {"code":"321300", "name": "宿迁"},
                {"code":"321200", "name": "泰州"},
                {"code":"320200", "name": "无锡"},
                {"code":"320300", "name": "徐州"},
                {"code":"320900", "name": "盐城"},
                {"code":"321000", "name": "扬州"},
                {"code":"321100", "name": "镇江"}]
        },
        {"code" : "210000", "name" : "辽宁省",
            "cities" : [
                {"code":"210300", "name": "鞍山市"},
                {"code":"210500", "name": "本溪市"},
                {"code":"211300", "name": "朝阳市"},
                {"code":"210600", "name": "丹东市"},
                {"code":"210400", "name": "抚顺市"},
                {"code":"210900", "name": "阜新市"},
                {"code":"211400", "name": "葫芦岛市"},
                {"code":"210700", "name": "锦州市"},
                {"code":"211000", "name": "辽阳市"},
                {"code":"211100", "name": "盘锦市"},
                {"code":"210100", "name": "沈阳市"},
                {"code":"211200", "name": "铁岭市"},
                {"code":"210800", "name": "营口市"}]
        },
        {"code" : "370000", "name" : "山东省",
            "cities" : [
                {"code":"371600", "name": "滨州市"},
                {"code":"371400", "name": "德州市"},
                {"code":"370500", "name": "东营市"},
                {"code":"371700", "name": "菏泽市"},
                {"code":"370100", "name": "济南市"},
                {"code":"370800", "name": "济宁市"},
                {"code":"371200", "name": "莱芜市"},
                {"code":"371500", "name": "聊城市"},
                {"code":"371300", "name": "临沂市"},
                {"code":"370200", "name": "青岛市"},
                {"code":"371100", "name": "日照市"},
                {"code":"370900", "name": "泰安市"},
                {"code":"371000", "name": "威海市"},
                {"code":"370700", "name": "潍坊市"},
                {"code":"370600", "name": "烟台市"},
                {"code":"370400", "name": "枣庄市"},
                {"code":"370300", "name": "淄博市"}]
        },
        {"code" : "140000", "name" : "山西省",
            "cities" : [
                {"code":"140400", "name": "长治市"},
                {"code":"140200", "name": "大同市"},
                {"code":"140500", "name": "晋城市"},
                {"code":"140700", "name": "晋中市"},
                {"code":"141000", "name": "临汾市"},
                {"code":"141100", "name": "吕梁市"},
                {"code":"140600", "name": "朔州市"},
                {"code":"140100", "name": "太原市"},
                {"code":"140900", "name": "忻州市"},
                {"code":"140300", "name": "阳泉市"},
                {"code":"140800", "name": "运城市"}]
        },
        {"code" : "510000", "name" : "四川省",
            "cities" : [
                {"code":"513200", "name": "阿坝藏族羌族自治州"},
                {"code":"511900", "name": "巴中市"},
                {"code":"510100", "name": "成都市"},
                {"code":"511700", "name": "达州市"},
                {"code":"510600", "name": "德阳市"},
                {"code":"513300", "name": "甘孜藏族自治州"},
                {"code":"511600", "name": "广安市"},
                {"code":"510800", "name": "广元市"},
                {"code":"511100", "name": "乐山市"},
                {"code":"513400", "name": "凉山彝族自治州"},
                {"code":"511400", "name": "眉山市"},
                {"code":"510700", "name": "绵阳市"},
                {"code":"511300", "name": "南充市"},
                {"code":"511000", "name": "内江市"},
                {"code":"510400", "name": "攀枝花市"},
                {"code":"510900", "name": "遂宁市"},
                {"code":"511800", "name": "雅安市"},
                {"code":"511500", "name": "宜宾市"},
                {"code":"512000", "name": "资阳市"},
                {"code":"510300", "name": "自贡市"},
                {"code":"510500", "name": "泸州市"}]
        },
        {"code" : "330000", "name" : "浙江省",
            "cities" : [
                {"code":"330100", "name": "杭州市"},
                {"code":"330500", "name": "湖州市"},
                {"code":"330400", "name": "嘉兴市"},
                {"code":"330700", "name": "金华市"},
                {"code":"331100", "name": "丽水市"},
                {"code":"330600", "name": "绍兴市"},
                {"code":"331000", "name": "台州市"},
                {"code":"330300", "name": "温州市"},
                {"code":"330900", "name": "舟山市"},
                {"code":"330800", "name": "衢州市"}]
        }];
    
    module.exports = {
        getCityName: function(code) {
            var index = code.indexOf("_");
            if (index < 0) return "";
            var cityName = "";
            var provinceCode = code.substr(0, index);
            var cityCode = code.substr(index + 1);
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
        isChooseProvince: function(code) {
            return "000000" == code;
        },
        getCity: function(provinceCode) {
            for (var i = 0; i < cityInfoes.length; i++) {
                var province = cityInfoes[i];
                if (province.code == provinceCode) {
                    var cities = province.cities;
                    return cities;
                }
            }
        },
        hasCity: function(provinceCode) {
            for (var i = 0; i < cityInfoes.length; i++) {
                var province = cityInfoes[i];
                if (province.code == provinceCode) {
                    return true;
                }
            }
        },
        showSelector: function(code) {
            return "<a href='#common/chooseCity?code=000000'><input id='js_address' type='text' code='" + code + "' readonly='readonly' placeholder='请选择省市' /></a>";
        }
    };
});