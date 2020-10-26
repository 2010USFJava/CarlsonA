package com.Revature;

public class Account {
	private int accountId;
	private double balance=0;
	private AccountStatusEnum accountStatus;
	
	private static int idTracker=0;
	
	//constructors
	public Account() {
		incrementId();
		accountStatus=AccountStatusEnum.IN_APPLICATION;
	}
	
	
	private void incrementId() {
		accountId=idTracker++;
	}
	
	//change account status
	public void approveAccount() {
		accountStatus=AccountStatusEnum.OPEN;
	}
	
	
	public void closeAccount() {
		accountStatus=AccountStatusEnum.CLOSED;
	}

}
