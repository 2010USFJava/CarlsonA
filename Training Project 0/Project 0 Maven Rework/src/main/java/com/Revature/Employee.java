package com.Revature;

import com.Revature.User.UserTypeEnum;

public class Employee extends User {
	
	{setUserType(UserTypeEnum.EMPLOYEE);}

	//constructor
	public Employee(String firstName,String middleName,String lastName) {
		super(firstName,middleName,lastName);
	}
	
	public Employee(String firstName,String lastName) {
		super(firstName,lastName);
	}

	
	
	//change account status
	public void changeAccountStatus(Account account, Account.AccountStatusEnum status) {
		account.changeStatus(status);
		
	}
	
	public String getAccountInfo(Account account) {
		String output=account.getCustomerPersonalData();
		return output;
		
	}

}
