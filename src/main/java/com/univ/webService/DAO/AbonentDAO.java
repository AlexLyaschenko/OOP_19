package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.Abonent;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class AbonentDAO {
    public ArrayList<Abonent> getAbonentFromDB(int idAbonent, String Name, String Surname, String phoneNumber, int idAreaCode,
                                               int idBilling, String Login, String Password, int isAdmin) {

        ArrayList<Abonent> getAbonentArr = new ArrayList<>();

        Connection conn = DataConnection.getDBConnection();

        String sqlQueryAbonent = "SELECT * FROM Abonent WHERE";
        if (idAbonent == -1) {
            sqlQueryAbonent += " idAbonent LIKE '%' ";
        } else {
            sqlQueryAbonent += " idAbonent = " + idAbonent + " ";
        }
        sqlQueryAbonent += "AND";
        if (Name.equals("")) {
            sqlQueryAbonent += " Name LIKE '%' ";
        } else {
            sqlQueryAbonent += " Name = '" + Name + "' ";
        }
        sqlQueryAbonent += "AND";
        if (Surname.equals("")) {
            sqlQueryAbonent += " Surname LIKE '%' ";
        } else {
            sqlQueryAbonent += " Surname = '" + Surname + "' ";
        }
        sqlQueryAbonent += "AND";
        if (phoneNumber.equals("")) {
            sqlQueryAbonent += " phoneNumber LIKE '%' ";
        } else {
            sqlQueryAbonent += " phoneNumber = '" + phoneNumber + "' ";
        }
        sqlQueryAbonent += "AND";
        if (idAreaCode == -1) {
            sqlQueryAbonent += " idAreaCode LIKE '%' ";
        } else {
            sqlQueryAbonent += " idAreaCode = " + idAreaCode + " ";
        }
        sqlQueryAbonent += "AND";
        if (idBilling == -1) {
            sqlQueryAbonent += " idBilling LIKE '%' ";
        } else {
            sqlQueryAbonent += " idBilling = " + idBilling + " ";
        }
        sqlQueryAbonent += "AND";
        if (Login.equals("")) {
            sqlQueryAbonent += " Login LIKE '%' ";
        } else {
            sqlQueryAbonent += " Login = '" + Login + "' ";
        }
        sqlQueryAbonent += "AND";
        if (Password.equals("")) {
            sqlQueryAbonent += " Password LIKE '%' ";
        } else {
            sqlQueryAbonent += " Password = '" + Password + "' ";
        }
        sqlQueryAbonent += "AND";
        if (isAdmin == -1) {
            sqlQueryAbonent += " isAdmin LIKE '%'";
        } else {
            sqlQueryAbonent += " isAdmin = " + isAdmin;
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlQueryAbonent);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Abonent abonent = new Abonent(rs.getInt("idAbonent"), rs.getString("Name"), rs.getString("Surname"),
                        rs.getString("phoneNumber"), rs.getInt("idAreaCode"), rs.getInt("idBilling"),
                        rs.getString("Login"), rs.getString("Password"), rs.getInt("isAdmin"));
                getAbonentArr.add(abonent);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAbonentArr;
    }
}
