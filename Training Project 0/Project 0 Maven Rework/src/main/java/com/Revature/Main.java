package com.Revature;

import com.Revature.Meta.Bank;
import com.Revature.Meta.FileHandler;
import com.Revature.Users.Employee;
import com.Revature.Users.LoginInfo;

public class Main {
	

	public static void main(String[] args) {
		FileHandler.loadAll();
//		
//		Employee emp =new Employee("TESTADMIN","","Armando");
//		LoginInfo login = new LoginInfo("testadmin","testadmin",emp);
//		Employee.createAdmin(emp, login);
//		Employee.createEmployee(emp, login);

//		FileHandler.saveAll();
		
		
		Bank bank = new Bank();
		
		
		bank.startPage();
		FileHandler.saveAll();

	}

}
