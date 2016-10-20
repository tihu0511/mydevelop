package org.hsq.wjg.demo.temp;

import java.util.List;

/**
 * Created by wujigang on 2016/10/17.
 */
public class Province {
    private String code;
    private String name;
    private List<City> cities;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
