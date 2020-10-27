package com.Revature;

public class JointAccount extends Account {
	private Customer secondAccountHolder;
	
	
	public JointAccount(Customer accountHolder,Customer secondAccountHolder) {
		super(accountHolder);
		this.secondAccountHolder = secondAccountHolder;
	}

}
