package com.atm;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private String userID;
	private int pin;
	private double balance;
	private List<Transaction> transactions;

	public String getUserID() {
		return userID;
	}

	public int getPin() {
		return pin;
	}

	public double getBalance() {
		return balance;
	}

	public Account(String userID, int pin, double balance) {
		this.userID = userID;
		this.pin = pin;
		this.balance = balance;
		transactions = new ArrayList<>();
	}

	public void deposit(double amount) {
		this.balance = this.balance + amount;
	}

	public boolean withdraw(double amount) {
		if (amount <= balance) {
			this.balance = this.balance - amount;
			return true;
		}
		return false;
	}

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}
}
