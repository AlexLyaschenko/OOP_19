package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.History;
import com.univ.webService.servlet.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {
    public List<History> getHistoryFromDB(int idHistory, int idAbonent, int idWeek) {

        final String sqlQuery = "SELECT * FROM History " +
                "WHERE idHistory " + (idHistory == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idHistory) +
                " AND idAbonent " + (idAbonent == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idAbonent) +
                " AND idWeek " + (idWeek == Constants.SELECT_ALL_INT ? "LIKE '%' " : "= " + idWeek);


        List<History> getHistoryArr = new ArrayList<>();
        Connection connection = DataConnection.getDBConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                History history = new History(rs.getInt("idHistory"), rs.getInt("idAbonent"), rs.getInt("idWeek"),
                        rs.getString("TariffName"), rs.getString("Status"), rs.getString("Date"));
                getHistoryArr.add(history);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getHistoryArr;

    }

    public void setHistoryToDB(int idAbonent, String tariffName, String status, String date, int idWeek) {
        final String sqlQueryHistory = "INSERT INTO History (idAbonent, TariffName, Status, Date, idWeek) VALUES ('" +
                idAbonent + "', '" + tariffName + "', '" + status + "', '" + date + "', '" + idWeek + "')";
        DataConnection.insertToDB(sqlQueryHistory);
    }
}
