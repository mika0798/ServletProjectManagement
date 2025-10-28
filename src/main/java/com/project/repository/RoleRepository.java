package com.project.repository;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;

import com.project.config.DatabaseConnection;
import com.project.entity.Role;
import com.project.util.QueriesConst;

public class RoleRepository {
    public List<Role> getAllRole() {
        List<Role> roleList = new LinkedList<>();
        String query = QueriesConst.GET_ROLES;
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("description"));

                roleList.add(role);
            }

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return roleList;
    }
}
