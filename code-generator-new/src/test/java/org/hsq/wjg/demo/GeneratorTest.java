package org.hsq.wjg.demo;

import org.hsq.wjg.demo.generator.Generator;
import org.hsq.wjg.demo.generator.enums.FrameworkEnum;
import org.hsq.wjg.demo.generator.pojo.ProjectInfo;
import org.hsq.wjg.demo.generator.enums.ProjectStyleEnum;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by wujigang on 2016/11/1.
 */
public class GeneratorTest {
    @Test
    public void generator() throws IOException {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setDirectory("F:\\temp\\hsq_component\\tmp\\test\\project");
        projectInfo.setName("Test-Generated-Project");
        projectInfo.setStyle(ProjectStyleEnum.SIMPLE_INTERFACE);
        projectInfo.setMainPackage("com.hsq.wjg.demo.codeGenerator");
        projectInfo.setGroupId("com.hsq.demo");
        projectInfo.addFrameworks(FrameworkEnum.SPRING_WEB, FrameworkEnum.MYBATIES, FrameworkEnum.DUBBO, FrameworkEnum.HSQ_COMPONENT, FrameworkEnum.LOG4J);
        Generator.generator(projectInfo);
    }


    @Test
    public void generatorAssetFb() throws IOException {
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setDirectory("F:\\CODE\\baofoo\\mandao-asset\\asset-fb-regular");
        projectInfo.setName("fb-regular");
        projectInfo.setStyle(ProjectStyleEnum.SIMPLE_INTERFACE);
        projectInfo.setMainPackage("com.mandao.asset.fb.regular");
        projectInfo.setGroupId("com.mandao.asset");
        projectInfo.addFrameworks(FrameworkEnum.SPRING_WEB, FrameworkEnum.MYBATIES, FrameworkEnum.DUBBO, FrameworkEnum.HSQ_COMPONENT, FrameworkEnum.LOG4J);
        Generator.generator(projectInfo);
    }
}
