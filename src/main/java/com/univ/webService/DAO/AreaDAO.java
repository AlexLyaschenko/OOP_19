package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.Area;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AreaDAO {
    public ArrayList<Area> getAreaFromDB(int idArea, String nameArea) {
        ArrayList<Area> getAreaArr = new ArrayList<>();

        Connection conn = DataConnection.getDBConnection();

        String sqlQueryArea = "SELECT idArea, nameArea FROM Area WHERE";
        if (idArea == -1) {
            sqlQueryArea += " idArea LIKE '%' ";
        } else {
            sqlQueryArea += " idArea = " + idArea + " ";
        }
        sqlQueryArea += "AND";
        if (nameArea.equals("")) {
            sqlQueryArea += " nameArea LIKE '%'";
        } else {
            sqlQueryArea += " nameArea = '" + nameArea + "'";
        }

        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sqlQueryArea);
            while (rs.next()) {
                Area area = new Area(rs.getInt("idArea"), rs.getString("nameArea"));
                getAreaArr.add(area);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getAreaArr;
    }

}
