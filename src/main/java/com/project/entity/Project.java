package com.project.entity;

import java.sql.Date;

public class Project {
    private int id;
    private String name;
    private String description;
    private Date start_date;
    private Date end_date;
    private int created_by;
    private User user;

    public Project(int id, String name, String description, Date start_date, Date end_date, int created_by, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.created_by = created_by;
        this.user = user;
    }

    public Project() {
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

    public Date getStart_date() {
        return this.start_date;
    }

    public Date getEnd_date() {
        return this.end_date;
    }

    public int getCreated_by() {
        return this.created_by;
    }

    public User getUser() {
        return this.user;
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

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
