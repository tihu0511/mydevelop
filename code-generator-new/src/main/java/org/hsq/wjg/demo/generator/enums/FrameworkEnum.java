package org.hsq.wjg.demo.generator.enums;

/**
 * Created by wujigang on 2016/11/1.
 */
public enum FrameworkEnum {
    //依赖包的格式为 groupId:artifactId:version:excludes(groupId1|artifactId1#groupId2|artifactId2)
    SPRING_JAR(new String[]{
        "org.springframework:spring-context"
    }),
    SPRING_WEB(new String[]{
            "org.springframework:spring-context",
            "org.springframework:spring-web"
    }),
    SPRING_MVC(new String[]{
            "org.springframework:spring-context",
            "org.springframework:spring-webmvc"
    }),
    MYBATIES(new String[]{
            "org.springframework:spring-jdbc",
            "org.mybatis:mybatis",
            "org.mybatis:mybatis-spring",
            "mysql:mysql-connector-java",
            "com.mchange:c3p0",
            "org.apache.velocity:velocity:1.7"
    }),
    DUBBO(new String[]{"com.alibaba:dubbo::org.springframework|spring",
            "org.apache.zookeeper:zookeeper", "com.101tec:zkclient"}),
    HSQ_COMPONENT(new String[]{"com.hsq.component:dubbo-common:2.0-SNAPSHOT",
            "com.hsq.component:lang-common:2.0-SNAPSHOT",
            "com.hsq.component:spring-common:2.0-SNAPSHOT",
            "com.hsq.component:file-common:2.0-SNAPSHOT",
            "com.hsq.component:mybatis-generator:2.0-SNAPSHOT"
    }),
    LOG4J(new String[]{
            "org.slf4j:slf4j-log4j12",
            "log4j:log4j",
            "org.slf4j:slf4j-api"
    }),
    ;

    private String[] jars;
    FrameworkEnum(String[] jars) {
        this.jars = jars;
    }

    public String getName() {
        return this.name();
    }

    public String[] getJars() {
        return jars;
    }
}
