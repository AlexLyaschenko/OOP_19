package com.univ.webService.DAO;

import com.univ.webService.dataConnection.DataConnection;
import com.univ.webService.dataModel.PeopleInTariff;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleInTariffDAO {
    public List<PeopleInTariff> getPeople(int idPeople, int idTariff, int numberOfPeople, int idWeek) {
        List<PeopleInTariff> getPeopleArr = new ArrayList<>();

        Connection connection = DataConnection.getDBConnection();

        String sqlQueryPeopleInTariff = "SELECT * FROM PeopleInTariff Where";
        if (idPeople == -1) {
            sqlQueryPeopleInTariff += " idPeopleInTariff LIKE '%' ";
        } else {
            sqlQueryPeopleInTariff += " idPeopleInTariff = " + idPeople + " ";
        }
        sqlQueryPeopleInTariff += "AND";
        if (idTariff == -1) {
            sqlQueryPeopleInTariff += " idTariff LIKE '%' ";
        } else {
            sqlQueryPeopleInTariff += " idTariff = " + idTariff + " ";
        }
        sqlQueryPeopleInTariff += "AND";
        if (numberOfPeople == -1) {
            sqlQueryPeopleInTariff += " numberOfPeople LIKE '%' ";
        } else {
            sqlQueryPeopleInTariff += " numberOfPeople = " + numberOfPeople + " ";
        }
        sqlQueryPeopleInTariff += "AND";
        if (idWeek == -1) {
            sqlQueryPeopleInTariff += " idWeek LIKE '%'";
        } else {
            sqlQueryPeopleInTariff += " idWeek = " + idWeek;
        }

        try {
            PreparedStatement pstmt = connection.prepareStatement(sqlQueryPeopleInTariff);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PeopleInTariff people = new PeopleInTariff(rs.getInt("idPeopleInTariff"), rs.getInt("idTariff"),
                        rs.getInt("numberOfPeople"), rs.getInt("idWeek"));
                getPeopleArr.add(people);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getPeopleArr;
    }

    public void setUserToDB(int idPeopleInTariff, int idTariff, int numberOfPeople, int idWeek) {
        String sqlQueryHistory = "INSERT INTO PeopleInTariff VALUES ('" + idPeopleInTariff + "', '" + idTariff + "', '" + numberOfPeople + "', '" +
                idWeek + "')";
        DataConnection.insertToDB(sqlQueryHistory);
    }
}
