package com.project.entity;

import java.sql.Date;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private int id;
    private String name;
    private String description;
    private Date start_date;
    private Date end_date;
    private int created_by;
    private User user;
}
