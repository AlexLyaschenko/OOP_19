package com.univ.webService;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AbonentDAO abonentDAO = new AbonentDAO();
        HttpSession session = request.getSession();

        if (session.getAttribute("type") == null) {
            String pass = request.getParameter("pass").equals("") ? "," : request.getParameter("pass");
            String login = request.getParameter("login");
            session.setAttribute("pass", pass);
            session.setAttribute("login", login);
            ArrayList<Abonent> abonentArr = abonentDAO.getAbonentFromDB(-1, "", "", "", -1, -1, login, pass, -1);
            if (abonentArr.size() == 1) {
                Abonent abonent = abonentArr.get(0);
                session.setAttribute("name", abonent.getName());
                session.setAttribute("surname", abonent.getSurname());
                session.setAttribute("number", abonent.getPhoneNumber());
                if (abonent.getIsAdmin() == 0) {
                    session.setAttribute("sessionId", 100);
                    session.setAttribute("type", "Abonent");
                } else {
                    session.setAttribute("sessionId", 7);
                    session.setAttribute("type", "Admin");
                    ArrayList<Abonent> userArr = abonentDAO.getAbonentFromDB(-1, "", "", "", -1, -1, "", "", 0);
                    session.setAttribute("abonentArr", userArr);
                }
            } else {
                session.setAttribute("sessionId", 0);
                session.setAttribute("type", "Wrong");
            }
        }
        else if (session.getAttribute("type").toString().equals("Admin") && session.getAttribute("sessionId").toString().equals("109")) {
            //int idAbonent = (int)session.getAttribute("idAbonent");
            int idAbonent = Integer.parseInt(request.getParameter("view"));

            Abonent abonent = abonentDAO.getAbonentFromDB(idAbonent, "","","",-1,-1,"","",0).get(0);
            AreaDAO areaDAO = new AreaDAO();
            Area area = areaDAO.getAreaFromDB(abonent.getIdAreaCode(), "").get(0);
            BillingDAO billingDao = new BillingDAO();
            Billing billing = billingDao.getBillingFromDB(abonent.getIdBilling(),-1,-1,"",-1,"").get(0);
            TariffDAO tariffDAO = new TariffDAO();
            Tariff tariff = tariffDAO.getTariffFromDB(billing.getIdTariff(),"",-1,-1).get(0);
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
            session.setAttribute("sessionId", "8");
        }
        request.getRequestDispatcher("response.jsp").forward(request, response);

    }
}
