package com.project.repository;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;

import com.project.util.QueriesConst;
import com.project.config.DatabaseConnection;
import com.project.entity.Project;
import com.project.entity.User;

public class ProjectRepository {
    public List<Project> getAllProjects() {
        List<Project> projects = new LinkedList<>();
        String query = QueriesConst.GET_PROJECTS;
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("p.id"));
                project.setName(rs.getString("p.name"));
                project.setDescription(rs.getString("p.description"));
                project.setStart_date(rs.getDate("p.start_date"));
                project.setEnd_date(rs.getDate("p.end_date"));
                project.setCreated_by(rs.getInt("p.created_by"));

                User user = new User();
                user.setId(rs.getInt("u.id"));
                user.setName(rs.getString("u.name"));
                user.setEmail(rs.getString("u.email"));
                user.setPhone(rs.getString("u.phone"));
                user.setAddress(rs.getString("u.address"));
                user.setRole_id(rs.getInt("u.role_id"));
                project.setUser(user);

                projects.add(project);
            }

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }

        return projects;
    }

    public Project getProjectById(int id) {
        String query = QueriesConst.GET_PROJECT_BY_ID;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("p.id"));
                project.setName(rs.getString("p.name"));
                project.setDescription(rs.getString("p.description"));
                project.setStart_date(rs.getDate("p.start_date"));
                project.setEnd_date(rs.getDate("p.end_date"));
                project.setCreated_by(rs.getInt("p.created_by"));

                User user = new User();
                user.setId(rs.getInt("u.id"));
                user.setName(rs.getString("u.name"));
                user.setEmail(rs.getString("u.email"));
                user.setPhone(rs.getString("u.phone"));
                user.setAddress(rs.getString("u.address"));
                user.setRole_id(rs.getInt("u.role_id"));
                project.setUser(user);

                return project;
            }
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return null;
    }

    public boolean addProject(Project project) {
        String query = QueriesConst.INSERT_PROJECT;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setDate(3, project.getStart_date());
            ps.setDate(4, project.getEnd_date());
            ps.setInt(5, project.getCreated_by());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return false;
    }

    public boolean updateProject(Project project) {
        String query = QueriesConst.UPDATE_PROJECT;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());
            ps.setDate(3, project.getStart_date());
            ps.setDate(4, project.getEnd_date());
            ps.setInt(5, project.getCreated_by());
            ps.setInt(6, project.getId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return false;
    }

    public boolean deleteProject(int id) {
        String query = QueriesConst.DELETE_PROJECT;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return false;
    }

    public boolean deleteProjectCreatedBy(int id) {
        String query = QueriesConst.DELETE_PROJECT_CREATED_BY;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return false;
    }
}
