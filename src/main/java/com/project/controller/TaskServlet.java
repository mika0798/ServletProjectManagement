package com.project.controller;

import com.project.entity.Task;
import com.project.service.ProjectService;
import com.project.service.StatusService;
import com.project.service.TaskService;
import com.project.service.UserService;
import com.project.util.DateConverter;
import com.project.util.JSPUrlConst;
import com.project.util.UrlConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "taskServlet", urlPatterns = {UrlConst.CREATE_TASK,UrlConst.TASK_LIST})
public class TaskServlet extends HttpServlet {
    private static final long serialVersionUID = 219632887L;
    private TaskService taskService;
    private ProjectService projectService;
    private UserService userService;
    private StatusService statusService;

    @Override
    public void init() throws ServletException {
        super.init();
        taskService = new TaskService();
        projectService = new ProjectService();
        userService = new UserService();
        statusService = new StatusService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case UrlConst.CREATE_TASK:
                if (request.getParameter("id") != null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("task", taskService.getTaskById(id));
                }

                request.setAttribute("users", userService.getUserByRole(3));
                request.setAttribute("projects", projectService.getAllProjects());
                request.setAttribute("statuses", statusService.getAllStatus());
                request.getRequestDispatcher(JSPUrlConst.CREATE_TASK).forward(request, response);
                break;
            case UrlConst.TASK_LIST:
                if (request.getParameter("id") != null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    taskService.deleteTask(id);
                }

                request.setAttribute("tasks", taskService.getAllTasks());
                request.setAttribute("users", userService.getAllUsers());
                request.getRequestDispatcher(JSPUrlConst.TASK_LIST).forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        int assignee = Integer.parseInt(request.getParameter("assignee"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        int statusId = Integer.parseInt(request.getParameter("statusId"));

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setStart_date(DateConverter.convertStringToDate(startDate));
        task.setEnd_date(DateConverter.convertStringToDate(endDate));
        task.setAssignee(assignee);
        task.setProject_id(projectId);
        task.setStatus_id(statusId);

        // Add new task
        if (request.getParameter("id") == null) {
            if (taskService.addTask(task)) {
                request.setAttribute("message","Task added successfully.");
                request.setAttribute("alert","success");
            } else {
                request.setAttribute("message","Task addition failed.");
                request.setAttribute("alert","danger");
            }
        } else { // Update existing task
            int id = Integer.parseInt(request.getParameter("id"));
            task.setId(id);
            Task newTask = taskService.updateTask(task);
            if (newTask != null) {
                request.setAttribute("message","Task updated successfully.");
                request.setAttribute("alert","success");
                request.setAttribute("task",newTask);
            } else {
                request.setAttribute("message","Task update failed.");
                request.setAttribute("alert","danger");
                request.setAttribute("task",task);
            }
        }

        request.setAttribute("users", userService.getUserByRole(3));
        request.setAttribute("projects", projectService.getAllProjects());
        request.setAttribute("statuses", statusService.getAllStatus());
        request.getRequestDispatcher(JSPUrlConst.CREATE_TASK).forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
