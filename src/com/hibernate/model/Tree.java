package com.hibernate.model;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/7.
 */
@Entity
public class Tree {
    private int id;
    private String name;
    private Set<Tree> childs = new HashSet<>();
    private Tree parent;

    @Id
    @GeneratedValue
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    public Set<Tree> getChilds() {
        return childs;
    }

    public void setChilds(Set<Tree> childs) {
        this.childs = childs;
    }

    @ManyToOne
    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }
}
