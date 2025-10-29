package com.project.filter;

import com.project.entity.User;
import com.project.util.SessionUtil;
import com.project.util.UrlConst;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        UrlConst.CREATE_PROJECT, UrlConst.MANAGE_PROJECT,
        UrlConst.CREATE_USER, UrlConst.USER_LIST,
        UrlConst.HOME,UrlConst.SIGN_IN,UrlConst.SIGN_UP
})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String url = request.getRequestURI();
        User user = ((User) SessionUtil.getInstance().getValue(request, "login"));

        // Accessing Project and User Management
        if (url.contains(UrlConst.MANAGE_PROJECT) || url.contains(UrlConst.CREATE_PROJECT)
            || url.contains(UrlConst.USER_LIST) || url.contains(UrlConst.CREATE_USER)) {
            // If logged in
            if (user != null) {
                // All types of user allowed to edit their profiles
                if (url.startsWith(request.getContextPath() + UrlConst.CREATE_USER)
                    && request.getQueryString() != null) {
                    chain.doFilter(request, response);
                } else if (user.getRole().getName().equalsIgnoreCase("MEMBER")) {
                    // Members not allowed to access Project Management and Total User Management
                    // Redirect to Home if try accessing
                    response.sendRedirect(request.getContextPath() + UrlConst.HOME);
                } else {
                    // Admin and Leader have full access
                    chain.doFilter(request, response);
                }

            } else {
                // Redirect to Sign In if not logged in and try accessing other pages
                response.sendRedirect(request.getContextPath() + UrlConst.SIGN_IN);
            }
        } else { // Accessing Home, Sign In and Sign Up
            if (user != null) { // Proceed to any of the above if already logged in
                chain.doFilter(request, response);
            } else if (request.getServletPath().equals(UrlConst.SIGN_IN)
                    || request.getServletPath().equals(UrlConst.SIGN_UP)) {
                chain.doFilter(request, response);
                // Allow access to Sign In and Sign Up if not logged in
            } else {
                // Redirect to Sign In if not logged in and try accessing Home page
                response.sendRedirect(request.getContextPath() + UrlConst.SIGN_IN);
            }
        }
    }

    @Override
    public void destroy() {}
}
