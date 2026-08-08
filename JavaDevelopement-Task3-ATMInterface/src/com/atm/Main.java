package com.atm;

public class Main {

	public static void main(String[] args) {

		Bank bank = new Bank();

		bank.addAccount(new Account("Naresh", 1111, 10000));
		bank.addAccount(new Account("Rithick", 2222, 20000));
		bank.addAccount(new Account("Thanigai", 3333, 25000));
		bank.addAccount(new Account("Sanjai", 4444, 5000));

		ATM atm = new ATM(bank);

		Account account = atm.login();

		if (account != null) {
			atm.showMenu(account);
		} else {
			System.out.println("Exiting Application...");
		}
	}
}