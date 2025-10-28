package com.project.service;

import com.project.entity.Role;
import com.project.repository.RoleRepository;

import java.util.List;

public class RoleService {
    private RoleRepository roleRepository;

    public RoleService() {
        roleRepository = new RoleRepository();
    }

    public List<Role> getAllRole() {
        return roleRepository.getAllRole();
    }
}
