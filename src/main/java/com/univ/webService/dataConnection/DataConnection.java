package com.univ.webService.dataConnection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;


public class DataConnection {

    private static final DataSource datasource = createDataSource();

    public static Connection getDBConnection() {
        try {
            return datasource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static DataSource createDataSource() {
        Properties env = loadJdbcPropertiesFromClassPath();
        BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        datasource.setUrl(env.getProperty("jdbc.url"));
        datasource.setUsername(env.getProperty("jdbc.username"));
        datasource.setPassword(env.getProperty("jdbc.password"));
        return datasource;
    }

    private static Properties loadJdbcPropertiesFromClassPath() {
        Properties properties = new Properties();
        properties.setProperty("jdbc.driverClassName", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/testDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        properties.setProperty("jdbc.username", "Alex");
        properties.setProperty("jdbc.password", "Hardpassword1");
        return properties;
    }

    public static void insertToDB(String sqlQuery) {
        Connection connection = DataConnection.getDBConnection();
        try {
            PreparedStatement stat = connection.prepareStatement(sqlQuery);
            stat.execute();
        } catch (SQLException e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDB(String sqlQuery) {
        Connection conn = DataConnection.getDBConnection();
        try {
            Statement stat = conn.createStatement();
            stat.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.getMessage();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
