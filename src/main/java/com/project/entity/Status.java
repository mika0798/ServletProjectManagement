package com.project.entity;

public class Status {
    private int id;
    private String name;
    private String description;

    public Status(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Status() {
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
