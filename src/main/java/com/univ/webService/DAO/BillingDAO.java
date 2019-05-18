package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.Billing;
import com.univ.webService.servlet.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {
    public List<Billing> getBillingFromDB(int idBilling, int balance, int chargeAmount, String connectionDate, int idTariff, String status) {

        final String sqlQuery = "SELECT * FROM Billing WHERE idBilling " + (idBilling == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idBilling) +
                " AND Balance " + (balance == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + balance) +
                " AND chargeAmount " + (chargeAmount == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + chargeAmount) +
                " AND connectionDate " + (connectionDate.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + connectionDate + "'") +
                " AND idTariff " + (idTariff == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idTariff) +
                " AND Status " + (status.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + status + "'");


        List<Billing> getBillingArr = new ArrayList<>();
        Connection connection = DataConnection.getDBConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Billing billing = new Billing(rs.getInt("idBilling"), rs.getInt("Balance"), rs.getInt("chargeAmount"),
                        rs.getString("connectionDate"), rs.getInt("idTariff"), rs.getString("Status"));
                getBillingArr.add(billing);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getBillingArr;
    }

    public void updateBillingStatusDB(int idBilling, String status) {
        status = status.equals("disabled") ? "enabled" : "disabled";
        String sqlQueryBilling = "UPDATE Billing SET Status = '" + status + "' WHERE idBilling = " + idBilling;
        DataConnection.updateDB(sqlQueryBilling);
    }

    public void updateBonusBillingDB(int idBilling, int bonus) {
        String sqlQueryBilling = "UPDATE Billing SET chargeAmount = '" + bonus + "' WHERE idBilling = " + idBilling;
        DataConnection.updateDB(sqlQueryBilling);
    }

    public void updateBalanceBillingDB(int idBilling, int balance) {
        String sqlQueryBilling = "UPDATE Billing SET Balance = '" + balance + "' WHERE idBilling = " + idBilling;
        DataConnection.updateDB(sqlQueryBilling);
    }

    public void updateTariffIdBillingDB(int idBilling, int idTariff) {
        String sqlQueryBilling = "UPDATE Billing SET idTariff = '" + idTariff + "' WHERE idBilling = " + idBilling;
        DataConnection.updateDB(sqlQueryBilling);
    }

    public void updateConnectionDatedBillingDB(int idBilling, String time) {
        String sqlQueryBilling = "UPDATE Billing SET connectionDate = '" + time + "' WHERE idBilling = " + idBilling;
        DataConnection.updateDB(sqlQueryBilling);
    }
}
