//package com.univ.webService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/abonentInfo")
//public class AbonentInfoServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("type", "Abonent");
//        request.setAttribute("sessionId", "101");
//        request.setAttribute("abonentId", request.getParameter("abonentId"));
//        AbonentDAO abonentDAO = new AbonentDAO();
//        Abonent abonent = abonentDAO.getAbonentFromDB(Integer.parseInt((String)request.getAttribute("abonentId")), "","","",-1,-1,"","",0).get(0);
//        AreaDAO areaDAO = new AreaDAO();
//        Area area = areaDAO.getAreaFromDB(abonent.getIdAreaCode(), "").get(0);
//        BillingDAO billingDao = new BillingDAO();
//        Billing billing = billingDao.getBillingFromDB(abonent.getIdBilling(),-1,-1,"",-1,"").get(0);
//        TariffDAO tariffDAO = new TariffDAO();
//        Tariff tariff = tariffDAO.getTariffFromDB(billing.getIdTariff(),"",-1,-1).get(0);
//        request.setAttribute("nameTariff", tariff.getNameTariff());
//        request.setAttribute("priceTariff", tariff.getPrice());
//        request.setAttribute("balance", billing.getBalance());
//        request.setAttribute("chargeAmount", billing.getChargeAmount());
//        request.setAttribute("connectionDate", billing.getConnectionDate());
//        request.setAttribute("area", area.getNameArea());
//        request.setAttribute("name", abonent.getName());
//        request.setAttribute("surname", abonent.getSurname());
//        request.setAttribute("number", abonent.getPhoneNumber());
//        request.setAttribute("status", billing.getStatus());
//        request.setAttribute("billingId", billing.getIdBilling());
//        request.getRequestDispatcher("response.jsp").forward(request, response);
//    }
//}
