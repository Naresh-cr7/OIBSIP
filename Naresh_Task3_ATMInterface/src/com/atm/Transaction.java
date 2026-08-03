package com.atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

	private String type;
	private double amount;
	private String dateTime;

	public Transaction(String type, double amount) {
		this.type = type;
		this.amount = amount;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

		this.dateTime = LocalDateTime.now().format(formatter);
	}

	public String getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public String getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
		return type + " : " + amount + " : " + dateTime;
	}

}
