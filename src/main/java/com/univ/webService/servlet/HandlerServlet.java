package com.univ.webService.servlet;

import com.univ.webService.DAO.AbonentDAO;
import com.univ.webService.DAO.AreaDAO;
import com.univ.webService.DAO.BillingDAO;
import com.univ.webService.DAO.TariffDAO;
import com.univ.webService.dataModel.Abonent;
import com.univ.webService.dataModel.Area;
import com.univ.webService.dataModel.Billing;
import com.univ.webService.dataModel.Tariff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet("/hello")
public class HandlerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("sessionId") == null) session.setAttribute("sessionId", "2");
        if (request.getParameter("sessionId") != null)
            session.setAttribute("sessionId", request.getParameter("sessionId"));
        switch (session.getAttribute("sessionId").toString()) {
            case Constants.LOGIN_ACCOUNT:
                loginAccount(session, request);
                break;
            case Constants.SHOW_USERS:
                showUserInfo(session, request);
                break;
            case Constants.CHANGE_USER_STATUS:
                changeUserStatus(session, request);
                break;
            case Constants.CHANGE_TARIFF:
                changeTariff(session, request);
                break;
        }
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }

    private void loginAccount(HttpSession session, HttpServletRequest request) {
        AbonentDAO abonentDAO = new AbonentDAO();
        String pass;
        String login;
        try {
            pass = request.getParameter("pass").equals("") ? "," : request.getParameter("pass");
            login = request.getParameter("login");
        } catch (Exception e) {
            pass = session.getAttribute("pass").toString();
            login = session.getAttribute("login").toString();
        }
        session.setAttribute("pass", pass);
        session.setAttribute("login", login);
        ArrayList<Abonent> abonentArr = abonentDAO.getAbonentFromDB(-1, "", "", "", -1, -1, login, pass, -1);
        if (abonentArr.size() == 1) {
            Abonent abonent = abonentArr.get(0);
            request.setAttribute("name", abonent.getName());
            request.setAttribute("surname", abonent.getSurname());
            request.setAttribute("number", abonent.getPhoneNumber());
            if (abonent.getIsAdmin() == 0) {
                session.setAttribute("sessionId", Constants.ID_USER);
                session.setAttribute("type", "Abonent");
                session.setAttribute("billingId", abonent.getIdBilling());
                session.setAttribute("areaCode", abonent.getIdAreaCode());
                showAvailablePackages(session, request);
            } else {
                session.setAttribute("sessionId", Constants.ID_ADMIN);
                session.setAttribute("type", "Admin");
                ArrayList<Abonent> userArr = abonentDAO.getAbonentFromDB(-1, "", "", "", -1, -1, "", "", 0);
                request.setAttribute("abonentArr", userArr);
            }
        } else {
            session.setAttribute("sessionId", Constants.ERROR);
            session.setAttribute("type", "Wrong");
        }
    }

    private void showUserInfo(HttpSession session, HttpServletRequest request) {
        int idAbonent;
        AbonentDAO abonentDAO = new AbonentDAO();
        try {
            idAbonent = Integer.parseInt(request.getParameter("idAbonent"));
        } catch (Exception e) {
            idAbonent = Integer.parseInt(session.getAttribute("idAbonent").toString());
        }
        session.setAttribute("idAbonent", idAbonent);
        Abonent abonent = abonentDAO.getAbonentFromDB(idAbonent, "", "", "", -1, -1, "", "", 0).get(0);
        AreaDAO areaDAO = new AreaDAO();
        Area area = areaDAO.getAreaFromDB(abonent.getIdAreaCode(), "").get(0);
        BillingDAO billingDao = new BillingDAO();
        Billing billing = billingDao.getBillingFromDB(abonent.getIdBilling(), -1, -1, "", -1, "").get(0);
        TariffDAO tariffDAO = new TariffDAO();
        Tariff tariff = tariffDAO.getTariffFromDB(billing.getIdTariff(), "", -1, -1).get(0);
        session.setAttribute("nameTariff", tariff.getNameTariff());
        session.setAttribute("priceTariff", tariff.getPrice());
        session.setAttribute("balance", billing.getBalance());
        session.setAttribute("chargeAmount", billing.getChargeAmount());
        session.setAttribute("connectionDate", billing.getConnectionDate());
        session.setAttribute("area", area.getNameArea());
        session.setAttribute("name", abonent.getName());
        session.setAttribute("surname", abonent.getSurname());
        session.setAttribute("number", abonent.getPhoneNumber());
        session.setAttribute("status", billing.getStatus());
        session.setAttribute("billingId", billing.getIdBilling());
        session.setAttribute("sessionId", Constants.SHOW_USER_BY_ID);
    }

    private void changeUserStatus(HttpSession session, HttpServletRequest request) {
        BillingDAO billingDAO = new BillingDAO();
        billingDAO.updateBillingStatusDB(Integer.parseInt(session.getAttribute("billingId").toString()), session.getAttribute("status").toString());
        session.setAttribute("sessionId", "109Admin");
        showUserInfo(session, request);
    }

    private void showAvailablePackages(HttpSession session, HttpServletRequest request) {
        int billingId = Integer.parseInt(session.getAttribute("billingId").toString());
        BillingDAO billingDAO = new BillingDAO();
        Billing billing = billingDAO.getBillingFromDB(billingId, -1, -1, "", -1, "").get(0);
        session.setAttribute("balance", billing.getBalance());
        session.setAttribute("chargeAmount", billing.getChargeAmount());
        session.setAttribute("connectionDate", billing.getConnectionDate());
        TariffDAO tariffDAO = new TariffDAO();
        Tariff tariff = tariffDAO.getTariffFromDB(billing.getIdTariff(), "", -1, -1).get(0);
        session.setAttribute("tariff", tariff.getNameTariff());
        session.setAttribute("tariffPrice", tariff.getPrice());
        ArrayList<Tariff> tariffs = tariffDAO.getTariffFromDB(-1, "", -1, Integer.parseInt(session.getAttribute("areaCode").toString()));
        session.setAttribute("tariffs", tariffs);
    }

    private void changeTariff(HttpSession session, HttpServletRequest request) {
        TariffDAO tariffDAO = new TariffDAO();
        BillingDAO billingDAO = new BillingDAO();
        Tariff tariff = tariffDAO.getTariffFromDB(Integer.parseInt(request.getParameter("tarifId")), "", -1, -1).get(0);
        int balance = Integer.parseInt(session.getAttribute("balance").toString());
        int bonus = Integer.parseInt(session.getAttribute("chargeAmount").toString());
        if (bonus >= tariff.getPrice()) {
            bonus -= tariff.getPrice();
        }
        else {
            balance -= (tariff.getPrice() - bonus);
            bonus = 0;
            billingDAO.updateBalanceBillingDB(Integer.parseInt(session.getAttribute("billingId").toString()), balance);
        }
        billingDAO.updateBonusBillingDB(Integer.parseInt(session.getAttribute("billingId").toString()), bonus);
        billingDAO.updateTariffIdBillingDB(Integer.parseInt(session.getAttribute("billingId").toString()), tariff.getIdTariff());
//        Date date = new Date(System.currentTimeMillis());
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        billingDAO.updateConnectionDatedBillingDB(Integer.parseInt(session.getAttribute("billingId").toString()), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
        session.setAttribute("sessionId", Constants.ID_USER);
        loginAccount(session, request);
    }
}
