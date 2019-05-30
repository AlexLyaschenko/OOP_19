package com.univ.webService.servlet;

import com.univ.webService.businessLogic.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("sessionId") == null) session.setAttribute("sessionId", Constants.LOGIN_ACCOUNT);
        if (request.getParameter("sessionId") != null)
            session.setAttribute("sessionId", request.getParameter("sessionId"));
        try {
            Service.loginAccount(session, request);
        } catch (SQLException e) {
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("response.jsp").forward(request, response);

    }
}
