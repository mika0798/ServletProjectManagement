package com.project.repository;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;

import com.project.util.PasswordUtil;
import com.project.util.QueriesConst;
import com.project.config.DatabaseConnection;
import com.project.entity.User;
import com.project.entity.Role;

public class UserRepository {
    public User checkLogin(String email, String password) {
        String query = QueriesConst.CHECK_LOGIN;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hash = rs.getString("u.password");
                if (PasswordUtil.checkPassword(password, hash)) {
                    User user = new User();
                    user.setId(rs.getInt("u.id"));
                    user.setName(rs.getString("u.name"));
                    user.setEmail(rs.getString("u.email"));
                    user.setPhone(rs.getString("u.phone"));
                    user.setAddress(rs.getString("u.address"));
                    user.setRole_id(rs.getInt("u.role_id"));

                    Role role = new Role();
                    role.setId(rs.getInt("u.role_id"));
                    role.setName(rs.getString("r.name"));
                    role.setDescription(rs.getString("r.description"));
                    user.setRole(role);

                    return user;
                }
            }

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return null;
    }

    public boolean isEmailTaken (String email) {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return false;
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        String query = QueriesConst.GET_USERS;
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("u.id"));
                user.setName(rs.getString("u.name"));
                user.setEmail(rs.getString("u.email"));
                user.setPhone(rs.getString("u.phone"));
                user.setAddress(rs.getString("u.address"));
                user.setRole_id(rs.getInt("u.role_id"));

                Role role = new Role();
                role.setId(rs.getInt("u.role_id"));
                role.setName(rs.getString("r.name"));
                role.setDescription(rs.getString("r.description"));
                user.setRole(role);

                users.add(user);
            }

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }

        return users;
    }

    public User getUserById(int id) {
        String query = QueriesConst.GET_USER_BY_ID;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("u.id"));
                user.setName(rs.getString("u.name"));
                user.setEmail(rs.getString("u.email"));
                user.setPhone(rs.getString("u.phone"));
                user.setAddress(rs.getString("u.address"));
                user.setRole_id(rs.getInt("u.role_id"));

                Role role = new Role();
                role.setId(rs.getInt("u.role_id"));
                role.setName(rs.getString("r.name"));
                role.setDescription(rs.getString("r.description"));
                user.setRole(role);

                return user;
            }

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return null;
    }

    public List<User> getUserByRole(int roleId) {
        List<User> users = new LinkedList<>();
        String query = QueriesConst.GET_USER_BY_ROLE;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("u.id"));
                user.setName(rs.getString("u.name"));
                user.setEmail(rs.getString("u.email"));
                user.setPhone(rs.getString("u.phone"));
                user.setAddress(rs.getString("u.address"));
                user.setRole_id(rs.getInt("u.role_id"));

                Role role = new Role();
                role.setId(rs.getInt("u.role_id"));
                role.setName(rs.getString("r.name"));
                role.setDescription(rs.getString("r.description"));
                user.setRole(role);

                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }

        return users;
    }

    public boolean addUser(User user) {
        String query = QueriesConst.INSERT_USER;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, PasswordUtil.encryptPassword(user.getPassword()));
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setInt(6, user.getRole_id());

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        return false;
    }

    public boolean updateUser(User user) {
        String query = QueriesConst.UPDATE_USER;
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, PasswordUtil.encryptPassword(user.getPassword()));
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setInt(6, user.getRole_id());
            ps.setInt(7, user.getId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }

        return false;
    }

    public boolean deleteUser(int id) {
        String query = QueriesConst.DELETE_USER;
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
