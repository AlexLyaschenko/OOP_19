package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.PeopleInRegion;
import com.univ.webService.dataModel.PeopleInTariff;
import com.univ.webService.servlet.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleInRegionDAO {
    public List<PeopleInRegion> getPeopleFromDB(int idPeople, int idArea, int numberOfPeople, int idWeek) {

        final String sqlQuery = "SELECT * FROM PeopleInRegion WHERE idPeopleInRegion " +
                (idPeople == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idPeople) +
                " AND idArea " + (idArea == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idArea) +
                " AND numberOfPeople " + (numberOfPeople == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + numberOfPeople) +
                " AND idWeek " + (idWeek == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idWeek);


        List<PeopleInRegion> getPeopleArr = new ArrayList<>();
        Connection connection = DataConnection.getDBConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PeopleInRegion people = new PeopleInRegion(rs.getInt("idPeopleInRegion"), rs.getInt("idArea"),
                        rs.getInt("numberOfPeople"), rs.getInt("idWeek"));
                getPeopleArr.add(people);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getPeopleArr;
    }

    public void setUserToDB(int idArea, int numberOfPeople, int idWeek) {
        String sqlQueryHistory = "INSERT INTO PeopleInRegion (idArea, numberOfPeople, idWeek) VALUES ('" + idArea + "', '" +
                numberOfPeople + "', '" + idWeek + "')";
        DataConnection.insertToDB(sqlQueryHistory);
    }

}
