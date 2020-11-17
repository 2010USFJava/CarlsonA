package com.revature.accounts;


import com.revature.Users.Customer;

public class SingleAccount extends AbstractAccount {
	/*Considering the deadlines, I'm leaving this in place so
	*that working code will continue to function.
	*However, with the customer set upgrade there is technically
	*no reason for me to store this information separately
	*/
	
	private static final long serialVersionUID = -6636853036712054421L;
	private Customer accountHolder;

	public SingleAccount(Customer accountHolder) {
		super(accountHolder);
		this.accountHolder=accountHolder;
	}
	
	public SingleAccount(int id, long balance, Customer accountHolder,AccountStatusEnum status) {
		super(id, balance,accountHolder,status);
		this.accountHolder=accountHolder;
	}
	
	
	
	
}
