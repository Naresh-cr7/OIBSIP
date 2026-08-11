package com.online.reservation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:reservation.db";

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(URL);

        } catch (SQLException e) {

            System.out.println(
                    "Database Connection Failed!");

            e.printStackTrace();

            return null;
        }
    }

    
}
