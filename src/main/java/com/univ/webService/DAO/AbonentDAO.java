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
                                          int idBilling, String login, String password, int isAdmin) throws SQLException {
        List<Abonent> getAbonentArr = new ArrayList<>();
        Connection connection = DataConnection.getDBConnection();
        final String sqlQuery =
                String.format(
                        "SELECT * FROM Abonent WHERE idAbonent %s AND Name %s AND Surname %s AND phoneNumber %s AND " +
                                "idAreaCode %s AND idBilling %s AND Login %s AND Password %s AND isAdmin %s",
                        idAbonent == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idAbonent,
                        name.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + name + "'",
                        surname.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + surname + "'",
                        phoneNumber.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + phoneNumber + "'",
                        idAreaCode == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idAreaCode,
                        idBilling == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idBilling,
                        login.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + login + "'",
                        password.equals(Constants.SELECT_ALL_STR) ? "LIKE '%'" : "= '" + password + "'",
                        isAdmin == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + isAdmin
                );
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.
                    executeQuery();
            while (rs.next()) {
                Abonent abonent = new Abonent(rs.getInt("idAbonent"), rs.getString("Name"), rs.getString("Surname"),
                        rs.getString("phoneNumber"), rs.getInt("idAreaCode"), rs.getInt("idBilling"),
                        rs.getString("Login"), rs.getString("Password"), rs.getInt("isAdmin"));
                getAbonentArr.add(abonent);
            }
            connection.close();
        return getAbonentArr;
    }
}
