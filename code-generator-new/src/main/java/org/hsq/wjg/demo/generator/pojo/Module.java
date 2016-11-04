package org.hsq.wjg.demo.generator.pojo;

/**
 * Created by wujigang on 2016/11/1.
 */
public class Module extends Dependency {
    private String mavenType;

    public Module(String groupId, String artifactId, String version, String mavenType) {
        super(groupId, artifactId, version);
        this.mavenType = mavenType;
    }

    public String getMavenType() {
        return mavenType;
    }

    public void setMavenType(String mavenType) {
        this.mavenType = mavenType;
    }
}
