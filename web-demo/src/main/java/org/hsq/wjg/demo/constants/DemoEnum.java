package org.hsq.wjg.demo.constants;

/**
 * Created by wujigang on 2016/10/11.
 */
public enum DemoEnum {
    TEST_SECRET("123456"),
    ;

    private String value;
    DemoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
