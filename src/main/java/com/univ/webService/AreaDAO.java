package com.univ.webService;

import java.sql.*;
import java.util.ArrayList;

class AreaDAO {
    ArrayList<Area> getAreaFromDB(int idArea, String nameArea) {
        ArrayList<Area> getAreaArr = new ArrayList<>();
        DataConnection dConn = new DataConnection();
        Connection conn = dConn.getDBConnection();
        String sqlQueryArea = "SELECT idArea, nameArea FROM Area WHERE";
        if (idArea == -1) {
            sqlQueryArea += " idArea LIKE '%' ";
        } else {
            sqlQueryArea += " idArea = " + idArea  + " ";
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
                Area area = new Area();
                area.setIdArea(rs.getInt("idArea"));
                area.setNameArea(rs.getString("nameArea"));
                getAreaArr.add(area);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getAreaArr;
    }

}
