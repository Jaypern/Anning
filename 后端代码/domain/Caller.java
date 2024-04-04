package com.springboot.springbootlogindemo.domain;

import javax.persistence.*;
@Table(name="caller")
@Entity
public class Caller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long uid;

    // 用户名属性varchar对应String
    private String uname;

    // 密码属性varchar对应String原本是密码属性，现在应该是文本属性
    private String text;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
