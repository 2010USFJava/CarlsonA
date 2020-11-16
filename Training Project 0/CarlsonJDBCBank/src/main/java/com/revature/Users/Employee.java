package com.revature.Users;



public class Employee extends User {
//local enums


	private enum EmployeeLevelEnum{
		STANDARD,
		ADMIN;
	}
	
//variables

	private EmployeeLevelEnum empLevel=EmployeeLevelEnum.STANDARD;
	
	{empLevel=EmployeeLevelEnum.STANDARD;}
	
	//constructor
	public Employee(String firstName, String lastName) {
		super(firstName, lastName, UserTypeEnum.EMPLOYEE);
		// TODO Auto-generated constructor stub
	}
	
	//helper construcot
	public static Employee createEmployee(Employee emp,LoginInfo login) {
		emp.setLoginInfo(login);
		login.addToLoginMap();
		return emp;
	}
	
	public static Employee createAdmin(Employee emp,LoginInfo login) {
		emp=createEmployee(emp,login);
		emp.empLevel=EmployeeLevelEnum.ADMIN;
		return emp;
		
	}
	
	public boolean checkIfAdmin() {
		if(empLevel.equals(EmployeeLevelEnum.ADMIN)) {
			return true;
		} else {
			return false;
		}
	}
	
}
