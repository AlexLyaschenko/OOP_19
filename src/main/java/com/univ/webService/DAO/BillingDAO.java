package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.Billing;

import java.sql.*;
import java.util.ArrayList;

public class BillingDAO {
    public ArrayList<Billing> getBillingFromDB(int idBilling, int Balance, int chargeAmount, String connectionDate, int idTariff, String status) {
        ArrayList<Billing> getBillingArr = new ArrayList<>();
        Connection conn = DataConnection.getDBConnection();
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
                Billing billing = new Billing(rs.getInt("idBilling"), rs.getInt("Balance"), rs.getInt("chargeAmount"),
                        rs.getString("connectionDate"), rs.getInt("idTariff"), rs.getString("Status"));
                getBillingArr.add(billing);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getBillingArr;
    }

    public void updateBillingStatusDB(int idBilling, String status) {
        DataConnection dConn = new DataConnection();
        Connection conn = dConn.getDBConnection();
        status = status.equals("disabled") ? "enabled" : "disabled";
        String sqlQueryBilling = "UPDATE Billing SET Status = '" + status + "' WHERE idBilling = " + idBilling;
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sqlQueryBilling);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void updateBonusBillingDB(int idBilling, int bonus) {
        DataConnection dConn = new DataConnection();
        Connection conn = dConn.getDBConnection();
        String sqlQueryBilling = "UPDATE Billing SET chargeAmount = '" + bonus + "' WHERE idBilling = " + idBilling;
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sqlQueryBilling);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void updateBalanceBillingDB(int idBilling, int balance) {
        DataConnection dConn = new DataConnection();
        Connection conn = dConn.getDBConnection();
        String sqlQueryBilling = "UPDATE Billing SET Balance = '" + balance + "' WHERE idBilling = " + idBilling;
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sqlQueryBilling);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void updateTariffIdBillingDB(int idBilling, int idTariff) {
        DataConnection dConn = new DataConnection();
        Connection conn = dConn.getDBConnection();
        String sqlQueryBilling = "UPDATE Billing SET idTariff = '" + idTariff + "' WHERE idBilling = " + idBilling;
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sqlQueryBilling);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void updateConnectionDatedBillingDB(int idBilling, String time) {
        DataConnection dConn = new DataConnection();
        Connection conn = dConn.getDBConnection();
        String sqlQueryBilling = "UPDATE Billing SET connectionDate = '" + time + "' WHERE idBilling = " + idBilling;
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sqlQueryBilling);
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}