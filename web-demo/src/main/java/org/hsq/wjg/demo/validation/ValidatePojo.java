package org.hsq.wjg.demo.validation;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by wjg on 2016/11/22.
 */
public class ValidatePojo {
    @NotEmpty(message = "名称不能为空")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
