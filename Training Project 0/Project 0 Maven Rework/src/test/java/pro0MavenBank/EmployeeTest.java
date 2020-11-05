package pro0MavenBank;

import static org.junit.Assert.*;

import org.junit.Test;

import com.Revature.Users.Employee;

public class EmployeeTest {


	static Employee empA=new Employee("Tim","Tatk");
	
	@Test
	public void checkIfEmployeeIsCustomer() {
		assertFalse(empA.checkIfCustomer());
		
	}
	 
	@Test
	public void checkIfEmployeeIsEmployee() {
		assertTrue(empA.checkIfEmployee());
		
		
	}

}
