package com.project.repository;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;

import com.project.config.DatabaseConnection;
import com.project.entity.Status;
import com.project.util.QueriesConst;

public class StatusRepository {
    public List<Status> getAllStatus() {
        List<Status> statusList = new LinkedList<>();
        String query = QueriesConst.GET_STATUS;
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Status status = new Status();
                status.setId(rs.getInt("id"));
                status.setName(rs.getString("name"));
                status.setDescription(rs.getString("description"));

                statusList.add(status);
            }

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        return statusList;
    }
}
