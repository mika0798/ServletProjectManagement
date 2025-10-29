package com.project.controller;

import com.project.entity.Project;
import com.project.entity.User;
import com.project.service.ProjectService;
import com.project.service.UserService;
import com.project.util.DateConverter;
import com.project.util.JSPUrlConst;
import com.project.util.SessionUtil;
import com.project.util.UrlConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

@WebServlet(name = "projectServlet", urlPatterns = {UrlConst.CREATE_PROJECT,UrlConst.MANAGE_PROJECT})
public class ProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 919632887L;
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        super.init();
        projectService = new ProjectService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case UrlConst.CREATE_PROJECT:
                if (request.getParameter("id") != null) {
                    int projectId = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("project",projectService.getProjectById(projectId));
                }
                request.getRequestDispatcher(JSPUrlConst.CREATE_PROJECT).forward(request, response);
                break;
            case UrlConst.MANAGE_PROJECT:
                if (request.getParameter("id") != null) {
                    int projectId = Integer.parseInt(request.getParameter("id"));
                    projectService.deleteProject(projectId);
                }

                request.setAttribute("projects",projectService.getAllProjects());
                request.getRequestDispatcher(JSPUrlConst.MANAGE_PROJECT).forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String startDate = request.getParameter("start_date");
        String endDate = request.getParameter("end_date");

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStart_date(DateConverter.convertStringToDate(startDate));
        project.setEnd_date(DateConverter.convertStringToDate(endDate));

        // Add new project
        if (request.getParameter("id") == null) {
            User tempUser = (User) SessionUtil.getInstance().getValue(request, "login");
            int createdBy = tempUser.getId();
            project.setCreated_by(createdBy);

            if (projectService.addProject(project)) {
                request.setAttribute("message","Project added successfully.");
                request.setAttribute("alert","success");
            } else {
                request.setAttribute("message","Project addition failed.");
                request.setAttribute("alert","danger");
            }
        } else { // Update existing project
            int projectId = Integer.parseInt(request.getParameter("id"));
            int createdBy = projectService.getProjectCreator(projectId);
            project.setId(projectId);
            project.setCreated_by(createdBy);

            Project newProject = projectService.updateProject(project);
            if (newProject != null) {
                request.setAttribute("message","Project updated successfully.");
                request.setAttribute("alert","success");
                request.setAttribute("project",newProject);
            } else {
                request.setAttribute("message","Project update failed.");
                request.setAttribute("alert","danger");
                request.setAttribute("project",project);
            }
        }

        request.getRequestDispatcher(JSPUrlConst.CREATE_PROJECT).forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
