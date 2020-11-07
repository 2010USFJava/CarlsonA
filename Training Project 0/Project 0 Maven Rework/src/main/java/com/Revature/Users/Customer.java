
package com.Revature.Users;

import java.io.Serializable;
import java.util.ArrayList;

import com.Revature.AccountInfo.Account;
import com.Revature.AccountInfo.JointAccount;


public class Customer extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5844012040276987566L;
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
		
		if(userAccounts.size()==0) {
			System.out.println("This user has no accounts at this time");
			
		}
		return userAccounts;
	}

	//account interaction
	public Account createAccount() {
		return createAccount(this);
		
	}
	
	public Account createAccount(Customer cust) {
		Account acct;
		if (cust.equals(this)) {
			acct=new Account(this);
		} else {
			acct= new JointAccount(this,cust);
			
		}
		userAccounts.add(acct);
		return acct;
		
	}
	
	public Account createJointAccount(Customer cust) {
		Account acct;
		if (cust.equals(this)) {
			acct=new JointAccount(this);
		} else {
			acct= new JointAccount(this,cust);
			
		}
		userAccounts.add(acct);
		return acct;
		
	}
	
	public Account createJointAccount() {
		return createJointAccount(this);
	}
	

}
