package com.Revature.AccountInfo;

import java.io.Serializable;

import com.Revature.AccountInfo.Account.AccountTypeEnum;
import com.Revature.Users.Customer;
import com.Revature.Users.User;

public class JointAccount extends Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9140192101113627345L;
	private Customer secondAccountHolder;
	
	
	public JointAccount(Customer accountHolder,Customer secondAccountHolder) {
		super(accountHolder);
		this.setSecondAccountHolder(secondAccountHolder);
		setAccountType(AccountTypeEnum.JOINT);
	}
	
	public JointAccount(Customer accountHolder) {
		super(accountHolder);
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
		String output=getCustomerPersonalData(this.getAccountHolder())+"\n";
		if (secondAccountHolder.equals(null)) {
			output+="No second account holder registered at this time";
		} else {
			output+= getCustomerPersonalData(this.getSecondAccountHolder());
		}
		return output;
	}
	
	



}
