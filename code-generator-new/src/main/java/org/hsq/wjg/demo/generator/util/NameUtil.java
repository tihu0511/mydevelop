package org.hsq.wjg.demo.generator.util;

/**
 * Created by wjg on 2016/11/14.
 */
public class NameUtil {
    public static String getCamelName(String moduleName) {
        String[] words = moduleName.split("-");
        if (null == words || words.length <= 0) {
            words = moduleName.split("_");
        }

        if (words != null && words.length > 0) {
            String camelName = "";
            for (String word : words) {
                camelName += word.substring(0, 1).toUpperCase() + word.substring(1, word.length()).toLowerCase();
            }
            return camelName;
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "NimblePurchase";
        System.out.println(getCamelName(str));
    }
}
