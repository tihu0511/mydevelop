package org.hsq.wjg.demo.generator.pojo;

import org.hsq.wjg.demo.generator.enums.FrameworkEnum;
import org.hsq.wjg.demo.generator.enums.ProjectStyleEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujigang on 2016/11/1.
 */
public class ProjectInfo {
    private String name; //项目名称
    private ProjectStyleEnum style; //1-简单风格(一个module) 2-接口风格(两个module，其中一个为common接口module) 3-完整风格(xxx-api,xxx-core,xxx-dao,xxx-service)
    private String mainPackage; //主包名
    private String groupId;
    private List<FrameworkEnum> frameworks; //使用框架

    private String directory; //生成项目路径

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectStyleEnum getStyle() {
        return style;
    }

    public void setStyle(ProjectStyleEnum style) {
        this.style = style;
    }

    public String getMainPackage() {
        return mainPackage;
    }

    public void setMainPackage(String mainPackage) {
        this.mainPackage = mainPackage;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public List<FrameworkEnum> getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(List<FrameworkEnum> frameworks) {
        this.frameworks = frameworks;
    }

    public void addFrameworks(FrameworkEnum ... frames) {
        if (null == frames || frames.length <= 0) {
            return;
        }
        if (null == frameworks) {
            frameworks = new ArrayList<FrameworkEnum>();
        }
        for (FrameworkEnum framework : frames) {
            frameworks.add(framework);
        }
    }
}
