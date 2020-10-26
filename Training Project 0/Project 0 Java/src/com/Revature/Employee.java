package com.Revature;

public class Employee  {
	//variables
	private int empId;
	private User user;
	
	private static int idTracker=0;
	
	public Employee(){
		this(new User());
	}
	
	public Employee(User user) {
		incrementId();
	}
	
	public Employee(String firstName,String middleName,String lastName) {
		this(new User(firstName,middleName,lastName));
		
	}
	
	private void incrementId() {
		empId=idTracker++;
	}
	
	

}
