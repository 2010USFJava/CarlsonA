package com.Revature;

import com.Revature.User.UserTypeEnum;

public class Customer extends User {
	
	{setUserType(UserTypeEnum.CUSTOMER);}
	

	public Customer(String firstName,String middleName,String lastName) {
		super(firstName,middleName,lastName);
		
	}
	
	public Customer(String firstName, String lastName) {
		super(firstName,lastName);
	}
	

	//account interaction
	public Account createAccount() {
		Account acct= new Account(this);
		return acct;
		
	}
	
	public Account createAccount(Customer cust) {
		Account acct;
		if (cust.equals(this)) {
			acct=createAccount();
		} else {
			acct= new JointAccount(this,cust);
			
		}
		return acct;
		
	}

}
