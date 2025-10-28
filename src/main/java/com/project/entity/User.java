package com.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private int role_id;
    private Role role;

}
