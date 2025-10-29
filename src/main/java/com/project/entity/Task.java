package com.project.entity;

import java.sql.Date;

public class Task {
    private int id;
    private String name;
    private String description;
    private Date start_date;
    private Date end_date;
    private int assignee;
    private int project_id;
    private int status_id;
    private User user;
    private Project project;
    private Status status;

    public Task(int id, String name, String description, Date start_date, Date end_date, int assignee, int project_id, int status_id, User user, Project project, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.assignee = assignee;
        this.project_id = project_id;
        this.status_id = status_id;
        this.user = user;
        this.project = project;
        this.status = status;
    }

    public Task() {
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

    public int getAssignee() {
        return this.assignee;
    }

    public int getProject_id() {
        return this.project_id;
    }

    public int getStatus_id() {
        return this.status_id;
    }

    public User getUser() {
        return this.user;
    }

    public Project getProject() {
        return this.project;
    }

    public Status getStatus() {
        return this.status;
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

    public void setAssignee(int assignee) {
        this.assignee = assignee;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
