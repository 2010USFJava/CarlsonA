package com.Revature.Users;

import java.io.Serializable;

import com.Revature.AccountInfo.Account;
import com.Revature.AccountInfo.JointAccount;
import com.Revature.Meta.FileHandler;
import com.Revature.Meta.LogThis;
import com.Revature.Meta.LogThis.LevelEnum;

public class Employee extends User implements Serializable{
	private enum EmployeeLevelEnum{
		STANDARD,
		ADMIN;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2947355514565998586L;

	private EmployeeLevelEnum empLevel=EmployeeLevelEnum.STANDARD;
	
	
	{setUserType(UserTypeEnum.EMPLOYEE);}

	//constructor
	public Employee(String firstName,String middleName,String lastName) {
		super(firstName,middleName,lastName);
	}
	
	public Employee(String firstName,String lastName) {
		super(firstName,lastName);
	}

	public Employee(String firstName,String middleName,String lastName,boolean isTest) {
		super(firstName,middleName,lastName,isTest);
	}
	
	public Employee(String firstName,String lastName,boolean isTest) {
		super(firstName,lastName,isTest);
	}

	public static Employee createEmployee(Employee emp,LoginInfo login) {
		emp.setLoginInfo(login);
		login.addToLoginMap();
		FileHandler.saveAll();
		return emp;
	}
	
	public static Employee createAdmin(Employee emp,LoginInfo login) {
		emp=createEmployee(emp,login);
		emp.empLevel=EmployeeLevelEnum.ADMIN;
		FileHandler.saveAll();
		return emp;
		
	}
	
	//change account status
//	public void changeAccountStatus(Account account, Account.AccountStatusEnum status) {
//		account.changeStatus(status);
//		
//
//		FileHandler.saveAll();
//		
//	}
	
	public String getAccountInfo(Account account) {
		String output=account.getCustomerPersonalData();
		return output;
		
	}

	public boolean checkIfAdmin() {
		if(empLevel.equals(EmployeeLevelEnum.ADMIN)) {
			return true;
		} else {
			return false;
		}
	}

}
