package com.univ.webService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class BillingDAO {
    ArrayList<Billing> getBillingFromDB(int idBilling, int Balance, int chargeAmount, String connectionDate, int idTariff, String status) {
        ArrayList<Billing> getBillingArr = new ArrayList<>();
        DataConnection dConn = new DataConnection();
        Connection conn = dConn.getDBConnection();
        String sqlQueryBilling = "SELECT idBilling, Balance, chargeAmount, connectionDate, idTariff, Status FROM Billing WHERE";
        if (idBilling == -1) {
            sqlQueryBilling += " idBilling LIKE '%' ";
        } else {
            sqlQueryBilling += " idBilling = " + idBilling + " ";
        }
        sqlQueryBilling += "AND";
        if (Balance == -1) {
            sqlQueryBilling += " Balance LIKE '%' ";
        } else {
            sqlQueryBilling += " Balance = " + Balance + " ";
        }
        sqlQueryBilling += "AND";
        if (chargeAmount == -1) {
            sqlQueryBilling += " chargeAmount LIKE '%' ";
        } else {
            sqlQueryBilling += " chargeAmount = " + chargeAmount + " ";
        }
        sqlQueryBilling += "AND";
        if (connectionDate.equals("")) {
            sqlQueryBilling += " connectionDate LIKE '%' ";
        } else {
            sqlQueryBilling += " connectionDate = '" + connectionDate + "' ";
        }
        sqlQueryBilling += "AND";
        if (idTariff == -1) {
            sqlQueryBilling += " idTariff LIKE '%' ";
        } else {
            sqlQueryBilling += " idTariff = " + idTariff + " ";
        }
        sqlQueryBilling += "AND";
        if (status.equals("")) {
            sqlQueryBilling += " Status LIKE '%'";
        } else {
            sqlQueryBilling += " Status = '" + status + "'";
        }

        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sqlQueryBilling);
            while (rs.next()) {
                Billing billing = new Billing();
                billing.setIdBilling(rs.getInt("idBilling"));
                billing.setBalance(rs.getInt("Balance"));
                billing.setChargeAmount(rs.getInt("chargeAmount"));
                billing.setConnectionDate(rs.getString("connectionDate"));
                billing.setIdTariff(rs.getInt("idTariff"));
                billing.setStatus(rs.getString("Status"));
                getBillingArr.add(billing);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getBillingArr;
    }

    void updateBillingDB(int idBilling, String status) {
        DataConnection dConn = new DataConnection();
        Connection conn = dConn.getDBConnection();
        String sqlQueryBilling = "UPDATE Billing SET Status = '" + status + "' WHERE idBilling = " + idBilling;
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sqlQueryBilling);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
