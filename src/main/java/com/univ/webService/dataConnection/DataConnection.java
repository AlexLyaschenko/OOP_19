package com.univ.webService.dataConnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataConnection {

    public static Connection getDBConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        Context ctx;
        DataSource ds;
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/appname");
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
