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

        final String sqlQuery = String.format("SELECT * FROM PeopleInRegion WHERE idPeopleInRegion %s AND idArea %s AND numberOfPeople %s AND idWeek %s",
                (idPeople == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idPeople),
                (idArea == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idArea),
                (numberOfPeople == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + numberOfPeople),
                (idWeek == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idWeek));
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
        String sqlQueryHistory = String.format("INSERT INTO PeopleInRegion (idArea, numberOfPeople, idWeek) VALUES ('%d'" +
                ", '%d', '%d')", idArea, numberOfPeople, idWeek);
        DataConnection.insertToDB(sqlQueryHistory);
    }

}
