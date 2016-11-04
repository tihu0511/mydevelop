package org.hsq.wjg.demo.generator.stragedy;

import com.hsq.component.file.FileUtil;
import org.hsq.wjg.demo.generator.enums.FrameworkEnum;
import org.hsq.wjg.demo.generator.pojo.*;
import org.hsq.wjg.demo.generator.constants.GeneratorConstants;
import org.hsq.wjg.demo.generator.util.PrintUtil;
import org.hsq.wjg.demo.generator.util.ResourcesTemplateUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wujigang on 2016/11/1.
 */
public class SimpleInterfStragedyGenerator extends AbstractGenerator implements IProjectGenerator {
    private static final String API_SUFFIX = "-common";

    @Override
    public void generator(ProjectInfo project) throws IOException {
        this.projectInfo = project;
        this.projectInfo.setDirectory(project.getDirectory().endsWith(File.separator) ? project.getDirectory() : project.getDirectory() + File.separator);

        //生成.gitignore
        generateGitIgnore();

        //生成最外层pom.xml
        generateOutPom(projectInfo.getDirectory() + "pom.xml");

        //生成common模块
        generateCommonModule();

        //生成实现模块
        generateServiceModule();
    }

    /**
     * 生成最外层pom.xml
     * @param path
     */
    private void generateOutPom(String path) throws IOException {
        Pom pom = new Pom();
        pom.setParent(new Dependency(GeneratorConstants.HSQ_PARENT_GROUPID, GeneratorConstants.HSQ_PARENT_ARTIFACTID, GeneratorConstants.HSQ_PARENT_VERSION));
        String projectDir = projectInfo.getDirectory();
        rootName = projectDir.substring(projectDir.lastIndexOf(File.separatorChar, projectDir.length() - 2) + 1, projectDir.length() - 1);
        pom.setModule(new Module(projectInfo.getGroupId(), rootName, GeneratorConstants.HSQ_PARENT_VERSION, "pom"));
        pom.addSubModules(projectInfo.getName() + API_SUFFIX, projectInfo.getName());
        generatePom(path, pom);
    }

    /**
     * 生成gitignore文件
     * @throws IOException
     */
    private void generateGitIgnore() throws IOException {
        String projectDir = this.projectInfo.getDirectory();
        FileUtil.mkdirs(projectDir);
        String filePath = projectDir.endsWith(File.separator) ? projectDir + ".gitignore" : projectDir + File.separator + ".gitignore";
        String content = ".idea\n" +
                "target/\n" +
                "*.iml";
        FileUtil.writeFile(filePath, content, GeneratorConstants.DEFAULT_CHARSET);
    }

    /**
     * 生成接口api模块
     * @throws IOException
     */
    private void generateCommonModule() throws IOException {
        //生成项目结构
        String moduleName = projectInfo.getName() + API_SUFFIX;
        PrintUtil.println("开始生成%s模块", moduleName);
        generateModuleStruct(moduleName, 1);

        //生成pom
        Pom pom = new Pom();
        pom.setParent(new Dependency(projectInfo.getGroupId(), rootName, GeneratorConstants.HSQ_PARENT_VERSION));
        pom.setModule(new Module(projectInfo.getGroupId(), moduleName, GeneratorConstants.HSQ_PARENT_VERSION, "jar"));
        pom.addDepenedency(new Dependency(GeneratorConstants.HSQ_COMPONENT_GROUPID, "validation-common", GeneratorConstants.HSQ_COMPONENT_VERSION));
        generatePom(modulePath + File.separator + "pom.xml", pom);

        //生成package  dto,ws
        mainPackagePath = javaCodePath + File.separator + projectInfo.getMainPackage().replace(".", File.separator);
        String dtoPackage = mainPackagePath + File.separator + "dto";
        String wsPackage = mainPackagePath + File.separator + "ws";
        FileUtil.mkdirs(dtoPackage, wsPackage);

        //生成测试代码
        generateTestCode(projectInfo.getMainPackage() + ".dto", javaCodePath + File.separator + projectInfo.getMainPackage().replace(".", File.separator) + File.separator + "dto");
        generateTestCode(projectInfo.getMainPackage() + ".ws", javaCodePath + File.separator + projectInfo.getMainPackage().replace(".", File.separator) + File.separator + "ws");

        PrintUtil.println("生成模块%s结束", moduleName);
    }

