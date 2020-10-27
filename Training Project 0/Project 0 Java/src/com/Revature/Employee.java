package com.Revature;

public class Employee extends User {
	//variables
	private int empId;
	private static int idTracker=0;
	
	public Employee(){
		super();
		incrementId();
	}
	

	public Employee(String firstName,String middleName,String lastName) {
		super(firstName,middleName,lastName);
		incrementId();
	}


	
	private void incrementId() {
		empId=idTracker++;
	}
	
	//getters and setters
	public int getId() {
		return empId;
		
	}
	
	//change account status
	public void changeAccountStatus(Account account, AccountStatusEnum status) {
		account.changeStatus(status);
		
	}
	
	public String getAccountInfo(Account account) {
		String output=account.getCustomerPersonalData();
		
		return output;
		
	}

}
