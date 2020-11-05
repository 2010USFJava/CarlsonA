package pro0MavenBank;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Revature.Users.Customer;
import com.Revature.Users.LoginInfo;

public class LoginInfoTest {
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
	public void checkAddFunction() {
		logA.addToLoginMap();
		boolean result=(LoginInfo.checkIfUsernameIsTaken(usernameA));
		assertTrue(result);
		logA.removeFromLoginMap();
	}

	@Test
	public void checkRemoveFunctionFunction() {
		logB.removeFromLoginMap();
		boolean result=(LoginInfo.checkIfUsernameIsTaken(usernameB));
		assertFalse(result);
		logB.addToLoginMap();
	}
	
	

}
