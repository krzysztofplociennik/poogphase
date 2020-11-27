package com.plociennik.poogphase.model;

import javax.persistence.*;

@Entity
public class DummyModel {
    private long id;
    private String name;
    private int age;

    public DummyModel(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public DummyModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
