package com.project.repository;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;

import com.project.config.DatabaseConnection;
import com.project.entity.Project;
import com.project.entity.Status;
import com.project.entity.Task;
import com.project.entity.User;
import com.project.util.QueriesConst;

public class TaskRepository {
    public List<Task> getAllTasks() {
        List<Task> tasks = new LinkedList<>();
        String query = QueriesConst.GET_TASKS;
        try (Connection con = DatabaseConnection.getConnection();
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery(query)) {
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("t.id"));
                task.setName(rs.getString("t.name"));
                task.setDescription(rs.getString("t.description"));
                task.setStart_date(rs.getDate("t.start_date"));
                task.setEnd_date(rs.getDate("t.end_date"));
                task.setAssignee(rs.getInt("t.assignee"));
                task.setProject_id(rs.getInt("t.project_id"));
                task.setStatus_id(rs.getInt("t.status_id"));

                User user = new User();
                user.setName(rs.getString("u.name"));
                user.setEmail(rs.getString("u.email"));
                user.setPhone(rs.getString("u.phone"));
                user.setAddress(rs.getString("u.address"));
                user.setRole_id(rs.getInt("u.role_id"));

                Project project = new Project();
                project.setName(rs.getString("p.name"));
                project.setDescription(rs.getString("p.description"));
                project.setStart_date(rs.getDate("p.start_date"));
                project.setEnd_date(rs.getDate("p.end_date"));
                project.setCreated_by(rs.getInt("p.created_by"));

                Status status = new Status();
                status.setName(rs.getString("s.name"));
                status.setDescription(rs.getString("s.description"));

                task.setUser(user);
                task.setProject(project);
                task.setStatus(status);
                tasks.add(task);
            }

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return tasks;
    }

    public Task getTaskById(int id) {
        String query = QueriesConst.GET_TASK_BY_ID;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("t.id"));
                task.setName(rs.getString("t.name"));
                task.setDescription(rs.getString("t.description"));
                task.setStart_date(rs.getDate("t.start_date"));
                task.setEnd_date(rs.getDate("t.end_date"));
                task.setAssignee(rs.getInt("t.assignee"));
                task.setProject_id(rs.getInt("t.project_id"));
                task.setStatus_id(rs.getInt("t.status_id"));

                User user = new User();
                user.setName(rs.getString("u.name"));
                user.setEmail(rs.getString("u.email"));
                user.setPhone(rs.getString("u.phone"));
                user.setAddress(rs.getString("u.address"));
                user.setRole_id(rs.getInt("u.role_id"));

                Project project = new Project();
                project.setName(rs.getString("p.name"));
                project.setDescription(rs.getString("p.description"));
                project.setStart_date(rs.getDate("p.start_date"));
                project.setEnd_date(rs.getDate("p.end_date"));
                project.setCreated_by(rs.getInt("p.created_by"));

                Status status = new Status();
                status.setName(rs.getString("s.name"));
                status.setDescription(rs.getString("s.description"));

                task.setUser(user);
                task.setProject(project);
                task.setStatus(status);

                return task;

            }
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return null;
    }

    public boolean addTask(Task task) {
        String query = QueriesConst.INSERT_TASK;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setDate(3, task.getStart_date());
            ps.setDate(4, task.getEnd_date());
            ps.setInt(5, task.getAssignee());
            ps.setInt(6, task.getProject_id());
            ps.setInt(7, task.getStatus_id());

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return false;
    }

    public boolean updateTask(Task task) {
        String query = QueriesConst.UPDATE_TASK;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setDate(3, task.getStart_date());
            ps.setDate(4, task.getEnd_date());
            ps.setInt(5, task.getAssignee());
            ps.setInt(6, task.getProject_id());
            ps.setInt(7, task.getStatus_id());
            ps.setInt(8, task.getId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return false;
    }

    public boolean deleteTask(int id) {
        String query = QueriesConst.DELETE_TASK;
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
