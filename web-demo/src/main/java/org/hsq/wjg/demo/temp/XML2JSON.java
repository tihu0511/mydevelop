package org.hsq.wjg.demo.temp;

import com.hsq.component.dom4j.Dom4jXmlUtil;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.Element;

import java.text.Collator;
import java.util.*;

/**
 * Created by wujigang on 2016/10/17.
 */
public class XML2JSON {
    public static void main(String[] args) throws Exception {
        String xml = "<select class=\"bfui_cell_input js_area_i\">\n" +
                "                    <optgroup label=\"北京市\">\n" +
                "                        <option value =\"110000_110000\">北京市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"天津市\">\n" +
                "                        <option value =\"120000_120000\">天津市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"河北省\">\n" +
                "                        <option value =\"130000_130100\">石家庄市</option>\n" +
                "                        <option value =\"130000_130200\">唐山市</option>\n" +
                "                        <option value =\"130000_130300\">秦皇岛市</option>\n" +
                "                        <option value =\"130000_130400\">邯郸市</option>\n" +
                "                        <option value =\"130000_130500\">邢台市</option>\n" +
                "                        <option value =\"130000_130600\">保定市</option>\n" +
                "                        <option value =\"130000_130700\">张家口市</option>\n" +
                "                        <option value =\"130000_130800\">承德市</option>\n" +
                "                        <option value =\"130000_130900\">沧州市</option>\n" +
                "                        <option value =\"130000_131000\">廊坊市</option>\n" +
                "                        <option value =\"130000_131100\">衡水市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"山西省\">\n" +
                "                        <option value =\"140000_140100\">太原市</option>\n" +
                "                        <option value =\"140000_140200\">大同市</option>\n" +
                "                        <option value =\"140000_140300\">阳泉市</option>\n" +
                "                        <option value =\"140000_140400\">长治市</option>\n" +
                "                        <option value =\"140000_140500\">晋城市</option>\n" +
                "                        <option value =\"140000_140600\">朔州市</option>\n" +
                "                        <option value =\"140000_140700\">晋中市</option>\n" +
                "                        <option value =\"140000_140800\">运城市</option>\n" +
                "                        <option value =\"140000_140900\">忻州市</option>\n" +
                "                        <option value =\"140000_141000\">临汾市</option>\n" +
                "                        <option value =\"140000_141100\">吕梁市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"辽宁省\">\n" +
                "                        <option value =\"210000_210100\">沈阳市</option>\n" +
                "                        <option value =\"210000_210300\">鞍山市</option>\n" +
                "                        <option value =\"210000_210400\">抚顺市</option>\n" +
                "                        <option value =\"210000_210500\">本溪市</option>\n" +
                "                        <option value =\"210000_210600\">丹东市</option>\n" +
                "                        <option value =\"210000_210700\">锦州市</option>\n" +
                "                        <option value =\"210000_210800\">营口市</option>\n" +
                "                        <option value =\"210000_210900\">阜新市</option>\n" +
                "                        <option value =\"210000_211000\">辽阳市</option>\n" +
                "                        <option value =\"210000_211100\">盘锦市</option>\n" +
                "                        <option value =\"210000_211200\">铁岭市</option>\n" +
                "                        <option value =\"210000_211300\">朝阳市</option>\n" +
                "                        <option value =\"210000_211400\">葫芦岛市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"上海市\">\n" +
                "                        <option value =\"310000_310000\">上海市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"江苏省\">\n" +
                "                        <option value =\"320000_320100\">南京</option>\n" +
                "                        <option value =\"320000_320200\">无锡</option>\n" +
                "                        <option value =\"320000_320300\">徐州</option>\n" +
                "                        <option value =\"320000_320400\">常州</option>\n" +
                "                        <option value =\"320000_320500\">苏州</option>\n" +
                "                        <option value =\"320000_320600\">南通</option>\n" +
                "                        <option value =\"320000_320700\">连云港</option>\n" +
                "                        <option value =\"320000_320800\">淮安</option>\n" +
                "                        <option value =\"320000_320900\">盐城</option>\n" +
                "                        <option value =\"320000_321000\">扬州</option>\n" +
                "                        <option value =\"320000_321100\">镇江</option>\n" +
                "                        <option value =\"320000_321200\">泰州</option>\n" +
                "                        <option value =\"320000_321300\">宿迁</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"浙江省\">\n" +
                "                        <option value =\"330000_330100\">杭州市</option>\n" +
                "                        <option value =\"330000_330300\">温州市</option>\n" +
                "                        <option value =\"330000_330400\">嘉兴市</option>\n" +
                "                        <option value =\"330000_330500\">湖州市</option>\n" +
                "                        <option value =\"330000_330600\">绍兴市</option>\n" +
                "                        <option value =\"330000_330700\">金华市</option>\n" +
                "                        <option value =\"330000_330800\">衢州市</option>\n" +
                "                        <option value =\"330000_330900\">舟山市</option>\n" +
                "                        <option value =\"330000_331000\">台州市</option>\n" +
                "                        <option value =\"330000_331100\">丽水市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"安徽省\">\n" +
                "                        <option value =\"340000_340100\">合肥市</option>\n" +
                "                        <option value =\"340000_340200\">芜湖市</option>\n" +
                "                        <option value =\"340000_340300\">蚌埠市</option>\n" +
                "                        <option value =\"340000_340400\">淮南市</option>\n" +
                "                        <option value =\"340000_340500\">马鞍山市</option>\n" +
                "                        <option value =\"340000_340600\">淮北市</option>\n" +
                "                        <option value =\"340000_340700\">铜陵市</option>\n" +
                "                        <option value =\"340000_340800\">安庆市</option>\n" +
                "                        <option value =\"340000_341000\">黄山市</option>\n" +
                "                        <option value =\"340000_341100\">滁州市</option>\n" +
                "                        <option value =\"340000_341200\">阜阳市</option>\n" +
                "                        <option value =\"340000_341300\">宿州市</option>\n" +
                "                        <option value =\"340000_341400\">巢湖市</option>\n" +
                "                        <option value =\"340000_341500\">六安市</option>\n" +
                "                        <option value =\"340000_341600\">亳州市</option>\n" +
                "                        <option value =\"340000_341700\">池州市</option>\n" +
                "                        <option value =\"340000_341800\">宣城市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"山东省\">\n" +
                "                        <option value =\"370000_370100\">济南市</option>\n" +
                "                        <option value =\"370000_370200\">青岛市</option>\n" +
                "                        <option value =\"370000_370300\">淄博市</option>\n" +
                "                        <option value =\"370000_370400\">枣庄市</option>\n" +
                "                        <option value =\"370000_370500\">东营市</option>\n" +
                "                        <option value =\"370000_370600\">烟台市</option>\n" +
                "                        <option value =\"370000_370700\">潍坊市</option>\n" +
                "                        <option value =\"370000_370800\">济宁市</option>\n" +
                "                        <option value =\"370000_370900\">泰安市</option>\n" +
                "                        <option value =\"370000_371000\">威海市</option>\n" +
                "                        <option value =\"370000_371100\">日照市</option>\n" +
                "                        <option value =\"370000_371200\">莱芜市</option>\n" +
                "                        <option value =\"370000_371300\">临沂市</option>\n" +
                "                        <option value =\"370000_371400\">德州市</option>\n" +
                "                        <option value =\"370000_371500\">聊城市</option>\n" +
                "                        <option value =\"370000_371600\">滨州市</option>\n" +
                "                        <option value =\"370000_371700\">菏泽市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"河南省\">\n" +
                "                        <option value =\"410000_410100\">郑州市</option>\n" +
                "                        <option value =\"410000_410200\">开封市</option>\n" +
                "                        <option value =\"410000_410300\">洛阳市</option>\n" +
                "                        <option value =\"410000_410400\">平顶山市</option>\n" +
                "                        <option value =\"410000_410500\">安阳市</option>\n" +
                "                        <option value =\"410000_410600\">鹤壁市</option>\n" +
                "                        <option value =\"410000_410700\">新乡市</option>\n" +
                "                        <option value =\"410000_410800\">焦作市</option>\n" +
                "                        <option value =\"410000_410881\">济源市</option>\n" +
                "                        <option value =\"410000_410900\">濮阳市</option>\n" +
                "                        <option value =\"410000_411000\">许昌市</option>\n" +
                "                        <option value =\"410000_411100\">漯河市</option>\n" +
                "                        <option value =\"410000_411200\">三门峡市</option>\n" +
                "                        <option value =\"410000_411300\">南阳市</option>\n" +
                "                        <option value =\"410000_411400\">商丘市</option>\n" +
                "                        <option value =\"410000_411500\">信阳市</option>\n" +
                "                        <option value =\"410000_411600\">周口市</option>\n" +
                "                        <option value =\"410000_411700\">驻马店市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"湖北省\">\n" +
                "                        <option value =\"420000_420100\">武汉市</option>\n" +
                "                        <option value =\"420000_420200\">黄石市</option>\n" +
                "                        <option value =\"420000_420300\">十堰市</option>\n" +
                "                        <option value =\"420000_420500\">宜昌市</option>\n" +
                "                        <option value =\"420000_420600\">襄樊市</option>\n" +
                "                        <option value =\"420000_420700\">鄂州市</option>\n" +
                "                        <option value =\"420000_420800\">荆门市</option>\n" +
                "                        <option value =\"420000_420900\">孝感市</option>\n" +
                "                        <option value =\"420000_421000\">荆州市</option>\n" +
                "                        <option value =\"420000_421100\">黄冈市</option>\n" +
                "                        <option value =\"420000_421200\">咸宁市</option>\n" +
                "                        <option value =\"420000_421300\">随州市</option>\n" +
                "                        <option value =\"420000_422800\">恩施自治州</option>\n" +
                "                        <option value =\"420000_429004\">仙桃市</option>\n" +
                "                        <option value =\"420000_429005\">潜江市</option>\n" +
                "                        <option value =\"420000_429006\">天门市</option>\n" +
                "                        <option value =\"420000_429021\">神农架林区</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"湖南省\">\n" +
                "                        <option value =\"430000_430100\">长沙市</option>\n" +
                "                        <option value =\"430000_430200\">株洲市</option>\n" +
                "                        <option value =\"430000_430300\">湘潭市</option>\n" +
                "                        <option value =\"430000_430400\">衡阳市</option>\n" +
                "                        <option value =\"430000_430500\">邵阳市</option>\n" +
                "                        <option value =\"430000_430600\">岳阳市</option>\n" +
                "                        <option value =\"430000_430700\">常德市</option>\n" +
                "                        <option value =\"430000_430800\">张家界市</option>\n" +
                "                        <option value =\"430000_430900\">益阳市</option>\n" +
                "                        <option value =\"430000_431000\">郴州市</option>\n" +
                "                        <option value =\"430000_431100\">永州市</option>\n" +
                "                        <option value =\"430000_431200\">怀化市</option>\n" +
                "                        <option value =\"430000_431300\">娄底市</option>\n" +
                "                        <option value =\"430000_433100\">湘西自治州</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"广东省\">\n" +
                "                        <option value =\"440000_440100\">广州市</option>\n" +
                "                        <option value =\"440000_440200\">韶关市</option>\n" +
                "                        <option value =\"440000_440300\">深圳市</option>\n" +
                "                        <option value =\"440000_440400\">珠海市</option>\n" +
                "                        <option value =\"440000_440500\">汕头市</option>\n" +
                "                        <option value =\"440000_440600\">佛山市</option>\n" +
                "                        <option value =\"440000_440700\">江门市</option>\n" +
                "                        <option value =\"440000_440800\">湛江市</option>\n" +
                "                        <option value =\"440000_440900\">茂名市</option>\n" +
                "                        <option value =\"440000_441200\">肇庆市</option>\n" +
                "                        <option value =\"440000_441300\">惠州市</option>\n" +
                "                        <option value =\"440000_441400\">梅州市</option>\n" +
                "                        <option value =\"440000_441500\">汕尾市</option>\n" +
                "                        <option value =\"440000_441600\">河源市</option>\n" +
                "                        <option value =\"440000_441700\">阳江市</option>\n" +
                "                        <option value =\"440000_441800\">清远市</option>\n" +
                "                        <option value =\"440000_441900\">东莞市</option>\n" +
                "                        <option value =\"440000_442000\">中山市</option>\n" +
                "                        <option value =\"440000_445100\">潮州市</option>\n" +
                "                        <option value =\"440000_445200\">揭阳市</option>\n" +
                "                        <option value =\"440000_445300\">云浮市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"重庆市\">\n" +
                "                        <option value =\"500000_500000\">重庆市</option>\n" +
                "                    </optgroup>\n" +
                "                    <optgroup label=\"四川省\">\n" +
                "                        <option value =\"510000_510100\">成都市</option>\n" +
                "                        <option value =\"510000_510300\">自贡市</option>\n" +
                "                        <option value =\"510000_510400\">攀枝花市</option>\n" +
                "                        <option value =\"510000_510500\">泸州市</option>\n" +
                "                        <option value =\"510000_510600\">德阳市</option>\n" +
                "                        <option value =\"510000_510700\">绵阳市</option>\n" +
                "                        <option value =\"510000_510800\">广元市</option>\n" +
                "                        <option value =\"510000_510900\">遂宁市</option>\n" +
                "                        <option value =\"510000_511000\">内江市</option>\n" +
                "                        <option value =\"510000_511100\">乐山市</option>\n" +
                "                        <option value =\"510000_511300\">南充市</option>\n" +
                "                        <option value =\"510000_511400\">眉山市</option>\n" +
                "                        <option value =\"510000_511500\">宜宾市</option>\n" +
                "                        <option value =\"510000_511600\">广安市</option>\n" +
                "                        <option value =\"510000_511700\">达州市</option>\n" +
                "                        <option value =\"510000_511800\">雅安市</option>\n" +
                "                        <option value =\"510000_511900\">巴中市</option>\n" +
                "                        <option value =\"510000_512000\">资阳市</option>\n" +
                "                        <option value =\"510000_513200\">阿坝藏族羌族自治州</option>\n" +
                "                        <option value =\"510000_513300\">甘孜藏族自治州</option>\n" +
                "                        <option value =\"510000_513400\">凉山彝族自治州</option>\n" +
                "                    </optgroup>\n" +
                "                </select>";

        Document document = Dom4jXmlUtil.parseXmlDocument(xml);
        Element root = document.getRootElement();

        List<Province> provinceList = new ArrayList<Province>();
        List<Element> optgroup = root.elements("optgroup");
        for (Element opt : optgroup) {
            Province province = new Province();
            String provinceName = opt.attribute("label").getValue();
//            System.out.println("省份： " + provinceName);
            province.setName(provinceName);

            List<Element> options = opt.elements("option");
            if (null != options && options.size() > 0) {
                List<City> cities = new ArrayList<City>();
                for (Element option : options) {
                    String cityCode = option.attribute("value").getValue();
                    int index = cityCode.indexOf("_");
                    province.setCode(cityCode.substring(0, index));
                    if (options.size() == 1 && cityCode.substring(0, index).equals(cityCode.substring(index + 1))) {
                        continue;
                    }
                    City city = new City();
                    city.setCode(cityCode.substring(index + 1));
                    city.setName(option.getTextTrim());
//                    System.out.println("\t城市：" + city.getCode() + " " + city.getName());
                    cities.add(city);
                }
                province.setCities(cities);
            }

            provinceList.add(province);
        }

        Collections.sort(provinceList, new Comparator<Province>() {
            @Override
            public int compare(Province o1, Province o2) {
                Collator instance = Collator.getInstance(Locale.CHINA);
                return instance.compare(o1.getName(), o2.getName());
            }
        });


        StringBuilder json = new StringBuilder();
        StringBuilder psb = new StringBuilder();//省份
        StringBuilder csb = new StringBuilder();//城市
        json.append("[{\"code\" : \"000000\", \"name\" : \"\", \n\t\"cities\" : [");
        for (Province province : provinceList) {
//            System.out.println("省份： " + province.getName());

            psb.append("\n\t\t{\"code\":\"").append(province.getCode()).append("\", \"name\" : \"").append(province.getName()).append("\"},");

            if (null != province.getCities() && province.getCities().size() > 0) {
                Collections.sort(province.getCities(), new Comparator<City>() {
                    @Override
                    public int compare(City o1, City o2) {
                        Collator instance = Collator.getInstance(Locale.CHINA);
                        return instance.compare(o1.getName(), o2.getName());
                    }
                });
                csb.append("\n{\"code\" : \"").append(province.getCode()).append("\", \"name\" : \"").append(province.getName()).append("\",\n" +
                        "\t\"cities\" : [");
                for (City city : province.getCities()) {
//                    System.out.println("\t城市：" + city.getCode() + " " + city.getName());

                    csb.append("\n\t\t{\"code\":\"").append(city.getCode()).append("\", \"name\": \"").append(city.getName()).append("\"},");
                }
                csb.deleteCharAt(csb.length() - 1);
                csb.append("]\n},");
            }
        }

        json.append(psb.substring(0, psb.length() - 1)).append("]\n},");
        json.append(csb.substring(0, csb.length() - 1)).append("\n]");
        System.out.println(json);
    }

}
