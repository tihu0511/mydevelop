package org.hsq.wjg.demo;

import org.hsq.wjg.demo.generator.WsGenerator;

import java.io.IOException;

/**
 * Created by wjg on 2016/11/14.
 */
public class WsGeneratorTest {
    public static void main(String[] args) throws IOException {
        String file = "Y:\\baofoo\\codeGenerator\\测试生成接口代码.xlsx";
        String projectPath = "M:\\develop\\java\\CODE\\my_develop\\mydevelop\\";
        new WsGenerator("org.hsq.wjg.demo.generator.tmp", projectPath, "code-generator-new", "code-generator-new").generate(file);
    }
}