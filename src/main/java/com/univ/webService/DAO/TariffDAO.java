package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.Tariff;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TariffDAO {
    public ArrayList<Tariff> getTariffFromDB(int idTariff, String nameTariff, int Price, int idArea) {
        ArrayList<Tariff> getTariffArr = new ArrayList<>();

        Connection conn = DataConnection.getDBConnection();

        String sqlQueryTariff = "SELECT * FROM Tariff WHERE";
        if (idTariff == -1) {
            sqlQueryTariff += " idTariff LIKE '%' ";
        } else {
            sqlQueryTariff += " idTariff = " + idTariff + " ";
        }
        sqlQueryTariff += "AND";
        if (nameTariff.equals("")) {
            sqlQueryTariff += " nameTariff LIKE '%' ";
        } else {
            sqlQueryTariff += " nameTariff = '" + nameTariff + "' ";
        }
        sqlQueryTariff += "AND";
        if (Price == -1) {
            sqlQueryTariff += " Price LIKE '%' ";
        } else {
            sqlQueryTariff += " Price = " + Price + " ";
        }
        sqlQueryTariff += "AND";
        if (idArea == -1) {
            sqlQueryTariff += " idArea LIKE '%'";
        } else {
            sqlQueryTariff += " idArea = " + idArea;
        }

        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sqlQueryTariff);
            while (rs.next()) {
                Tariff tariff = new Tariff(rs.getInt("idTariff"), rs.getString("nameTariff"),
                        rs.getInt("Price"), rs.getInt("idArea"));
                getTariffArr.add(tariff);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getTariffArr;
    }
}
