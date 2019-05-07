package com.univ.webService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class TariffDAO {
    ArrayList<Tariff> getTariffFromDB(int idTariff, String nameTariff, int Price, int idArea) {
        ArrayList<Tariff> getTariffArr = new ArrayList<>();
        DataConnection dConn = new DataConnection();
        Connection conn = dConn.getDBConnection();
        String sqlQueryTariff = "SELECT * FROM Tariff WHERE";
        if (idTariff == -1) {
            sqlQueryTariff += " idTariff LIKE '%' ";
        } else {
            sqlQueryTariff += " idTariff = " + idTariff  + " ";
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
            sqlQueryTariff += " Price = " + Price  + " ";
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
                Tariff tariff = new Tariff();
                tariff.setIdTariff(rs.getInt("idTariff"));
                tariff.setNameTariff(rs.getString("nameTariff"));
                tariff.setPrice(rs.getInt("Price"));
                tariff.setIdArea(rs.getInt("idArea"));
                getTariffArr.add(tariff);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getTariffArr;
    }
}
