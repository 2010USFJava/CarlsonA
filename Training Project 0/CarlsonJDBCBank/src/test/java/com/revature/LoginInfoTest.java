package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.Users.Customer;
import com.revature.Users.LoginInfo;
import com.revature.Users.User;


public class LoginInfoTest {
	
//	Login data will only be saved if added to login map. Customer A defaults to NOT in map. Customer B defaults to in.
	
	
	private  static String usernameA="testGuestA";
	private static  String passwordA="pass";
	private  static Customer custA = new Customer(333,"Joe","Smith",usernameA,passwordA);
//	private  static LoginInfo logA=new LoginInfo(usernameA,passwordA,custA);
	


	public static String usernameB="testGuestB";
	public static String passwordB="pass";
	public static  Customer custB = new Customer(234,"Joe","Smith",usernameB,passwordB);
	//	public static LoginInfo logB=new LoginInfo(usernameB,passwordB,custB);
	
	@BeforeClass
	public static void init() {
		custB.getLoginInfo().addToLoginMap();
	}
	
	@AfterClass
	public static void cleanUp(){
		custB.getLoginInfo().removeFromLoginMap();
	}


	@Test
	public void userExistsCheckFail() {
		boolean result=(LoginInfo.checkIfUsernameIsTaken(usernameA));
		assertFalse(result);
	}
	
	@Test
	public void userExistsCheckPass() {
		boolean result=(LoginInfo.checkIfUsernameIsTaken(usernameB));

		assertTrue(result);
	}
	
	@Test
	public void checkAddToMap() {
		custA.getLoginInfo().addToLoginMap();
		boolean result=(LoginInfo.checkIfUsernameIsTaken(usernameA));
		assertTrue(result);
		custA.getLoginInfo().removeFromLoginMap();
	}

	@Test
	public void checkRemoveFromMap() {
		custB.getLoginInfo().removeFromLoginMap();
		boolean result=(LoginInfo.checkIfUsernameIsTaken(usernameB));
		assertFalse(result);
		custB.getLoginInfo().addToLoginMap();
	}
	
	@Test
	public void loginTestPass() {
		User testUser=LoginInfo.logIn(usernameB,passwordB);
		assertEquals(custB,testUser);
	}
	

	@Test
	public void loginTestFailNotInMap() {
		User testUser=LoginInfo.logIn(usernameA,passwordA);
		assertEquals(null,testUser);
	}
	
	@Test
	public void loginTestWrongPassword() {
		User testUser=LoginInfo.logIn(usernameB,"badPass");
		assertEquals(null,testUser);
	}
	
	

}
