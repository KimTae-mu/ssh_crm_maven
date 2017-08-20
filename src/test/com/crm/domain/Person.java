package com.crm.domain;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Taeyeon-Serenity on 2017/8/20.
 */
public class Person {
    private String pname;

    //解决死循环的注解
    @JSONField(serialize = false)
    private Role role;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
