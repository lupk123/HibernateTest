package com.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2016/8/2.
 */
@Entity
public class Teacher {
    private int id;
    private String name;
    private int level;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
