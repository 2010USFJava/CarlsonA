package pro0MavenBank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Revature.Users.Customer;
import com.Revature.Users.LoginInfo;
import com.Revature.Users.User;

public class LoginInfoTest {
	
//	Login data will only be saved if added to login map. Customer A defaults to NOT in map. Customer B defaults to in.
	
	private  static Customer custA = new Customer("Joe","Smith",true);
	private  static String usernameA="testGuestA";
	private static  String passwordA="pass";
	private  static LoginInfo logA=new LoginInfo(usernameA,passwordA,custA);
	

	private static  Customer custB = new Customer("Joe","Smith",true);
	private static String usernameB="testGuestB";
	private static String passwordB="pass";
	private static LoginInfo logB=new LoginInfo(usernameB,passwordB,custB);
	
	@BeforeClass
	public static void init() {
		logB.addToLoginMap();
	}
	
	@AfterClass
	public static void cleanUp(){
		logB.removeFromLoginMap();
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
		logA.addToLoginMap();
		boolean result=(LoginInfo.checkIfUsernameIsTaken(usernameA));
		assertTrue(result);
		logA.removeFromLoginMap();
	}

	@Test
	public void checkRemoveFromMap() {
		logB.removeFromLoginMap();
		boolean result=(LoginInfo.checkIfUsernameIsTaken(usernameB));
		assertFalse(result);
		logB.addToLoginMap();
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
