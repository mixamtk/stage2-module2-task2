package com.example.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        if ("user".equals(login) | "admin".equals(login)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", login);
            response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
