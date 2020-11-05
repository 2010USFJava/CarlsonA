package pro0MavenBank;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.Revature.Users.Customer;
import com.Revature.Users.User;

public class CustomerTests {
	static Customer custA=new Customer("John","Doe");
	static Customer custB=new Customer("Apple","Hermin","Pie");

	@BeforeClass
	public static void loadCustomers() {
	}
	
	
	//general User tests
	@Test
	public void firstNameTest() {
		String expected="John";
		assertEquals(expected,custA.getFirstName());
	}
	
	@Test
	public void lastNameTest() {
		String expected="Doe";
		assertEquals(expected,custA.getLastName());
	}
	
	@Test
	public void middleNameTestNoMiddle() {
		String expected="";
		assertEquals(expected,custA.getMiddleName());
	}
	
	@Test
	public void middleNameTestYesMiddle() {
		String expected="Hermin";
		assertEquals(expected,custB.getMiddleName());
	}
	
	

}
