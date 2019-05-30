package com.univ.webService.businessLogic;

import com.univ.webService.DAO.BillingDAO;
import com.univ.webService.DAO.TariffDAO;
import com.univ.webService.dataModel.Billing;
import com.univ.webService.dataModel.Tariff;
import com.univ.webService.factory.BeanFactory;
import com.univ.webService.servlet.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ChangeService {
    public static void changeUserStatus(HttpSession session, HttpServletRequest request) throws SQLException {
        BillingDAO billingDAO = (BillingDAO) BeanFactory.getBean(BillingDAO.class);
        billingDAO.updateBillingStatusDB(Integer.parseInt(session.getAttribute("billingId").toString()), session.getAttribute("status").toString());
        session.setAttribute("sessionId", "109Admin");
        ShowService.showUserInfo(session, request);
    }

    public static void changeTariff(HttpSession session, HttpServletRequest request) throws SQLException {
        TariffDAO tariffDAO = (TariffDAO) BeanFactory.getBean(TariffDAO.class);
        BillingDAO billingDAO = (BillingDAO) BeanFactory.getBean(BillingDAO.class);

        Tariff tariff = tariffDAO.getTariffFromDB(Integer.parseInt(request.getParameter("tarifId")), Constants.SELECT_ALL_INT).get(0);
        Billing billing = billingDAO.getBillingFromDB(Integer.parseInt(session.getAttribute("billingId").toString())).get(0);
        int currentIdTariff = billing.getIdTariff();
        Tariff currentTariff = tariffDAO.getTariffFromDB(currentIdTariff, Constants.SELECT_ALL_INT).get(0);
        String currentTariffName = currentTariff.getNameTariff();
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
        LoginService.loginAccount(session, request);
    }

}
