package com.revature.Users;

import java.io.Serializable;



/*Middle name handling has become complex enough despite not being a required feature, 
 * that I'm disabling it for this current code version. 
 * I'll place the relavent code at the bottom of this class in case I want revival in
 * the future.
 */



public abstract class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6686479606343552816L;

	public enum UserTypeEnum{
		CUSTOMER,
		EMPLOYEE;
	}
	
	//basic user name information
	private int userId;
	private String firstName;
	private String lastName;
	//Some people don't have middle names. Defaulting this to ""
	private LoginInfo loginInfo;
	private UserTypeEnum userType;

	
	//constructors
	
	public User(String firstName, String lastName,UserTypeEnum userType) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.userType=userType;
		
		
	}
	
	public User(int id, String firstName, String lastName, UserTypeEnum userType) {
		this.userId=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.userType=userType;
	}
	
	public User(int id, String firstName, String lastName,String username,String password, UserTypeEnum userType) {
		this.userId=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.userType=userType;
	}
	
	
	//getters and setters
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFullName() {
		return firstName+" "+lastName;
	}
	

	
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName=lastName;
		
	}
	
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo=loginInfo;
	}
	
	public int getUserId() {
		return userId;
	}

	
	
	
	public boolean checkIfEmployee() {
		if(Employee.class.isInstance(this)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkIfCustomer() {
		
		if(Customer.class.isInstance(this)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String userTypeToString() {
		if(checkIfCustomer()) {
			return "customer";
		} else if (checkIfEmployee()) {
			return "employee";
		} else {
			return "unspecified user";
		}
	}
	
	@Override
	public String toString() {
		String output=userTypeToString()+"\nID: "+userId;
		output+= "\n\tFirstName: "+firstName;
		output+="\tLastName: "+lastName;
		return output;
	}
	
	
}



//middle name variables and function purgatory

//private String middleName="";
////in the event the user has multiple middle names this can be called
//	private ArrayList<String> additionalMiddleNames = new ArrayList<>();
//	//Checks if additional steps should be run for the extra middle names
//	private boolean hasAdditionalMiddleNames=false;
//
////middle Name Fuctions
//public void setMiddleName(String middleName) {
//if(!middleName.trim().equals("")) {
//	this.middleName=checkName(middleName, "Middle Name",true);
//}
//}
//public String getMiddleName() {
//	return middleName;
//}