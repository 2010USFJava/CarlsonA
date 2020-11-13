package com.Revature;

import com.Revature.Meta.Bank;
import com.Revature.Meta.FileHandler;
import com.Revature.Users.Employee;
import com.Revature.Users.LoginInfo;

public class Main {
	//Known bug: Normal Employee has admin privileges
	
	/*Known bug: Applications approved through queue system do not update status
	 * Temp fix: Change account status through admin menu.
	 */

	public static void main(String[] args) {
		FileHandler.loadAll();
		
		

		/*Known bug: After running JUnit tests some items are overwritten and original data is lost
		*Slated Solution: Re-Examine where saves are made. Possibly only call save data in UI code.
		*Temporary Fix: After running unit tests. Run code below to recreate test employees. Tab out after first run.
		*/
		
		//		Creating admin employee
//		Employee emp =new Employee("TESTADMIN","","Armando");
//		LoginInfo login = new LoginInfo("testadmin","testadmin",emp);
//		Employee.createAdmin(emp, login);
//Creating normal Employee
//		Employee empb =new Employee("TestEmp","","Dr");
//		LoginInfo loginb = new LoginInfo("testemp","testemp",emp);
//		Employee.createEmployee(empb, loginb);

//		FileHandler.saveAll();
		
		
		Bank bank = new Bank();
		
		
		bank.startPage();
		FileHandler.saveAll();

	}

}
