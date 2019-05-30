package com.univ.webService.businessLogic;

import com.univ.webService.DAO.*;
import com.univ.webService.dataModel.*;
import com.univ.webService.servlet.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Service {
    public static void loginAccount(HttpSession session, HttpServletRequest request) throws SQLException {
        AbonentDAO abonentDAO = new AbonentDAO();
        String pass;
        String login;
        try {
            pass = checkValidation(checkXSS(request.getParameter("pass").equals(Constants.EMPTY_FIELD) ? "," : request.getParameter("pass")));
            login = checkValidation(checkXSS(request.getParameter("login")));
        } catch (Exception e) {
            pass = session.getAttribute("pass").toString();
            login = session.getAttribute("login").toString();
        }
        session.setAttribute("pass", pass);
        session.setAttribute("login", login);
        List<Abonent> abonentArr = abonentDAO.getAbonentFromDB(Constants.SELECT_ALL_INT, Constants.SELECT_ALL_STR, Constants.SELECT_ALL_STR,
                Constants.SELECT_ALL_STR, Constants.SELECT_ALL_INT, Constants.SELECT_ALL_INT, login, pass, Constants.SELECT_ALL_INT);
        if (abonentArr.size() == 1) {
            Abonent abonent = abonentArr.get(0);
            request.setAttribute("name", abonent.getName());
            request.setAttribute("surname", abonent.getSurname());
            request.setAttribute("number", abonent.getPhoneNumber());
            session.setAttribute("idAbonent", abonent.getIdAbonent());
            if (abonent.getIsAdmin() == 0) {
                session.setAttribute("sessionId", Constants.ID_USER);
                session.setAttribute("type", "Abonent");
                session.setAttribute("billingId", abonent.getIdBilling());
                session.setAttribute("areaCode", abonent.getIdAreaCode());
                showAvailablePackages(session, request);
            } else {
                session.setAttribute("sessionId", Constants.ID_ADMIN);
                session.setAttribute("type", "Admin");
                List<Abonent> userArr = abonentDAO.getAbonentFromDB(Constants.SELECT_ALL_INT, Constants.SELECT_ALL_STR,
                        Constants.SELECT_ALL_STR, Constants.SELECT_ALL_STR, Constants.SELECT_ALL_INT, Constants.SELECT_ALL_INT,
                        Constants.SELECT_ALL_STR, Constants.SELECT_ALL_STR, Constants.IS_USER);
                request.setAttribute("abonentArr", userArr);
            }
        } else {
            session.setAttribute("sessionId", Constants.ERROR);
            session.setAttribute("type", "Wrong");
        }
    }

    public static void showUserInfo(HttpSession session, HttpServletRequest request) throws SQLException {
        int idAbonent;
        AbonentDAO abonentDAO = new AbonentDAO();
        try {
            idAbonent = Integer.parseInt(checkDigitValidation(checkXSS(request.getParameter("idAbonent"))));
        } catch (Exception e) {
            idAbonent = Integer.parseInt(session.getAttribute("idAbonent").toString());
        }
        session.setAttribute("idAbonent", idAbonent);
        Abonent abonent;
        try {
            abonent = abonentDAO.getAbonentFromDB(idAbonent, Constants.SELECT_ALL_STR, Constants.SELECT_ALL_STR,
                    Constants.SELECT_ALL_STR, Constants.SELECT_ALL_INT, Constants.SELECT_ALL_INT,
                    Constants.SELECT_ALL_STR, Constants.SELECT_ALL_STR, Constants.IS_USER).get(0);
        } catch (Exception e) {
            session.setAttribute("sessoinId", Constants.LOGIN_ACCOUNT);
            loginAccount(session, request);
            return;
        }
        AreaDAO areaDAO = new AreaDAO();
        Area area = areaDAO.getAreaFromDB(abonent.getIdAreaCode(), Constants.SELECT_ALL_STR).get(0);
        BillingDAO billingDao = new BillingDAO();
        Billing billing = billingDao.getBillingFromDB(abonent.getIdBilling(), Constants.SELECT_ALL_INT, Constants.SELECT_ALL_INT,
                Constants.SELECT_ALL_STR, Constants.SELECT_ALL_INT, Constants.SELECT_ALL_STR).get(0);
        TariffDAO tariffDAO = new TariffDAO();
        Tariff tariff = tariffDAO.getTariffFromDB(billing.getIdTariff(), Constants.SELECT_ALL_STR, Constants.SELECT_ALL_INT,
                Constants.SELECT_ALL_INT).get(0);
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

    public static void changeUserStatus(HttpSession session, HttpServletRequest request) throws SQLException {
        BillingDAO billingDAO = new BillingDAO();
        billingDAO.updateBillingStatusDB(Integer.parseInt(session.getAttribute("billingId").toString()), session.getAttribute("status").toString());
        session.setAttribute("sessionId", "109Admin");
        showUserInfo(session, request);
    }

    public static void changeTariff(HttpSession session, HttpServletRequest request) throws SQLException {
        TariffDAO tariffDAO = new TariffDAO();
        BillingDAO billingDAO = new BillingDAO();
        Tariff tariff = tariffDAO.getTariffFromDB(Integer.parseInt(request.getParameter("tarifId")), Constants.SELECT_ALL_STR,
                Constants.SELECT_ALL_INT, Constants.SELECT_ALL_INT).get(0);
        String nextTariff = tariff.getNameTariff();
        Billing billing = billingDAO.getBillingFromDB(Integer.parseInt(session.getAttribute("billingId").toString()),
                Constants.SELECT_ALL_INT, Constants.SELECT_ALL_INT, Constants.SELECT_ALL_STR, Constants.SELECT_ALL_INT,
                Constants.SELECT_ALL_STR).get(0);
        int currentIdTariff = billing.getIdTariff();
        Tariff currentTariff = tariffDAO.getTariffFromDB(currentIdTariff, Constants.SELECT_ALL_STR, Constants.SELECT_ALL_INT, Constants.SELECT_ALL_INT).get(0);
        String currentTariffName = currentTariff.getNameTariff();
        String currentStatus = billing.getStatus();
        int balance = Integer.parseInt(session.getAttribute("balance").toString());
        int bonus = Integer.parseInt(session.getAttribute("chargeAmount").toString());
        if (bonus >= tariff.getPrice()) {
            bonus -= tariff.getPrice();
        } else {
            balance -= (tariff.getPrice() - bonus);
            bonus = 0;
            billingDAO.updateBalanceBillingDB(Integer.parseInt(session.getAttribute("billingId").toString()), balance);
        }
        billingDAO.updateBonusBillingDB(Integer.parseInt(session.getAttribute("billingId").toString()), bonus);
        billingDAO.updateTariffIdBillingDB(Integer.parseInt(session.getAttribute("billingId").toString()), tariff.getIdTariff());
        billingDAO.updateConnectionDatedBillingDB(Integer.parseInt(session.getAttribute("billingId").toString()),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
        session.setAttribute("sessionId", Constants.ID_USER);
        if (currentTariffName == null) {
            Exception e = new Exception();
            e.printStackTrace();
        }
        loginAccount(session, request);
    }

    private static void showAvailablePackages(HttpSession session, HttpServletRequest request) throws SQLException {
        int billingId = Integer.parseInt(session.getAttribute("billingId").toString());
        BillingDAO billingDAO = new BillingDAO();
        Billing billing = billingDAO.getBillingFromDB(billingId, Constants.SELECT_ALL_INT, Constants.SELECT_ALL_INT,
                Constants.SELECT_ALL_STR, Constants.SELECT_ALL_INT, Constants.SELECT_ALL_STR).get(0);
        session.setAttribute("balance", billing.getBalance());
        session.setAttribute("chargeAmount", billing.getChargeAmount());
        session.setAttribute("connectionDate", billing.getConnectionDate());
        TariffDAO tariffDAO = new TariffDAO();
        Tariff tariff = tariffDAO.getTariffFromDB(billing.getIdTariff(), Constants.SELECT_ALL_STR, Constants.SELECT_ALL_INT,
                Constants.SELECT_ALL_INT).get(0);
        session.setAttribute("tariff", tariff.getNameTariff());
        session.setAttribute("tariffPrice", tariff.getPrice());
        List<Tariff> tariffs = tariffDAO.getTariffFromDB(Constants.SELECT_ALL_INT, Constants.SELECT_ALL_STR, Constants.SELECT_ALL_INT,
                Integer.parseInt(session.getAttribute("areaCode").toString()));
        session.setAttribute("tariffs", tariffs);
        request.setAttribute("tariffs", tariffs);
    }


    private static int getCurrentWeek() {
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }

    private static String checkXSS(String s) {
        return s.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").
                replaceAll("'", "&apos;").replaceAll("&", "&amp;");
    }

    private static String checkValidation(String s) {
        if (s.matches("^[A-Za-z0-9]{1,35}")) {
            return s;
        }
        return ",";
    }

    private static String checkDigitValidation(String s) {
        if (s.matches("^[1-9][0-9]{0,4}")) {
            return s;
        }
        return "0";

    }
}
