package org.hsq.wjg.demo.generator.pojo;

/**
 * Created by wujigang on 2016/11/1.
 */
public class Exclude {
    private String groupId;
    private String artifactId;

    public Exclude(String groupId, String artifactId) {
        this.groupId = groupId;
        this.artifactId = artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
}
