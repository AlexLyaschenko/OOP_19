package com.univ.webService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/statusAbonent")
public class AbonentStatusServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("status", request.getParameter("stAbonent"));
        request.setAttribute("billingId", request.getParameter("billingId"));
        String status = request.getAttribute("status").equals("disabled") ? "enabled" : "disabled";
        BillingDAO billingDAO = new BillingDAO();
        billingDAO.updateBillingDB(Integer.parseInt((String)request.getAttribute("billingId")), status);
        request.setAttribute("sessionId", 102);
        request.setAttribute("type", "Admin");
        HttpSession session = request.getSession();
        session.setAttribute("testSession", "It works");
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }
}
