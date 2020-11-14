package com.revature.Users;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void getFirstName() {
		User user = new Customer("Joe","Smith");
		String result = user.getFirstName();
		assertEquals("Joe",result);
	}
	
	@Test
	public void getLastName() {
		User user = new Customer("Joe","Smith");
		String result = user.getLastName();
		assertEquals("Smith",result);
	}
	
	@Test
	public void setFirstName() {
		User user = new Customer("Joe","Smith");
		user.setFirstName("Megan");
		assertEquals("Megan",user.getFirstName());
	}
	
	@Test
	public void setLastName() {
		User user = new Customer("Joe","Smith");
		user.setLastName("Peter");
		assertEquals("Peter",user.getLastName());
		
	}


}
