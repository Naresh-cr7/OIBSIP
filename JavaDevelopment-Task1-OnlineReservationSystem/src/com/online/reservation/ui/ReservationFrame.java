package com.online.reservation.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.online.reservation.dao.ReservationDAO;
import com.online.reservation.dao.TrainDAO;

public class ReservationFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private JTextField txtPassengerName;
    private JTextField txtTrainNumber;
    private JTextField txtTrainName;
    private JComboBox<String> cmbClassType;
    private JTextField txtJourneyDate;
    private JTextField txtSource;
    private JTextField txtDestination;

    private JButton btnBook;
    private JButton btnCancel;

    public ReservationFrame() {

        setTitle("Online Reservation System - Reservation");

        setSize(550, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        JLabel lblTitle =
                new JLabel("TRAIN RESERVATION");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        add(lblTitle, gbc);

        JLabel lblPassengerName =
                new JLabel("Passenger Name:");

        txtPassengerName =
                new JTextField(18);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        add(lblPassengerName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;

        add(txtPassengerName, gbc);

        JLabel lblTrainNumber =
                new JLabel("Train Number:");

        txtTrainNumber =
                new JTextField(18);

        gbc.gridx = 0;
        gbc.gridy = 2;

        add(lblTrainNumber, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        add(txtTrainNumber, gbc);

        txtTrainNumber.getDocument()
                .addDocumentListener(
                        new DocumentListener() {

                            public void insertUpdate(
                                    DocumentEvent e) {

                                loadTrainName();
                            }

                            public void removeUpdate(
                                    DocumentEvent e) {

                                loadTrainName();
                            }

                            public void changedUpdate(
                                    DocumentEvent e) {

                                loadTrainName();
                            }
                        });

        JLabel lblTrainName =
                new JLabel("Train Name:");

        txtTrainName =
                new JTextField(18);

        txtTrainName.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 3;

        add(lblTrainName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;

        add(txtTrainName, gbc);

        JLabel lblClassType =
                new JLabel("Class Type:");

        cmbClassType =
                new JComboBox<>(
                        new String[]{
                                "Select here",
                                "Sleeper",
                                "AC 3 Tier",
                                "AC 2 Tier",
                                "First Class"
                        });

        cmbClassType.setSelectedIndex(0);

        gbc.gridx = 0;
        gbc.gridy = 4;

        add(lblClassType, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;

        add(cmbClassType, gbc);

        JLabel lblJourneyDate =
                new JLabel("Journey Date:");

        txtJourneyDate =
                new JTextField(18);

        gbc.gridx = 0;
        gbc.gridy = 5;

        add(lblJourneyDate, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;

        add(txtJourneyDate, gbc);

        JLabel lblSource = new JLabel("Source Station:");

        txtSource =
                new JTextField(18);

        gbc.gridx = 0;
        gbc.gridy = 6;

        add(lblSource, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;

        add(txtSource, gbc);

        JLabel lblDestination =
                new JLabel("Destination Station:");

        txtDestination =
                new JTextField(18);

        gbc.gridx = 0;
        gbc.gridy = 7;

        add(lblDestination, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;

        add(txtDestination, gbc);

        btnBook =
                new JButton("Book Ticket");

        btnCancel =
                new JButton("Cancel Booking");

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;

        add(btnBook, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;

        add(btnCancel, gbc);

        btnBook.addActionListener(
                e -> bookTicket());

        btnCancel.addActionListener(
                e -> {

                    CancellationFrame
                            cancellationFrame =
                            new CancellationFrame();

                    cancellationFrame
                            .setVisible(true);
                });
    }

    private void loadTrainName() {

        String trainNumberText =
                txtTrainNumber.getText().trim();

        if (trainNumberText.isEmpty()) {

            txtTrainName.setText("");

            return;
        }

        try {

            int trainNumber =
                    Integer.parseInt(
                            trainNumberText);

            TrainDAO trainDAO =
                    new TrainDAO();

            String trainName =
                    trainDAO.getTrainName(
                            trainNumber);

            if (trainName != null) {

                txtTrainName.setText(
                        trainName);

            } else {

                txtTrainName.setText("");
            }

        } catch (NumberFormatException e) {

            txtTrainName.setText("");
        }
    }

    private void bookTicket() {

        String passengerName =
                txtPassengerName
                        .getText()
                        .trim();

        String trainNumberText =
                txtTrainNumber
                        .getText()
                        .trim();

        String trainName =
                txtTrainName
                        .getText()
                        .trim();

        String classType =
                (String) cmbClassType
                        .getSelectedItem();

        String journeyDate =
                txtJourneyDate
                        .getText()
                        .trim();

        String source =
                txtSource
                        .getText()
                        .trim();

        String destination =
                txtDestination
                        .getText()
                        .trim();

        if (passengerName.isEmpty()
                || trainNumberText.isEmpty()
                || trainName.isEmpty()
                || journeyDate.isEmpty()
                || source.isEmpty()
                || destination.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all required fields.");

            return;
        }

        if (classType == null
                || classType.equals("Select here")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a class type.");

            return;
        }

        int trainNumber;

        try {

            trainNumber =
                    Integer.parseInt(
                            trainNumberText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Train number must be numeric.");

            return;
        }

        if (!journeyDate.matches(
                "\\d{2}-\\d{2}-\\d{4}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter date in DD-MM-YYYY format.");

            return;
        }

        String pnr =
                "PNR"
                + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

        ReservationDAO reservationDAO =
                new ReservationDAO();

        boolean success =
                reservationDAO.bookTicket(
                        pnr,
                        passengerName,
                        trainNumber,
                        trainName,
                        classType,
                        journeyDate,
                        source,
                        destination);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking Successful!\n\n"
                    + "PNR: " + pnr + "\n"
                    + "Passenger: "
                    + passengerName + "\n"
                    + "Train: "
                    + trainName + "\n"
                    + "Class: "
                    + classType + "\n"
                    + "Date: "
                    + journeyDate + "\n"
                    + "From: "
                    + source + "\n"
                    + "To: "
                    + destination);

            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking failed.");
        }
    }

    private void clearFields() {

        txtPassengerName.setText("");

        txtTrainNumber.setText("");

        txtTrainName.setText("");

        txtJourneyDate.setText("");

        txtSource.setText("");

        txtDestination.setText("");

        cmbClassType.setSelectedIndex(0);
    }
}