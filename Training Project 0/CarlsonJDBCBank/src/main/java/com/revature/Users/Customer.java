package com.revature.Users;

public class Customer extends User {
	
	
	public Customer(String firstname,String lastname) {
		super(firstname,lastname,UserTypeEnum.CUSTOMER);
	}

}
