package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.PeopleInRegion;
import com.univ.webService.dataModel.PeopleInTariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleInRegionDAO {
    public List<PeopleInRegion> getPeopleFromDB(int idPeople, int idArea, int numberOfPeople, int idWeek) {
        List<PeopleInRegion> getPeopleArr = new ArrayList<>();

        Connection connection = DataConnection.getDBConnection();

        String sqlQueryPeopleInRegion = "SELECT * FROM PeopleInRegion Where";
        if (idPeople == -1) {
            sqlQueryPeopleInRegion += " idPeopleInRegion LIKE '%' ";
        } else {
            sqlQueryPeopleInRegion += " idPeopleInRegion = " + idPeople + " ";
        }
        sqlQueryPeopleInRegion += "AND";
        if (idArea == -1) {
            sqlQueryPeopleInRegion += " idArea LIKE '%' ";
        } else {
            sqlQueryPeopleInRegion += " idArea = " + idArea + " ";
        }
        sqlQueryPeopleInRegion += "AND";
        if (numberOfPeople == -1) {
            sqlQueryPeopleInRegion += " numberOfPeople LIKE '%' ";
        } else {
            sqlQueryPeopleInRegion += " numberOfPeople = " + numberOfPeople + " ";
        }
        sqlQueryPeopleInRegion += "AND";
        if (idWeek == -1) {
            sqlQueryPeopleInRegion += " idWeek Like '%'";
        } else {
            sqlQueryPeopleInRegion += " idWeek = " + idWeek;
        }

        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQueryPeopleInRegion);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PeopleInRegion people = new PeopleInRegion(rs.getInt("idPeopleInRegion"), rs.getInt("idArea"),
                        rs.getInt("numberOfPeople"), rs.getInt("idWeek"));
                getPeopleArr.add(people);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return getPeopleArr;
    }

}
