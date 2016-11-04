package org.hsq.wjg.demo.generator;

import org.hsq.wjg.demo.generator.enums.ProjectStyleEnum;
import org.hsq.wjg.demo.generator.pojo.ProjectInfo;
import org.hsq.wjg.demo.generator.stragedy.CompleteStragedyGenerator;
import org.hsq.wjg.demo.generator.stragedy.IProjectGenerator;
import org.hsq.wjg.demo.generator.stragedy.SimpleInterfStragedyGenerator;
import org.hsq.wjg.demo.generator.stragedy.SimpleStragedyGenerator;
import org.hsq.wjg.demo.generator.util.PrintUtil;

import java.io.IOException;

/**
 * Created by wujigang on 2016/11/1.
 */
public class Generator {
    public static void generator(ProjectInfo project) throws IOException {
        IProjectGenerator generator = getGenerator(project.getStyle());
        PrintUtil.println("开始生成项目%s\n风格：%s", project.getName(), project.getStyle().getDesc());
        generator.generator(project);
        PrintUtil.println("生成项目%s结束", project.getName());
    }

    public static IProjectGenerator getGenerator(ProjectStyleEnum style) {
        switch (style.getCode()) {
        case 1:
            return new SimpleStragedyGenerator();
        case 2:
            return new SimpleInterfStragedyGenerator();
        case 3:
            return new CompleteStragedyGenerator();
        default:
            return null;
        }
    }

}
