package com.project.controller;

import com.project.entity.User;
import com.project.service.RoleService;
import com.project.service.UserService;
import com.project.util.JSPUrlConst;
import com.project.util.PasswordUtil;
import com.project.util.UrlConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userServlet", urlPatterns = {UrlConst.CREATE_USER, UrlConst.USER_LIST})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 519632887L;
    private UserService userService;
    private RoleService roleService;

    @Override
    public void init()  throws ServletException {
        super.init();
        userService = new UserService();
        roleService = new RoleService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case UrlConst.CREATE_USER:
                if (request.getParameter("id") != null) {
                    int userId = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("user",userService.getUserById(userId));
                }
                request.setAttribute("roles",roleService.getAllRole());
                request.getRequestDispatcher(JSPUrlConst.CREATE_USER).forward(request, response);
                break;
            case UrlConst.USER_LIST:
                if (request.getParameter("id") != null) {
                    int userId = Integer.parseInt(request.getParameter("id"));
                    userService.deleteUser(userId);
                }
                request.setAttribute("users",userService.getAllUsers());
                request.getRequestDispatcher(JSPUrlConst.USER_LIST).forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int roleId;
        if (request.getParameter("id") != null) {
            roleId = Integer.parseInt(request.getParameter("roleId"));
        } else {
            roleId = 3;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(PasswordUtil.encryptPassword(password));
        user.setAddress(address);
        user.setPhone(phone);
        user.setRole_id(roleId);

        // Create new user
        if (request.getParameter("id") == null) {
            if (userService.isEmailTaken(email)) {
                request.setAttribute("message","Email is already taken.");
                request.setAttribute("alert","danger");
            } else {
                if (userService.addUser(user)) {
                    request.setAttribute("message","User created successfully.");
                    request.setAttribute("alert","success");

                    request.setAttribute("users",userService.getAllUsers());
                    request.getRequestDispatcher(JSPUrlConst.USER_LIST).forward(request, response);
                } else {
                    request.setAttribute("message","User creation failed.");
                    request.setAttribute("alert","danger");
                }
            }
        } else {
            // Update existing user
            int userId = Integer.parseInt(request.getParameter("id"));
            user.setId(userId);
            User newUser = userService.updateUser(user);

            if (newUser != null) {
                request.setAttribute("message","User updated successfully.");
                request.setAttribute("alert","success");
                request.setAttribute("user",newUser);
            } else {
                request.setAttribute("message","User update failed.");
                request.setAttribute("alert","danger");
                request.setAttribute("user",user);
            }

        }
        request.setAttribute("roles",roleService.getAllRole());
        request.getRequestDispatcher(JSPUrlConst.CREATE_USER).forward(request, response);

    }

}
