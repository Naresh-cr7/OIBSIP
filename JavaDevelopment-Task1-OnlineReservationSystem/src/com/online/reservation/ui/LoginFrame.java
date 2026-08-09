package com.online.reservation.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.online.reservation.dao.UserDAO;

public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {

        setTitle("Online Reservation System - Login");

        setSize(420, 280);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel lblTitle =
                new JLabel("ONLINE RESERVATION SYSTEM");

        JLabel lblUsername =
                new JLabel("Username:");

        JLabel lblPassword =
                new JLabel("Password:");

        txtUsername =
                new JTextField(15);

        txtPassword =
                new JPasswordField(15);

        btnLogin =
                new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        add(lblTitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        add(lblUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;

        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;

        add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        add(btnLogin, gbc);

        btnLogin.addActionListener(e -> login());
    }

    private void login() {

        String username =
                txtUsername.getText().trim();

        String password =
                new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password.");

            return;
        }

        UserDAO userDAO =
                new UserDAO();

        boolean valid =
                userDAO.validateLogin(
                        username,
                        password);

        if (valid) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful!");

            dispose();

            ReservationFrame reservationFrame =
                    new ReservationFrame();

            reservationFrame.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.");
        }
    }
}