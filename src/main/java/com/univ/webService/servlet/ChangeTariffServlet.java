package com.univ.webService.servlet;

import com.univ.webService.businessLogic.ChangeService;
import com.univ.webService.factory.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/change-user-tariff")
public class ChangeTariffServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            ((ChangeService) BeanFactory.getBean(ChangeService.class)).changeTariff(session, request);
        } catch (SQLException e) {
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }
}
