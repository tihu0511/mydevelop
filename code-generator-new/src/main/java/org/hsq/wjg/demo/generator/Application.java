package org.hsq.wjg.demo.generator;

import com.hsq.component.lang.PropertiesUtil;
import org.hsq.wjg.demo.generator.enums.FrameworkEnum;
import org.hsq.wjg.demo.generator.enums.ProjectStyleEnum;
import org.hsq.wjg.demo.generator.pojo.ProjectInfo;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by wjg on 2016/11/15.
 */
public class Application {
    public static void main(String[] args) throws Exception {
        System.out.println("请选择需要的操作\n\t1.生成项目代码(请配置project-generate.properties)\n" +
                "\t2.生成ws接口\n" +
                "\t3.生成sql");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                generateProjectCode();
                break;
            case 2:
                generateWsCode();
                break;
            case 3:
                generateSql();
                break;
            default:
                System.out.println("选择错误!!");
        }
    }

    private static void generateProjectCode() throws IOException {
        Properties properties = PropertiesUtil.getByFile("project-generate.properties");
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setDirectory(properties.getProperty("project.path"));
        projectInfo.setName(properties.getProperty("project.name"));
        projectInfo.setStyle(ProjectStyleEnum.SIMPLE_INTERFACE);
        projectInfo.setMainPackage(properties.getProperty("project.mainPackage"));
        projectInfo.setGroupId(properties.getProperty("project.groupId"));
        projectInfo.addFrameworks(FrameworkEnum.SPRING_WEB, FrameworkEnum.MYBATIES, FrameworkEnum.DUBBO, FrameworkEnum.HSQ_COMPONENT, FrameworkEnum.LOG4J);
        Generator.generator(projectInfo);
    }

    private static void generateWsCode() throws IOException {
        Properties properties = PropertiesUtil.getByFile("project-generate.properties");

        String file = properties.getProperty("interface.doc");
        String projectPath = properties.getProperty("project.path");
        String projectName = properties.getProperty("project.name");
        new WsGenerator(properties.getProperty("project.mainPackage"), projectPath, projectName + "-common", projectName).generate(file);
    }

    private static void generateSql() throws IOException {
        Properties properties = PropertiesUtil.getByFile("project-generate.properties");

        String file = properties.getProperty("database.doc");
        String schemaName = properties.getProperty("database.schema");
        String resourcesPath = properties.getProperty("project.path") + File.separator + properties.getProperty("project.name")
            + File.separator + "src\\main\\resources";
        new SqlGenerator(schemaName, resourcesPath).generate(file);
    }
}
