package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.PeopleInTariff;
import com.univ.webService.servlet.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleInTariffDAO {
    public List<PeopleInTariff> getPeople(int idPeople, int idTariff, int numberOfPeople, int idWeek) {

        final String sqlQuery = String.format("SELECT * FROM PeopleInTariff WHERE idPeopleInTariff %s AND idTariff %s AND numberOfPeople %s AND idWeek %s",
                (idPeople == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idPeople),
                (idTariff == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idTariff),
                (numberOfPeople == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + numberOfPeople),
                (idWeek == Constants.SELECT_ALL_INT ? "LIKE '%'" : "= " + idWeek));
        List<PeopleInTariff> getPeopleArr = new ArrayList<>();
        Connection connection = DataConnection.getDBConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PeopleInTariff people = new PeopleInTariff(rs.getInt("idPeopleInTariff"), rs.getInt("idTariff"),
                        rs.getInt("numberOfPeople"), rs.getInt("idWeek"));
                getPeopleArr.add(people);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getPeopleArr;
    }

    public void setUserToDB(int idPeopleInTariff, int idTariff, int numberOfPeople, int idWeek) {
        String sqlQueryHistory = String.format("INSERT INTO PeopleInTariff VALUES ('%d', '%d', '%d', '%d')",
                idPeopleInTariff, idTariff, numberOfPeople, idWeek);
        DataConnection.insertToDB(sqlQueryHistory);
    }
}
