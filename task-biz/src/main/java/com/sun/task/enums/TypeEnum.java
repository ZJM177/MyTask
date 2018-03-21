package com.sun.task.enums;

/**
 * Created by pengjikun on 2018/3/21.
 */
public enum TypeEnum {
    AVENE(1, "雅漾"),
    RF(2, "馥绿德雅");

    private int type;
    private String desc;


    TypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
