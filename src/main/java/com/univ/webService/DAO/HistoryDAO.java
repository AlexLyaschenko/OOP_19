package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {
    public List<History> getHistoryFromDB(int idHistory, int idAbonent, int idWeek) {
        List<History> getHistoryArr = new ArrayList<>();

        Connection connection = DataConnection.getDBConnection();

        String sqlQueryHistory = "SELECT * FROM History Where";
        if (idHistory == -1) {
            sqlQueryHistory += " idHistory LIKE '%' ";
        } else {
            sqlQueryHistory += " idHistory = " + idHistory + " ";
        }
        sqlQueryHistory += "AND";
        if (idAbonent == -1) {
            sqlQueryHistory += " idAbonent LIKE '%' ";
        } else {
            sqlQueryHistory += " idAbonent = " + idAbonent + " ";
        }
        sqlQueryHistory += "AND";
        if (idWeek == -1) {
            sqlQueryHistory += " idWeek LIKE '%' ";
        } else {
            sqlQueryHistory += " idWeek = " + idWeek;
        }

        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQueryHistory);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                History history = new History(rs.getInt("idHistory"), rs.getInt("idAbonent"), rs.getInt("idWeek"),
                        rs.getString("TariffName"), rs.getString("Status"), rs.getString("Date"));
                getHistoryArr.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return getHistoryArr;

    }

    public void setHistoryToDB(int idAbonent, String tariffName, String status, String date, int idWeek) {
        String sqlQueryHistory = "INSERT INTO History (idAbonent, TariffName, Status, Date, idWeek) VALUES ('" + idAbonent + "', '" + tariffName + "', '" +
                status + "', '" + date + "', '" + idWeek + "')";
        DataConnection.insertToDB(sqlQueryHistory);
    }
}
