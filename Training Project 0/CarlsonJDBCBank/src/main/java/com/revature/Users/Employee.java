package com.revature.Users;



public class Employee extends User {
//local enums


	public enum EmployeeLevelEnum{
		STANDARD,
		ADMIN;
	}
	
//variables

	private EmployeeLevelEnum empLevel=EmployeeLevelEnum.STANDARD;
	
	{empLevel=EmployeeLevelEnum.STANDARD;}
	
	//constructor
	private Employee(String firstName, String lastName) {
		super(firstName, lastName, UserTypeEnum.EMPLOYEE);
		// TODO Auto-generated constructor stub
	}
	private Employee(int id, String firstName, String lastName) {
		super(id, firstName,lastName, UserTypeEnum.EMPLOYEE);
	}
	
	//helper construcot
	public static Employee createEmployee(String firstName,String lastName,String username, String password) {
		Employee emp = new Employee(firstName,lastName);
		LoginInfo.createLoginInfoAndAddToMap(username, password, emp);
		return emp;
	}
	
	public static Employee createEmployee(int id, String firstName,String lastName,String username, String password) {
		Employee emp = new Employee(id,firstName,lastName);
		LoginInfo.createLoginInfoAndAddToMap(username, password, emp);
		return emp;
	}
	
	public static Employee createAdmin(String firstName,String lastName,String username, String password) {
		Employee emp=createEmployee(firstName,lastName,username,password);
		emp.empLevel=EmployeeLevelEnum.ADMIN;
		return emp;
		
	}
	
	public static Employee createAdmin(int id, String firstName,String lastName,String username, String password) {
		Employee emp=createEmployee(id,firstName,lastName,username,password);
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
