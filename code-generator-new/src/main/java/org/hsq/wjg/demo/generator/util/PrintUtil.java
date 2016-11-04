package org.hsq.wjg.demo.generator.util;

import org.hsq.wjg.demo.generator.constants.GeneratorConstants;

/**
 * Created by wujigang on 2016/11/1.
 */
public class PrintUtil {
    public static void println(String content, Object ... params) {
        System.out.println(GeneratorConstants.PRINT_PREFIX + String.format(content, params));
    }
}
