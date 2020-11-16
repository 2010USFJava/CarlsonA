package com.revature.accounts;

import com.revature.Users.Customer;

public class JointAccount extends AbstractAccount {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1823100970366011642L;

	//constructors
	{setAccountType(AccountTypeEnum.JOINT);}
	public JointAccount(Customer accountHolder,Customer secondAccountHolder) {
		super(accountHolder);
		updateCustAcctMaps(secondAccountHolder);
		
	}

	public JointAccount(Customer customer) {
		super(customer);
	}

}
