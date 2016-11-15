package org.hsq.wjg.demo.generator.pojo.ws;

/**
 * Created by wjg on 2016/11/14.
 */
public class FieldEntity {
    private String name;
    private String type;
    private String desc;
    private boolean isMust;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean getIsMust() {
        return isMust;
    }

    public void setIsMust(boolean isMust) {
        this.isMust = isMust;
    }
}
