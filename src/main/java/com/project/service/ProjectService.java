package com.project.service;

import com.project.entity.Project;
import com.project.repository.ProjectRepository;

import java.util.List;

public class ProjectService {
    private ProjectRepository projectRepository;
    public ProjectService() {
        projectRepository = new ProjectRepository();
    }

    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    public Project getProjectById(int id) {
        return projectRepository.getProjectById(id);
    }

    public boolean addProject(Project project) {
        return projectRepository.addProject(project);
    }

    public boolean updateProject(Project project) {
        return projectRepository.updateProject(project);
    }

    public boolean deleteProject(int id) {
        return projectRepository.deleteProject(id);
    }
}
