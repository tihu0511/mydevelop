package org.hsq.wjg.demo.generator.enums;

/**
 * Created by wujigang on 2016/11/1.
 */
public enum ProjectStyleEnum {
    SIMPLE(1, "简单风格，单个工程"),
    SIMPLE_INTERFACE(2, "两个工程，其中一个为接口common"),
    COMPLETE(3, "完整风格(xxx-api,xxx-core,xxx-dao,xxx-service)"),
    ;
    private Integer code;
    private String desc;

    ProjectStyleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return this.desc;
    }
}
