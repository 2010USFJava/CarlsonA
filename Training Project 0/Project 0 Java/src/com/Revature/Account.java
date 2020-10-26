package com.Revature;

public class Account {
	private int accountId;
	private Customer primaryCustomer;
	
	private static int idTracker=0;
	
	public Account(Customer primaryCustomer) {
		incrementId();
		this.primaryCustomer=primaryCustomer;
	}
	
	private void incrementId() {
		accountId=idTracker++;
	}

}
