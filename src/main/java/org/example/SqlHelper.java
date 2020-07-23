package org.example;

import java.sql.*;

public class SqlHelper {
    private static final String HOST = "jdbc:postgresql://localhost:5432/test";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123";

    public Connection getConnection() {
        return connection;
    }

    private Connection connection;

    public SqlHelper(){
        try {
            connection = DriverManager.getConnection(HOST,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        SqlHelper sqlHelper = new SqlHelper();
//        String query = "SELECT * FROM public.\"test\"";
//
//        try {
//            Statement statement = sqlHelper.getConnection().createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            String s = resultSet.getString("id");
//            System.out.println(s);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
}
