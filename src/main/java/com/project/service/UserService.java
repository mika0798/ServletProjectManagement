package com.project.service;

import com.project.entity.User;
import com.project.repository.ProjectRepository;
import com.project.repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public User checkLogin(String email, String password) {
        return userRepository.checkLogin(email, password);
    }

    public boolean isEmailTaken (String email) {
        return userRepository.isEmailTaken(email);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public List<User> getUserByRole(int roleId) {
        return userRepository.getUserByRole(roleId);
    }

    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    public User updateUser(User user) {
        return userRepository.updateUser(user) ? getUserById(user.getId()) : null;
    }

    public boolean deleteUser(int id) {
        ProjectRepository projectRepository = new ProjectRepository();
        projectRepository.deleteProjectCreatedBy(id);
        return userRepository.deleteUser(id);
    }
}
