package org.hsq.wjg.demo.generator.pojo.ws;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjg on 2016/11/15.
 */
public class VoEntity {
    private String name;
    private String desc;
    private String packagePath;
    private List<FieldEntity> fields;

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

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public List<FieldEntity> getFields() {
        return fields;
    }

    public void setFields(List<FieldEntity> fields) {
        this.fields = fields;
    }

    public void addFiled(FieldEntity field) {
        if (null == fields) {
            fields = new ArrayList<FieldEntity>();
        }
        fields.add(field);
    }
}
