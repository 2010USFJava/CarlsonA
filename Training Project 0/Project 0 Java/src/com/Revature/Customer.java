package com.Revature;

public class Customer extends User {
	
	//variable
	private int custId;
	private static int idTracker=0;
	
	
	//constructors
	public Customer(){
		super();
	}
	
	
	public Customer(String firstName,String middleName,String lastName) {
		super(firstName,middleName,lastName);
		incrementId();
		
	}
	
	public Customer(String firstName, String lastName) {
		super(firstName,lastName);
		incrementId();
	}
	
	//constructor assists
	private void incrementId() {
		custId=idTracker++;
	}
	
	//getter and setters
	public int getId() {
		return custId;
		
	}
	
	//account interaction
	public Account createAccount() {
		Account acct= new Account(this);
		return acct;
		
	}
	
	public Account createAccount(Customer cust) {
		Account acct;
		if (cust.equals(this)) {
			acct=createAccount();
		} else {
			acct= new JointAccount(this,cust);
			
		}
		return acct;
		
	}

}
