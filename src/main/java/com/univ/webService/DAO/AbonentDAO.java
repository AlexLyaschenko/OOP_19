package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.Abonent;
import com.univ.webService.servlet.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AbonentDAO {
    public List<Abonent> getAbonentFromDB(int idAbonent, String name, String surname, String phoneNumber, int idAreaCode,
                                          int idBilling, String login, String password, int isAdmin) {

        final String sqlQuery = "SELECT * FROM Abonent WHERE idAbonent " + (idAbonent == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idAbonent) +
                " AND Name " + (name.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + name + "'") +
                " AND Surname " + (surname.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + surname + "'") +
                " AND phoneNumber " + (phoneNumber.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + phoneNumber + "'") +
                " AND idAreaCode " + (idAreaCode == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idAreaCode) +
                " AND idBilling " + (idBilling == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idBilling) +
                " AND Login " + (login.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + login + "'") +
                " AND Password " + (password.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + password + "'") +
                " AND isAdmin " + (isAdmin == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + isAdmin);


        List<Abonent> getAbonentArr = new ArrayList<>();
        Connection connection = DataConnection.getDBConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Abonent abonent = new Abonent(rs.getInt("idAbonent"), rs.getString("Name"), rs.getString("Surname"),
                        rs.getString("phoneNumber"), rs.getInt("idAreaCode"), rs.getInt("idBilling"),
                        rs.getString("Login"), rs.getString("Password"), rs.getInt("isAdmin"));
                getAbonentArr.add(abonent);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAbonentArr;
    }
}
