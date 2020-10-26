package com.Revature;

public class Customer {
	
	//variable
	private int custId;
	private User user;
	
	private static int idTracker=0;
	
	public Customer(){
		this(new User());
	}
	
	public Customer(User user) {
		incrementId();
	}
	
	public Customer(String firstName,String middleName,String lastName) {
		this(new User(firstName,middleName,lastName));
		
	}
	
	private void incrementId() {
		custId=idTracker++;
	}
	
	

}
