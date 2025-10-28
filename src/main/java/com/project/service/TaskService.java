package com.project.service;

import com.project.entity.Project;
import com.project.entity.Task;
import com.project.repository.TaskRepository;

import java.util.List;

public class TaskService {
    private TaskRepository taskRepository;

    public TaskService() {
        taskRepository = new TaskRepository();
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Task getTaskById(int id) {
        return taskRepository.getTaskById(id);
    }

    public boolean addTask(Task task) {
        ProjectService projectService = new ProjectService();
        Project project = projectService.getProjectById(task.getProject_id());

        if (project != null) {
            if (task.getStart_date().after(project.getEnd_date())
            || task.getStart_date().before(project.getStart_date())
            || task.getEnd_date().before(project.getStart_date())
            || task.getEnd_date().after(project.getEnd_date())) {
                return false;
            }
        }
        return taskRepository.addTask(task);
    }

    public boolean updateTask(Task task) {
        ProjectService projectService = new ProjectService();
        Project project = projectService.getProjectById(task.getProject_id());

        if (project != null) {
            if (task.getStart_date().after(project.getEnd_date())
                    || task.getStart_date().before(project.getStart_date())
                    || task.getEnd_date().before(project.getStart_date())
                    || task.getEnd_date().after(project.getEnd_date())) {
                return false;
            }
        }
        return taskRepository.updateTask(task);
    }

    public boolean deleteTask(int id) {
        return taskRepository.deleteTask(id);
    }
}
