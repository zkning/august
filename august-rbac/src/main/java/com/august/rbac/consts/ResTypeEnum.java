package com.august.rbac.consts;

/**
 * 资源类型
 * Created by ningzuokun on 2018/3/21.
 */
public enum ResTypeEnum {
    MENU(1, "菜单"),
    SERV(2, "服务");

    private Integer code;
    private String value;

    ResTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static ResTypeEnum getInstance(Integer code) {
        for (ResTypeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