    /**
     * 生成业务模块
     */
    private void generateServiceModule() throws IOException {
        //生成项目结构
        String moduleName = projectInfo.getName();
        PrintUtil.println("开始生成%s模块", moduleName);
        generateModuleStruct(moduleName, 3);

        //生成pom
        Pom pom = new Pom();
        pom.setParent(new Dependency(projectInfo.getGroupId(), rootName, GeneratorConstants.HSQ_PARENT_VERSION));
        pom.setModule(new Module(projectInfo.getGroupId(), moduleName, GeneratorConstants.HSQ_PARENT_VERSION, "war"));
        //添加依赖
        pom.addDepenedency(new Dependency(projectInfo.getGroupId(), projectInfo.getName() + API_SUFFIX, GeneratorConstants.HSQ_PARENT_VERSION));
        pom.addDepenedency(resovleDependencies(projectInfo.getFrameworks()));
        generatePom(modulePath + File.separator + "pom.xml", pom);

        //生成resources下的配置文件
        generatedResources();
    }

    /**
     * 生成配置文件
     */
    private void generatedResources() throws IOException {
        List<FrameworkEnum> frameworks = projectInfo.getFrameworks();
        boolean hasSpring = frameworks.contains(FrameworkEnum.SPRING_JAR) || frameworks.contains(FrameworkEnum.SPRING_WEB) || frameworks.contains(FrameworkEnum.SPRING_MVC);
        boolean hasMybatis = frameworks.contains(FrameworkEnum.MYBATIES);
        boolean hasDubbo = frameworks.contains(FrameworkEnum.DUBBO);
        if (hasSpring) {
            //生成spring.xml
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("rootName", rootName);
            params.put("moduleName", projectInfo.getName());
            params.put("mainPackage", projectInfo.getMainPackage());
            params.put("hasMybatis", projectInfo.getMainPackage());
            params.put("hasMybatis", hasMybatis);
            params.put("hasDubbo", hasDubbo);
            String springContent = ResourcesTemplateUtil.getTemplateContent(params, SPRING_TPL_PATH);
            FileUtil.writeFile(resourcesPath + File.separator + "spring.xml", springContent, GeneratorConstants.DEFAULT_CHARSET);

            if (hasMybatis) {
                //生成spring-db.xml
                Map<String, Object> springDbParams = new HashMap<String, Object>();
                springDbParams.put("mainPackage", projectInfo.getMainPackage());
                String springDbContent = ResourcesTemplateUtil.getTemplateContent(springDbParams, SPRING_DB_TPL_PATH);
                FileUtil.writeFile(resourcesPath + File.separator + "spring-db.xml", springDbContent, GeneratorConstants.DEFAULT_CHARSET);
            }
        }
        if (hasDubbo) {
            //生成dubbo.xml dubbo-service.xml dubbo-reference.xml
            Map<String, Object> dubboParams = new HashMap<String, Object>();
            dubboParams.put("rootName", rootName);
            FileUtil.writeFile(resourcesPath + File.separator + "dubbo.xml", ResourcesTemplateUtil.getTemplateContent(dubboParams, DUBBO_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);
            FileUtil.writeFile(resourcesPath + File.separator + "dubbo-service.xml", ResourcesTemplateUtil.getTemplateContent(dubboParams, DUBBO_SERVICE_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);
            FileUtil.writeFile(resourcesPath + File.separator + "dubbo-reference.xml", ResourcesTemplateUtil.getTemplateContent(dubboParams, DUBBO_REFERENCE_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);
            FileUtil.writeFile(resourcesPath + File.separator + "logDubboConf.properties", ResourcesTemplateUtil.getTemplateContent(dubboParams, DUBBO_LOG_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);
        }

        //app.properties  db.properties log4j.properties
        String appPropPath = resourcesPath + File.separator + "app.properties";
        String dbPropPath  = resourcesPath + File.separator + "db.properties";
        String appProdPropPath = resourcesPath + File.separator + "app_product.properties";
        String log4jPath = resourcesPath + File.separator + "log4j.properties";
        FileUtil.writeFile(appPropPath, ResourcesTemplateUtil.getTemplateContent(null, APP_PROP_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);
        FileUtil.writeFile(appProdPropPath, ResourcesTemplateUtil.getTemplateContent(null, APP_PROD_PROP_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);
        FileUtil.writeFile(dbPropPath, ResourcesTemplateUtil.getTemplateContent(null, DB_PROP_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);

        Map<String, Object> log4jParams = new HashMap<String, Object>();
        log4jParams.put("rootName", rootName);
        log4jParams.put("moduleName", projectInfo.getName());
        FileUtil.writeFile(log4jPath, ResourcesTemplateUtil.getTemplateContent(log4jParams, LOG4J_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);

        //web.xml index.jsp
        String webXmlPath = webappPath + File.separator + "WEB-INF" + File.separator + "web.xml";
        String indexJspPath = webappPath + File.separator + "index.jsp";
        FileUtil.writeFile(webXmlPath, ResourcesTemplateUtil.getTemplateContent(null, WEB_XML_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);
        FileUtil.writeFile(indexJspPath, ResourcesTemplateUtil.getTemplateContent(null, INDEX_JSP_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);

        //mybatis-generator
        if (hasMybatis) {
            String generateCodePath = testCodePath + File.separator + "MybatisGeneratorTest.java";
            String generatePropPath = testResPath + File.separator + "mybatisGenerator.properties";
            FileUtil.writeFile(generateCodePath, ResourcesTemplateUtil.getTemplateContent(null, MYBATIS_GENERATOR_CODE_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);

            Map<String, Object> mybatisGeneratorParams = new HashMap<String, Object>();
            mybatisGeneratorParams.put("mainPackage", projectInfo.getMainPackage());
            FileUtil.writeFile(generatePropPath, ResourcesTemplateUtil.getTemplateContent(mybatisGeneratorParams, MYBATIS_GENERATOR_PROP_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);
        }

        //javaCode package
        String packagePath = javaCodePath + File.separator + projectInfo.getMainPackage().replace(".", File.separator);
        String constantsPath = packagePath + File.separator + "constants";
        String daoPath = packagePath + File.separator + "dao";
        String entityPath = packagePath + File.separator + "entity";
        String enumsPath = packagePath + File.separator + "enums";
        String servicePath = packagePath + File.separator + "service";
        String exceptionPath = packagePath + File.separator + "exception";
        String utilPath = packagePath + File.separator + "util";
        String wsPath = packagePath + File.separator + "ws";
        FileUtil.mkdirs(constantsPath, daoPath, entityPath, enumsPath, servicePath, exceptionPath, utilPath, wsPath);

        //constants
        String moduleName = projectInfo.getName();
        String moduleCamelName = getCamelName(moduleName);
        String generatedTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        Map<String, Object> constantsParams = new HashMap<String, Object>();
        constantsParams.put("mainPackage", projectInfo.getMainPackage());
        constantsParams.put("generatedTime", generatedTime);
        constantsParams.put("moduleCamelName", moduleCamelName);
        FileUtil.writeFile(constantsPath + File.separator + moduleCamelName + "Constant.java", ResourcesTemplateUtil.getTemplateContent(constantsParams, CONSTANTS_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);

        //MsgEnum
        String module_SplitName = moduleName.replace("-", "_");
        Map<String, Object> msgEnumParams = new HashMap<String, Object>();
        msgEnumParams.put("mainPackage", projectInfo.getMainPackage());
        msgEnumParams.put("generatedTime", generatedTime);
        msgEnumParams.put("moduleCamelName", moduleCamelName);
        msgEnumParams.put("module_SplitName", module_SplitName);
        FileUtil.writeFile(enumsPath + File.separator + moduleCamelName + "MsgEnum.java", ResourcesTemplateUtil.getTemplateContent(msgEnumParams, MSG_ENUM_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);

        //exception
        Map<String, Object> exceptionParams = new HashMap<String, Object>();
        exceptionParams.put("mainPackage", projectInfo.getMainPackage());
        exceptionParams.put("generatedTime", generatedTime);
        exceptionParams.put("moduleCamelName", moduleCamelName);
        FileUtil.writeFile(exceptionPath + File.separator + moduleCamelName + "Exception.java", ResourcesTemplateUtil.getTemplateContent(exceptionParams, EXCEPTION_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);

        //生成测试代码
        generateTestCode(projectInfo.getMainPackage() + ".util", javaCodePath + File.separator + projectInfo.getMainPackage().replace(".", File.separator) + File.separator + "util");
        generateTestCode(projectInfo.getMainPackage() + ".ws", javaCodePath + File.separator + projectInfo.getMainPackage().replace(".", File.separator) + File.separator + "ws");

    }

    private String getCamelName(String moduleName) {
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

    private void generateTestCode(String packageName, String path) throws IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("package", packageName);
        String testPath = path + File.separator + "Test.java";
        FileUtil.writeFile(testPath, ResourcesTemplateUtil.getTemplateContent(params, TEST_JAVA_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);
    }
}
