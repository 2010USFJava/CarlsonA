package com.Revature;

public class Account {
	private int accountId;
	private double balance=0;
	private AccountStatusEnum accountStatus=AccountStatusEnum.IN_APPLICATION;
	private Customer accountHolder;
	//tracker assist
	private static int idTracker=0;
	
	//constructors
	public Account(Customer accountHolder) {
		incrementId();
		this.accountHolder=accountHolder;

	}
	
	public Account(String firstName, String lastName) {
		 this(new Customer(firstName,lastName));
	}
	
	public Account(String firstName,  String middleName,String lastName) {
		 this(new Customer(firstName,middleName,lastName));
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
	
	//balance adjustments
	public double withdraw(double money) {
		if(balance<money) {
			money=balance;
			System.out.println("Not enough money in account. Withdrawing maximum amount: "+money);
		}
		balance-=money;
		return money;
	}
	
	public void deposit(double money) {
		balance+=money;
	}
	
	
	public double transferMoneyToAccount(Account account, double money) {
//may want to add upper limit checker here
		double amountTransfered=withdraw(money);
		account.deposit(amountTransfered);
		return amountTransfered;
	}
	

}
