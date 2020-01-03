package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil { //DB와 연동되는 부분을 정의

    public static Connection getConnection() { //접속한 상태를 반환
        try {
            String dbURL = "jdbc:mysql://localhost:3306/amado?characterEncoding=UTF-8&serverTimezone=UTC";
            String dbID = "root";
            String dbPassword = "1q2w3e4r1!";
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException error");
        } catch (SQLException e) {
            System.err.println("SQLException error");
        }
        return null;
    }
}
