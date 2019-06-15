package com.echomu.seeklevelbardemo;

/**
 * author : echoMu
 * date   : 2019/6/14
 * desc   :
 */
public class BaseLevel {
    private int color;
    private String value;
    private String name;


    public BaseLevel(int color, String value,String name) {
        this.color = color;
        this.value = value;
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
