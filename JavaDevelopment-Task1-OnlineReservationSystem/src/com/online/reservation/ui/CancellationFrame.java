package com.online.reservation.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.online.reservation.dao.ReservationDAO;

public class CancellationFrame extends JFrame {
	
	 private static final long serialVersionUID = 1L;


    private JTextField txtPNR;
    private JTextField txtPassengerName;
    private JTextField txtTrainName;
    private JTextField txtClassType;
    private JTextField txtJourneyDate;
    private JTextField txtSource;
    private JTextField txtDestination;

    private JButton btnFetch;
    private JButton btnCancel;

    private String currentPNR;

    public CancellationFrame() {

        setTitle("Online Reservation System - Cancellation");

        setSize(550, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        JLabel lblTitle =
                new JLabel("TICKET CANCELLATION");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        add(lblTitle, gbc);

        JLabel lblPNR =
                new JLabel("PNR Number:");

        txtPNR =
                new JTextField(15);

        btnFetch =
                new JButton("Fetch Ticket");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        add(lblPNR, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;

        add(txtPNR, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        add(btnFetch, gbc);

        JLabel lblPassenger =
                new JLabel("Passenger Name:");

        txtPassengerName =
                new JTextField(15);

        txtPassengerName.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 3;

        add(lblPassenger, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;

        add(txtPassengerName, gbc);

        JLabel lblTrain =
                new JLabel("Train Name:");

        txtTrainName =
                new JTextField(15);

        txtTrainName.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 4;

        add(lblTrain, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;

        add(txtTrainName, gbc);

        JLabel lblClass =
                new JLabel("Class Type:");

        txtClassType =
                new JTextField(15);

        txtClassType.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 5;

        add(lblClass, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;

        add(txtClassType, gbc);

        JLabel lblDate =
                new JLabel("Journey Date:");

        txtJourneyDate =
                new JTextField(15);

        txtJourneyDate.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 6;

        add(lblDate, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;

        add(txtJourneyDate, gbc);

        JLabel lblSource =
                new JLabel("Source:");

        txtSource =
                new JTextField(15);

        txtSource.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 7;

        add(lblSource, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;

        add(txtSource, gbc);

        JLabel lblDestination =
                new JLabel("Destination:");

        txtDestination =
                new JTextField(15);

        txtDestination.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 8;

        add(lblDestination, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;

        add(txtDestination, gbc);

        btnCancel =
                new JButton("Confirm Cancellation");

        btnCancel.setEnabled(false);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;

        add(btnCancel, gbc);

        btnFetch.addActionListener(
                e -> fetchTicket());

        btnCancel.addActionListener(
                e -> cancelTicket());
    }

    private void fetchTicket() {

        String pnr = txtPNR.getText().trim();

        if (pnr.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter PNR.");

            return;
        }

        ReservationDAO reservationDAO =
                new ReservationDAO();

        String[] reservation =
                reservationDAO.getReservation(pnr);

        if (reservation != null) {

            txtPassengerName.setText(reservation[0]);
            txtTrainName.setText(reservation[1]);
            txtClassType.setText(reservation[2]);
            txtJourneyDate.setText(reservation[3]);
            txtSource.setText(reservation[4]);
            txtDestination.setText(reservation[5]);

            currentPNR = pnr;

            btnCancel.setEnabled(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "PNR not found.");

            clearBookingDetails();

            currentPNR = null;

            btnCancel.setEnabled(false);
        }
    }

    private void cancelTicket() {

        if (currentPNR == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fetch a valid ticket first.");

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel this ticket?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION);

        if (choice ==
                JOptionPane.YES_OPTION) {

            ReservationDAO reservationDAO =
                    new ReservationDAO();

            boolean success =
                    reservationDAO.cancelTicket(
                            currentPNR);

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ticket Cancelled Successfully.");

                clearBookingDetails();

                txtPNR.setText("");

                currentPNR = null;

                btnCancel.setEnabled(false);

                dispose();
                ReservationFrame reservationFrame =    new ReservationFrame();

                reservationFrame.setVisible(true);
            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Cancellation Failed.");
            }
        }
        
        
    }

    private void clearBookingDetails() {

        txtPassengerName.setText("");
        txtTrainName.setText("");
        txtClassType.setText("");
        txtJourneyDate.setText("");
        txtSource.setText("");
        txtDestination.setText("");
    }
}