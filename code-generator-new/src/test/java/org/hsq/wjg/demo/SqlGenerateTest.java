package org.hsq.wjg.demo;

import org.hsq.wjg.demo.generator.SqlGenerator;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wujigang on 2016/11/7.
 */
public class SqlGenerateTest {
    @Test
    public void generate() throws IOException {
        String file = "Y:\\\\baofoo\\\\codeGenerator\\\\database.xlsx";
        String schemaName = "MANDAO_PROD_FB";
        String resourcesPath = "F:\\CODE\\demos\\mydevelop\\code-generator-new\\src\\main";
        new SqlGenerator(schemaName, resourcesPath).generate(file);
    }
}
