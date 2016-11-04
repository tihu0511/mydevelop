package org.hsq.wjg.demo.generator.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujigang on 2016/11/1.
 */
public class Dependency {
    private String groupId;
    private String artifactId;
    private String version;
    private List<Exclude> excludes;

    public Dependency(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Exclude> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<Exclude> excludes) {
        this.excludes = excludes;
    }

    public void addExcludes(Exclude ... es) {
        if (null == es || es.length <= 0) {
            return ;
        }
        if (null == excludes) {
            excludes = new ArrayList<Exclude>();
        }
        for (Exclude exclude : es) {
            excludes.add(exclude);
        }
    }
}
