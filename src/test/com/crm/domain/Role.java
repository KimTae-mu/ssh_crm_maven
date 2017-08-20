package com.crm.domain;

/**
 * Created by Taeyeon-Serenity on 2017/8/20.
 */
public class Role {
    private String rname;
    private Person person;

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
