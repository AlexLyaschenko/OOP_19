package com.univ.webService.servlet;

import com.univ.webService.businessLogic.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/main_page")
public class HandlerServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("sessionId") == null) session.setAttribute("sessionId", Constants.LOGIN_ACCOUNT);
        if (request.getParameter("sessionId") != null)
            session.setAttribute("sessionId", request.getParameter("sessionId"));

        switch (session.getAttribute("sessionId").toString()) {
            case Constants.LOGIN_ACCOUNT:
                Service.loginAccount(session, request);
                break;
            case Constants.CHANGE_USER_STATUS:
                Service.changeUserStatus(session, request);
                break;
            case Constants.CHANGE_TARIFF:
                Service.changeTariff(session, request);
                break;
            case Constants.ADD_TO_DB_BY_REGION:
                Service.addToPeopleInRegionDB(session, request);
                break;
            case Constants.ADD_TO_DB_BY_TARIFF:
                Service.addToPeopleInTariffDB(session, request);
                break;
        }
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("sessionId") == null) session.setAttribute("sessionId", Constants.LOGIN_ACCOUNT);
        if (request.getParameter("sessionId") != null)
            session.setAttribute("sessionId", request.getParameter("sessionId"));

        switch (session.getAttribute("sessionId").toString()) {
            case Constants.SHOW_USERS:
                Service.showUserInfo(session, request);
                break;
            case Constants.SHOW_PEOPLE_IN_REGION:
                Service.showPeopleInRegion(request);
                break;
            case Constants.SHOW_PEOPLE_IN_TARIFF:
                Service.showPeopleInTariff(request);
                break;
            case Constants.SHOW_HISTORY:
                Service.showHistoryByWeek(request);
                break;
        }
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }

}
