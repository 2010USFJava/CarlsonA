package pro0MavenBank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.Revature.Users.Customer;
import com.Revature.Users.Employee;

public class CustomerTests {
	public static Customer custA=new Customer("John","Doe",true);
	public static Customer custB=new Customer("Apple","Hermin","Pie",true);

	
	
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
	
	//check that identifiers work
	@Test
	public void checkIfCustomerIsCustomer() {
		assertTrue(custA.checkIfCustomer());
		
	}
	
	@Test
	public void checkIfCustomerIsEmployee() {
		assertFalse(custA.checkIfEmployee());
		
		
	}


}
