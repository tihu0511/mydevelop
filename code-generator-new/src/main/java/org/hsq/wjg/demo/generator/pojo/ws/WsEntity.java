package org.hsq.wjg.demo.generator.pojo.ws;

import java.util.List;

/**
 * Created by wjg on 2016/11/14.
 */
public class WsEntity {
    private String name; //接口名
    private String desc; //接口描述
    private String mainPackage; //主包名
    private List<WsMethod> wsMethods; //接口方法

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMainPackage() {
        return mainPackage;
    }

    public void setMainPackage(String mainPackage) {
        this.mainPackage = mainPackage;
    }

    public List<WsMethod> getWsMethods() {
        return wsMethods;
    }

    public void setWsMethods(List<WsMethod> wsMethods) {
        this.wsMethods = wsMethods;
    }
}
