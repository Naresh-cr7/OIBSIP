package com.atm;

import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner sc;

    public ATM(Bank bank) {
        this.bank = bank;
        this.sc = new Scanner(System.in);
    }

    public Account login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter User ID: ");
            String userID = sc.nextLine();

            System.out.print("Enter PIN: ");

            int pin;

            try {
                pin = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid PIN! Please enter numbers only.");
                continue;
            }

            Account account = bank.findAccount(userID, pin);

            if (account != null) {
                System.out.println("Login Successful!");
                return account;
            }

            attempts++;

            System.out.println("Invalid User ID or PIN.");
            System.out.println("Attempts Remaining: " + (3 - attempts));
        }

        System.out.println("Access Denied!");
        return null;
    }

    public void showMenu(Account account) {

        boolean flag = true;

        do {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose your option: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Choice! Please enter numbers only.");
                continue;
            }

            switch (choice) {

            case 1 -> {

                if (account.getTransactions().isEmpty()) {

                    System.out.println("No Transactions Found.");

                } else {

                    System.out.println("\n===== Transaction History =====");

                    for (Transaction transaction : account.getTransactions()) {
                        System.out.println(transaction);
                    }

                }

            }

            case 2 -> {

                System.out.print("Enter Amount: ");

                double amount;

                try {
                    amount = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Amount! Please enter numbers only.");
                    continue;
                }

                if (amount <= 0) {

                    System.out.println("Invalid Amount!");

                } else if (account.getBalance() >= amount) {

                    account.withdraw(amount);
                    account.addTransaction(new Transaction("Withdraw", amount));

                    System.out.println("Amount Withdrawn Successfully.");
                    System.out.println("Current Balance : ₹" + account.getBalance());

                } else {

                    System.out.println("Insufficient Funds!");

                }

            }

            case 3 -> {

                System.out.print("Enter Amount: ");

                double amount;

                try {
                    amount = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Amount! Please enter numbers only.");
                    continue;
                }

                if (amount <= 0) {

                    System.out.println("Invalid Amount!");

                } else {

                    account.deposit(amount);
                    account.addTransaction(new Transaction("Deposit", amount));

                    System.out.println("Amount Deposited Successfully.");
                    System.out.println("Current Balance : ₹" + account.getBalance());

                }

            }

            case 4 -> {

                System.out.print("Enter Receiver User ID: ");
                String receiverId = sc.nextLine();

                Account receiver = bank.findAccount(receiverId);

                if (receiver == null) {

                    System.out.println("Receiver Account Not Found!");

                } else if (receiver == account) {

                    System.out.println("You cannot transfer money to your own account.");

                } else {

                    System.out.print("Enter Transfer Amount: ");

                    double amount;

                    try {
                        amount = Double.parseDouble(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Amount! Please enter numbers only.");
                        continue;
                    }

                    if (amount <= 0) {

                        System.out.println("Invalid Amount!");

                    } else if (account.getBalance() >= amount) {

                        account.withdraw(amount);
                        receiver.deposit(amount);

                        account.addTransaction(new Transaction("Transfer To " + receiverId, amount));
                        receiver.addTransaction(new Transaction("Received From " + account.getUserID(), amount));

                        System.out.println("Amount Transferred Successfully.");
                        System.out.println("Current Balance : ₹" + account.getBalance());

                    } else {

                        System.out.println("Insufficient Funds!");

                    }

                }

            }

            case 5 -> {

                System.out.println("Thank You For Using ATM.");
                flag = false;

            }

            default -> {

                System.out.println("Invalid Choice!");

            }

            }

        } while (flag);

    }
}