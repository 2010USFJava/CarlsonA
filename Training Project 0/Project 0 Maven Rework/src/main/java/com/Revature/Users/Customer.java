
package com.Revature.Users;

import java.util.ArrayList;

import com.Revature.AccountInfo.Account;
import com.Revature.AccountInfo.JointAccount;


public class Customer extends User {
	private ArrayList<Account> userAccounts=new ArrayList<>();
	
	{setUserType(UserTypeEnum.CUSTOMER);}
	

	public Customer(String firstName,String middleName,String lastName) {
		super(firstName,middleName,lastName);
		
	}
	
	public Customer(String firstName,String middleName,String lastName,boolean isTest) {
		super(firstName,middleName,lastName,isTest);
		
	}
	
	public Customer(String firstName, String lastName) {
		super(firstName,lastName);
	}
	
	public Customer(String firstName, String lastName,boolean isTest) {
		super(firstName,lastName,isTest);
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
