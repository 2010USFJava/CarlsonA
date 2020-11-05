package com.Revature.AccountInfo;

import com.Revature.AccountInfo.Account.AccountTypeEnum;
import com.Revature.Users.Customer;

public class JointAccount extends Account {
	private Customer secondAccountHolder;
	
	
	public JointAccount(Customer accountHolder,Customer secondAccountHolder) {
		super(accountHolder);
		this.setSecondAccountHolder(secondAccountHolder);
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
