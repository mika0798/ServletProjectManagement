package com.project.controller;

import com.project.entity.User;
import com.project.service.UserService;
import com.project.util.JSPUrlConst;
import com.project.util.PasswordUtil;
import com.project.util.SessionUtil;
import com.project.util.UrlConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "auth", urlPatterns = {
        UrlConst.SIGN_IN, UrlConst.SIGN_UP, UrlConst.SIGN_OUT
})
public class AuthenticationServlet extends HttpServlet {
    private static final long serialVersionUID = 519632487L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case UrlConst.SIGN_IN:
                request.getRequestDispatcher(JSPUrlConst.SIGN_IN).forward(request, response);
                break;
            case UrlConst.SIGN_UP:
                request.getRequestDispatcher(JSPUrlConst.SIGN_UP).forward(request, response);
                break;
            case UrlConst.SIGN_OUT:
                SessionUtil.getInstance().removeValue(request,"login");
                response.sendRedirect(request.getContextPath() + UrlConst.SIGN_IN);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String path = request.getServletPath();
        switch (path) {
            case UrlConst.SIGN_IN:
               User login = userService.checkLogin(email, password);
               if (login != null) {
                   String[] fullName = login.getName().split(" ");
                   String lastName = fullName[fullName.length - 1];

                   SessionUtil.getInstance().putValue(request, "lastName", lastName);
                   SessionUtil.getInstance().putValue(request, "login", login);
                   response.sendRedirect(request.getContextPath() + UrlConst.HOME);
               } else {
                   request.setAttribute("message","Sign in failed.");
                   request.setAttribute("alert","danger");
                   request.getRequestDispatcher(JSPUrlConst.SIGN_IN).forward(request, response);
               }
               break;
            case UrlConst.SIGN_UP:
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");

                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(PasswordUtil.encryptPassword(password));
                user.setPhone(phone);
                user.setAddress(address);
                user.setRole_id(3);

                if (userService.isEmailTaken(email)) {
                    request.setAttribute("message","Email is already taken.");
                    request.setAttribute("alert","danger");
                    request.getRequestDispatcher(JSPUrlConst.SIGN_UP).forward(request, response);
                } else {
                    if (userService.addUser(user)) {
                        request.setAttribute("message","Sign up successfully.");
                        request.setAttribute("alert","success");

                        String[] fullName = user.getName().split(" ");
                        String lastName = fullName[fullName.length - 1];

                        SessionUtil.getInstance().putValue(request, "login", user);
                        SessionUtil.getInstance().putValue(request, "lastName", lastName);
                        response.sendRedirect(request.getContextPath() + UrlConst.HOME);

                    } else {
                        request.setAttribute("message","Sign up failed.");
                        request.setAttribute("alert","danger");
                        request.getRequestDispatcher(JSPUrlConst.SIGN_UP).forward(request, response);
                    }
                }
                break;
        }
    }

}
