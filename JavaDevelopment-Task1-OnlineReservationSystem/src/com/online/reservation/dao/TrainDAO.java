package com.online.reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.online.reservation.database.DBConnection;

public class TrainDAO {

    public String getTrainName(int trainNumber) {

        String sql =
                "SELECT train_name FROM trains WHERE train_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, trainNumber);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getString("train_name");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}