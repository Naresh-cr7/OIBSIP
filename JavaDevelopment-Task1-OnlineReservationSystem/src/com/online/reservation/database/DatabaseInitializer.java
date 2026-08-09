package com.online.reservation.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT UNIQUE NOT NULL, "
                + "password TEXT NOT NULL"
                + ")";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {

            stmt.execute(sql);

            System.out.println("Users table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDefaultUser() {

        String checkUser =
                "SELECT * FROM users WHERE username = ?";

        String insertUser =
                "INSERT INTO users(username, password) VALUES(?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkStmt =
                     con.prepareStatement(checkUser)) {

            checkStmt.setString(1, "admin");

            try (ResultSet rs = checkStmt.executeQuery()) {

                if (rs.next()) {

                    System.out.println("Username already exists.");

                } else {

                    try (PreparedStatement insertStmt =
                                 con.prepareStatement(insertUser)) {

                        insertStmt.setString(1, "admin");
                        insertStmt.setString(2, "admin123");

                        insertStmt.executeUpdate();

                        System.out.println(
                                "Default user inserted successfully.");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTrainsTable() {

        String sql = "CREATE TABLE IF NOT EXISTS trains ("
                + "train_number INTEGER PRIMARY KEY, "
                + "train_name TEXT NOT NULL"
                + ")";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {

            stmt.execute(sql);

            System.out.println("Trains table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSampleTrains() {

        String checkTrain =
                "SELECT * FROM trains WHERE train_number = ?";

        String insertTrain =
                "INSERT INTO trains(train_number, train_name) VALUES(?, ?)";

        int[] trainNumbers = {
                12623,
                12627,
                12675,
                22691
        };

        String[] trainNames = {
                "Chennai Express",
                "Karnataka Express",
                "Kovai Express",
                "Rajdhani Express"
        };

        try (Connection con = DBConnection.getConnection()) {

            for (int i = 0; i < trainNumbers.length; i++) {

                try (PreparedStatement checkStmt =
                             con.prepareStatement(checkTrain)) {

                    checkStmt.setInt(1, trainNumbers[i]);

                    try (ResultSet rs = checkStmt.executeQuery()) {

                        if (!rs.next()) {

                            try (PreparedStatement insertStmt =
                                         con.prepareStatement(insertTrain)) {

                                insertStmt.setInt(1, trainNumbers[i]);
                                insertStmt.setString(2, trainNames[i]);

                                insertStmt.executeUpdate();
                            }
                        }
                    }
                }
            }

            System.out.println(
                    "Sample trains inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createReservationsTable() {

        String sql = "CREATE TABLE IF NOT EXISTS reservations ("
                + "pnr TEXT PRIMARY KEY, "
                + "passenger_name TEXT NOT NULL, "
                + "train_number INTEGER NOT NULL, "
                + "train_name TEXT NOT NULL, "
                + "class_type TEXT NOT NULL, "
                + "journey_date TEXT NOT NULL, "
                + "source TEXT NOT NULL, "
                + "destination TEXT NOT NULL"
                + ")";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {

            stmt.execute(sql);

            System.out.println(
                    "Reservations table created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}