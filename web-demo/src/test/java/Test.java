import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjg on 2016/11/16.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        String str = "http://www.gxyj.com/searchproducts/pv.jhtml?storeFilter=[]&categoryFilter=[]&brandFilter=[]&skuFilter=[]&provinceFilter=&cityFilter=&priceRegionFilter={}&sortFilter=0&dirFilter=1&promotionFilter=[]&resultSearchFilter=&query=&searchType=CATEGORY&viewType=large&selectedSearch=[]&catId=79001&exQuery=&currentPage=1&mallId=00000000&propFilter=[{\"prodPropId\":\"0000100283\",\"prodPropEnum\":\"扶贫商品\",\"prodPropEnumId\":\"0000100283\"}]&authIdArr=fpsp";
        System.out.println(str.replaceAll("\"", "1234567"));

        testOutOfMemory();
    }

//    @org.junit.Test
    public static void testOutOfMemory() throws InterruptedException {
        int i = 0;
        while (true) {
            List<String> strs = new ArrayList<String>();
            strs.add("string " + i++);
            if (i % 10 == 0) {
                System.out.println("第" + i + "个对象");
            }

//            Thread.sleep(10);
        }
    }
}
