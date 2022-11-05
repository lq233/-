package com.example.lq.myapplication.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 003 on 2019/5/9.
 */
@Entity
public class DbBean {
    @Id(autoincrement = true)
    Long id;
    @Property
    String author;
    @Property
    String type;
    @Property
    String title;
    @Property
    String time;
    @Generated(hash = 2128776610)
    public DbBean(Long id, String author, String type, String title, String time) {
        this.id = id;
        this.author = author;
        this.type = type;
        this.title = title;
        this.time = time;
    }
    @Generated(hash = 1953169116)
    public DbBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    

}
