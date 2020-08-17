package org.example;

import java.sql.*;

public class SqlHelper {
    private static final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";


    public Connection getConnection() {
        return connection;
    }

    private Connection connection;

    public SqlHelper() {
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String getRow(String sqlResponce, String row) {
        try {
            SqlHelper sqlHelper = new SqlHelper();
            Statement statement = sqlHelper.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlResponce);
            resultSet.next();
            return resultSet.getString(row);
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }
}
