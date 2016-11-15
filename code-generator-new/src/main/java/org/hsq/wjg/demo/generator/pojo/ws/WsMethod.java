package org.hsq.wjg.demo.generator.pojo.ws;

/**
 * Created by wjg on 2016/11/14.
 */
public class WsMethod {
    private String name; //接口方法名
    private String desc; //接口方法描述
    private DtoEntity inDto; //接口方法入参
    private DtoEntity outDto; //接口方法出参

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

    public DtoEntity getInDto() {
        return inDto;
    }

    public void setInDto(DtoEntity inDto) {
        this.inDto = inDto;
    }

    public DtoEntity getOutDto() {
        return outDto;
    }

    public void setOutDto(DtoEntity outDto) {
        this.outDto = outDto;
    }
}
