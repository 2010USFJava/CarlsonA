package pro0MavenBank;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.Revature.AccountInfo.Account;
import com.Revature.Users.Customer;
import com.Revature.Users.LoginInfo;

public class CustomerAccountRelationshipTests {
	public static  Customer custB = LoginInfoTest.custB;
	public static LoginInfo logB=LoginInfoTest.logB;
	
	@BeforeClass
	public static void init() {
		logB.addToLoginMap();
	}
	
	@AfterClass
	public static void cleanUp(){
		logB.removeFromLoginMap();
	}
	
	@Test
	public void LoadFromOtherTestTest() {
		assertEquals("Joe",custB.getFirstName());
	}
	
	@Test	
	public void userExistsCheckPass() {
		boolean result=(LoginInfo.checkIfUsernameIsTaken(custB.getLoginInfo().getUsername()));

		assertTrue(result);
	}

}
