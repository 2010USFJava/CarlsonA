package com.Revature;

public class JointAccount extends Account {
	private Customer secondAccountHolder;
	
	
	public JointAccount(Customer accountHolder,Customer secondAccountHolder) {
		super(accountHolder);
		this.secondAccountHolder = secondAccountHolder;
	}
	
	public JointAccount(String account1FirstName,String account1LastName,String account2FirstName,String account2LastName) {
		super(account1FirstName,account1LastName);
		secondAccountHolder=new Customer(account2FirstName,account2LastName);
		
		
	}
	
	public JointAccount(String account1FirstName,String account1MiddleName,String account1LastName,String account2FirstName, String account2MiddleName,String account2LastName) {
		super(account1FirstName,account1MiddleName,account1LastName);
		secondAccountHolder=new Customer(account2FirstName,account1MiddleName,account2LastName);
		
		
	}



}
