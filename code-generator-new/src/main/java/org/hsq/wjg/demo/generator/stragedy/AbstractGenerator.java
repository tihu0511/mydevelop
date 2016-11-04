package org.hsq.wjg.demo.generator.stragedy;

import com.hsq.component.file.FileUtil;
import org.hsq.wjg.demo.generator.enums.FrameworkEnum;
import org.hsq.wjg.demo.generator.pojo.Dependency;
import org.hsq.wjg.demo.generator.pojo.Exclude;
import org.hsq.wjg.demo.generator.pojo.Pom;
import org.hsq.wjg.demo.generator.pojo.ProjectInfo;
import org.hsq.wjg.demo.generator.constants.GeneratorConstants;
import org.hsq.wjg.demo.generator.util.ResourcesTemplateUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wujigang on 2016/11/1.
 */
public abstract class AbstractGenerator {
    protected static final String POM_TPL_PATH = "/tpl/pom.vm";
    protected static final String SPRING_TPL_PATH = "/tpl/spring.vm";
    protected static final String SPRING_DB_TPL_PATH = "/tpl/spring-db.vm";
    protected static final String DUBBO_TPL_PATH = "/tpl/dubbo.vm";
    protected static final String DUBBO_SERVICE_TPL_PATH = "/tpl/dubbo-service.vm";
    protected static final String DUBBO_REFERENCE_TPL_PATH = "/tpl/dubbo-reference.vm";
    protected static final String DUBBO_LOG_TPL_PATH = "/tpl/logDubboConf.vm";
    protected static final String APP_PROP_TPL_PATH = "/tpl/app.vm";
    protected static final String APP_PROD_PROP_TPL_PATH = "/tpl/app-prod.vm";
    protected static final String DB_PROP_TPL_PATH = "/tpl/db-properties.vm";
    protected static final String LOG4J_TPL_PATH = "/tpl/log4j.vm";
    protected static final String WEB_XML_TPL_PATH = "/tpl/web.vm";
    protected static final String INDEX_JSP_TPL_PATH = "/tpl/index-jsp.vm";
    protected static final String MYBATIS_GENERATOR_CODE_TPL_PATH = "/tpl/mybatis-generator.vm";
    protected static final String MYBATIS_GENERATOR_PROP_TPL_PATH = "/tpl/mybatis-generator-prop.vm";
    protected static final String CONSTANTS_TPL_PATH = "/tpl/constants.vm";
    protected static final String MSG_ENUM_TPL_PATH = "/tpl/msgEnum.vm";
    protected static final String EXCEPTION_TPL_PATH = "/tpl/exception.vm";
    protected static final String TEST_JAVA_TPL_PATH = "/tpl/test-java.vm";

    protected ProjectInfo projectInfo;

    protected String javaCodePath; //   src/main/java
    protected String resourcesPath; //   src/main/resources
    protected String testCodePath; //   src/test/java
    protected String testResPath; //   src/test/resources
    protected String webappPath; //     src/main/webapp

    protected String rootName;
    protected String modulePath;
    protected String mainPackagePath;


    /**
     * 生成最外层pom.xml
     */
    protected void generatePom(String pomFile, Pom pom) throws IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pom", pom);
        String content = ResourcesTemplateUtil.getTemplateContent(params, POM_TPL_PATH);

        FileUtil.writeFile(pomFile, content, GeneratorConstants.DEFAULT_CHARSET);
    }

    /**
     * 生成module结构
     * @param moduleName
     * @param type 1-只有src/main/java/
     *             2-jar工程
     *             3-web工程
     */
    protected void generateModuleStruct(String moduleName, int type) {
        //生成项目结构
        String projectDir = projectInfo.getDirectory();
        modulePath = projectDir.endsWith(File.separator) ? projectDir + moduleName : projectDir + File.separator + moduleName;
        javaCodePath = modulePath + File.separator + "src" + File.separator + "main" + File.separator + "java";
        if (type == 1) {
            FileUtil.mkdirs(javaCodePath);
        } else {
            resourcesPath = modulePath + File.separator + "src" + File.separator + "main" + File.separator + "resources";
            testCodePath = modulePath + File.separator + "src" + File.separator + "test" + File.separator + "java";
            testResPath = modulePath + File.separator + "src" + File.separator + "test" + File.separator + "resources";

            if (type == 2) {
                FileUtil.mkdirs(javaCodePath, resourcesPath, testCodePath, testResPath);
            }
            else if (type == 3) {
                webappPath = modulePath + File.separator + "src" + File.separator + "main" + File.separator + "webapp";
                FileUtil.mkdirs(javaCodePath, resourcesPath, testCodePath, testResPath, webappPath + File.separator + "WEB-INF");
            }

        }
    }

    /**
     * 解析pom依赖框架
     * @param frameworkEnums
     * @return
     */
    protected List<Dependency> resovleDependencies(List<FrameworkEnum> frameworkEnums) {
        List<Dependency> dependencies = null;
        if (null != frameworkEnums && frameworkEnums.size() > 0) {
            dependencies = new ArrayList<Dependency>();
            for (FrameworkEnum framework : frameworkEnums) {
                String[] jars = framework.getJars();
                for (String jar : jars) {
                    String[] jarInfo = jar.split(":");
                    String version = jarInfo.length >= 3 && jarInfo[2].length() > 0 ? jarInfo[2] : null;
                    Dependency dependency = new Dependency(jarInfo[0], jarInfo[1], version);
                    if (jarInfo.length >= 4 && jarInfo[3].length() > 0) {
                        String excludeStr = jarInfo[3];
                        String[] excludes = excludeStr.split("#");
                        for (String exclude : excludes) {
                            String[] excludeInfo = exclude.split("\\|");
                            dependency.addExcludes(new Exclude(excludeInfo[0], excludeInfo[1]));
                        }
                    }
                    dependencies.add(dependency);
                }
            }
        }
        return dependencies;
    }
}
