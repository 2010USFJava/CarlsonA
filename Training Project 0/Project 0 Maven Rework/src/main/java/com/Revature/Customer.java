package com.Revature;

import java.util.ArrayList;


public class Customer extends User {
	private ArrayList<Account> userAccounts=new ArrayList<>();
	
	{setUserType(UserTypeEnum.CUSTOMER);}
	

	public Customer(String firstName,String middleName,String lastName) {
		super(firstName,middleName,lastName);
		
	}
	
	public Customer(String firstName, String lastName) {
		super(firstName,lastName);
	}
	
	//getters and setters
	public ArrayList<Account> getUserAccounts() {
		return userAccounts;
	}

	//account interaction
	public Account createAccount() {
		Account acct= new Account(this);
		userAccounts.add(acct);
		return acct;
		
	}
	
	public Account createAccount(Customer cust) {
		Account acct;
		if (cust.equals(this)) {
			acct=createAccount();
		} else {
			acct= new JointAccount(this,cust);
			userAccounts.add(acct);
			
		}
		return acct;
		
	}

}
