package com.univ.webService.dataConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataConnection {

    public static Connection getDBConnection() {
        Connection dbConnection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "Alex","Hardpassword1");
            return dbConnection;
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }
}
