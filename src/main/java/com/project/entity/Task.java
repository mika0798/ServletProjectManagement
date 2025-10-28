package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}
