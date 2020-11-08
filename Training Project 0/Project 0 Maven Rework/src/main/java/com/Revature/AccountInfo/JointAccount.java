package com.Revature.AccountInfo;

import java.io.Serializable;

import com.Revature.Meta.FileHandler;
import com.Revature.Users.Customer;

public class JointAccount extends Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9140192101113627345L;
	private Customer secondAccountHolder;
	
	
	public JointAccount(Customer accountHolder,Customer secondAccountHolder) {
		super(accountHolder);
		this.setSecondAccountHolder(secondAccountHolder);
		updateCustAcctMaps(accountHolder);
		updateCustAcctMaps(secondAccountHolder);
		setAccountType(AccountTypeEnum.JOINT);
	}
	
	public JointAccount(Customer accountHolder) {
		super(accountHolder);
		updateCustAcctMaps(accountHolder);
		setAccountType(AccountTypeEnum.JOINT);
	}


	
	//getter and setter
	public Customer getSecondAccountHolder() {
		return secondAccountHolder;
		
	}

	public void setSecondAccountHolder(Customer secondAccountHolder) {
		Customer oldSecondAccountHolder=this.secondAccountHolder;
		
		this.secondAccountHolder = secondAccountHolder;
		updateCustAcctMaps(secondAccountHolder);
		removeAdditionalHolderCustAcctMaps(oldSecondAccountHolder);
		FileHandler.saveAll();
		
		
	}
	
	private void removeAdditionalHolderCustAcctMaps(Customer oldSecondAccountHolder) {
		CustomerAccountRelationship.removeCustomerFromAccount(oldSecondAccountHolder, this);
		FileHandler.saveAll();
		
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
