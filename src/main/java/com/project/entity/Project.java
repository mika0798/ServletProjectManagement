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
public class Project {
    private int id;
    private String name;
    private String description;
    private Date start_date;
    private Date end_date;
    private int created_by;
    private User user;

}
