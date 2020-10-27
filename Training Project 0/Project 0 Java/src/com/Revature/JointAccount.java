package com.Revature;

import com.Revature.Account.AccountTypeEnum;

public class JointAccount extends Account {
	private Customer secondAccountHolder;
	
	
	public JointAccount(Customer accountHolder,Customer secondAccountHolder) {
		super(accountHolder);
		this.setSecondAccountHolder(secondAccountHolder);
		setAccountType(AccountTypeEnum.JOINT);
	}
	
	public JointAccount(String account1FirstName,String account1LastName,String account2FirstName,String account2LastName) {
		super(account1FirstName,account1LastName);
		setSecondAccountHolder(new Customer(account2FirstName,account2LastName));
		setAccountType(AccountTypeEnum.JOINT);
		
		
	}
	
	public JointAccount(String account1FirstName,String account1MiddleName,String account1LastName,String account2FirstName, String account2MiddleName,String account2LastName) {
		super(account1FirstName,account1MiddleName,account1LastName);
		setSecondAccountHolder(new Customer(account2FirstName,account1MiddleName,account2LastName));
		setAccountType(AccountTypeEnum.JOINT);
		
		
	}

	
	//getter and setter
	public Customer getSecondAccountHolder() {
		return secondAccountHolder;
	}

	public void setSecondAccountHolder(Customer secondAccountHolder) {
		this.secondAccountHolder = secondAccountHolder;
	}
	
	public String getCustomerPersonalData() {
		String output=getCustomerPersonalData(this.getAccountHolder());
		output+= getCustomerPersonalData(this.getSecondAccountHolder());
		
		return output;
	}
	
	



}
