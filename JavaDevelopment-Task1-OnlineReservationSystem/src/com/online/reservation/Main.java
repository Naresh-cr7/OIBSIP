package com.online.reservation;

import javax.swing.SwingUtilities;

import com.online.reservation.database.DatabaseInitializer;
import com.online.reservation.ui.LoginFrame;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer db =
                new DatabaseInitializer();

        db.createUsersTable();
        db.insertDefaultUser();

        db.createTrainsTable();
        db.insertSampleTrains();

        db.createReservationsTable();

        SwingUtilities.invokeLater(() -> {

            LoginFrame loginFrame =
                    new LoginFrame();

            loginFrame.setVisible(true);
        });
    }
}