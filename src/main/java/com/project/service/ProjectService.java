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

    public int getProjectCreator(int id) {return projectRepository.getProjectCreator(id);}

    public boolean addProject(Project project) {
        return projectRepository.addProject(project);
    }

    public Project updateProject(Project project) {
        return projectRepository.updateProject(project) ? getProjectById(project.getId()) : null;
    }

    public boolean deleteProject(int id) {
        return projectRepository.deleteProject(id);
    }
}
