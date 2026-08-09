package com.online.reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.online.reservation.database.DBConnection;

public class ReservationDAO {

    public boolean bookTicket(
            String pnr,
            String passengerName,
            int trainNumber,
            String trainName,
            String classType,
            String journeyDate,
            String source,
            String destination) {

        String sql =
                "INSERT INTO reservations "
                + "(pnr, passenger_name, train_number, train_name, "
                + "class_type, journey_date, source, destination) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt =
                     con.prepareStatement(sql)) {

            pstmt.setString(1, pnr);
            pstmt.setString(2, passengerName);
            pstmt.setInt(3, trainNumber);
            pstmt.setString(4, trainName);
            pstmt.setString(5, classType);
            pstmt.setString(6, journeyDate);
            pstmt.setString(7, source);
            pstmt.setString(8, destination);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println("Booking failed!");
            e.printStackTrace();

            return false;
        }
    }

    public String[] getReservation(String pnr) {

        String sql =
                "SELECT passenger_name, train_name, "
                + "class_type, journey_date, source, "
                + "destination "
                + "FROM reservations WHERE pnr = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt =
                     con.prepareStatement(sql)) {

            pstmt.setString(1, pnr);

            try (ResultSet rs =
                    pstmt.executeQuery()) {

                if (rs.next()) {

                    String[] reservation =
                            new String[6];

                    reservation[0] =
                            rs.getString("passenger_name");

                    reservation[1] =
                            rs.getString("train_name");

                    reservation[2] =
                            rs.getString("class_type");

                    reservation[3] =
                            rs.getString("journey_date");

                    reservation[4] =
                            rs.getString("source");

                    reservation[5] =
                            rs.getString("destination");

                    return reservation;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to fetch reservation!");

            e.printStackTrace();
        }

        return null;
    }

    public boolean cancelTicket(String pnr) {

        String sql =
                "DELETE FROM reservations "
                + "WHERE pnr = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt =
                     con.prepareStatement(sql)) {

            pstmt.setString(1, pnr);

            int rows =
                    pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Cancellation failed!");

            e.printStackTrace();

            return false;
        }
    }
}