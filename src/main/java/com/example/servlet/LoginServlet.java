package com.example.servlet;

import com.example.Users;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            if (httpSession.getAttribute("user") == null)
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            else {
                response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
            }
        } else
            response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean isUser = Users.getInstance().getUsers().stream().anyMatch(login::equals);
        if (isUser && password != null && !"".equals(password) ) {
            HttpSession httpSession = request.getSession(false);
            if (httpSession != null) {
                httpSession.setAttribute("user", login);
                response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
            } else
                request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
