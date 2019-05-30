package com.univ.webService.businessLogic;

import com.univ.webService.DAO.AbonentDAO;
import com.univ.webService.DAO.AreaDAO;
import com.univ.webService.DAO.BillingDAO;
import com.univ.webService.DAO.TariffDAO;
import com.univ.webService.dataModel.Abonent;
import com.univ.webService.dataModel.Area;
import com.univ.webService.dataModel.Billing;
import com.univ.webService.dataModel.Tariff;
import com.univ.webService.factory.BeanFactory;
import com.univ.webService.servlet.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ShowService {

    public void showUserInfo(HttpSession session, HttpServletRequest request) throws SQLException {
        int idAbonent;
        AbonentDAO abonentDAO = (AbonentDAO) BeanFactory.getBean(AbonentDAO.class);
        try {
            idAbonent = Integer.parseInt(checkDigitValidation(checkXSS(request.getParameter("idAbonent"))));
        } catch (Exception e) {
            idAbonent = Integer.parseInt(session.getAttribute("idAbonent").toString());
        }
        session.setAttribute("idAbonent", idAbonent);
        Abonent abonent;
        try {
            abonent = abonentDAO.getAbonentFromDB(idAbonent, Constants.SELECT_ALL_STR, Constants.SELECT_ALL_STR, Constants.IS_USER).get(0);
        } catch (Exception e) {
            session.setAttribute("sessoinId", Constants.LOGIN_ACCOUNT);
            ((LoginService) BeanFactory.getBean(LoginService.class)).loginAccount(session, request);
            return;
        }
        AreaDAO areaDAO = (AreaDAO) BeanFactory.getBean(AreaDAO.class);
        Area area = areaDAO.getAreaFromDB(abonent.getIdAreaCode()).get(0);
        BillingDAO billingDao = (BillingDAO) BeanFactory.getBean(BillingDAO.class);
        Billing billing = billingDao.getBillingFromDB(abonent.getIdBilling()).get(0);
        TariffDAO tariffDAO = (TariffDAO) BeanFactory.getBean(TariffDAO.class);
        Tariff tariff = tariffDAO.getTariffFromDB(billing.getIdTariff(), Constants.SELECT_ALL_INT).get(0);

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

    private static String checkXSS(String s) {
        return s.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").
                replaceAll("'", "&apos;").replaceAll("&", "&amp;");
    }

    private static String checkDigitValidation(String s) {
        if (s.matches("^[1-9][0-9]{0,4}")) {
            return s;
        }
        return "0";

    }
}
