package com.atm;

import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accounts;

	public Bank() {
		accounts = new ArrayList<>();
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	public Account findAccount(String userId, int pin) {

		for (Account account : accounts) {

			if (account.getUserID().equalsIgnoreCase(userId) && account.getPin() == pin) {
				return account;
			}
		}
		return null;
	}

	public Account findAccount(String userId) {

		for (Account account : accounts) {

			if (account.getUserID().equalsIgnoreCase(userId)) {
				return account;
			}
		}
		return null;
	}

}