package com.example.lq.myapplication.bean;

/**
 * Created by 003 on 2019/5/10.
 */

public class Bean {
    private String name;
    private String iconurl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public Bean(String name, String iconurl) {
        this.name = name;
        this.iconurl = iconurl;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", iconurl='" + iconurl + '\'' +
                '}';
    }
}
