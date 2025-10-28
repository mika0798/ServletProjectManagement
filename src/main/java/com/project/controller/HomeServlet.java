package com.project.controller;

import com.project.util.JSPUrlConst;
import com.project.util.UrlConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "homeServlet", urlPatterns = UrlConst.HOME)
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 253698731L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher(JSPUrlConst.HOME).forward(request, response);
    }
}
