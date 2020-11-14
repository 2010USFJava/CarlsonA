package com.revature.Users;

import java.util.ArrayList;
import java.util.Scanner;


/*Middle name handling has become complex enough despite not being a required feature, 
 * that I'm disabling it for this current code version. 
 * I'll place the relavent code at the bottom of this class in case I want revival in
 * the future.
 */



public abstract class User {

	protected enum UserTypeEnum{
		CUSTOMER,
		EMPLOYEE;
	}
	
	//basic user name information
	private int userId;
	private String firstName;
	private String lastName;
	//Some people don't have middle names. Defaulting this to ""
//	private LoginInfo loginInfo;
	private UserTypeEnum userType;

		


	
	
	//constructors
	
	public User(String firstName, String lastName,UserTypeEnum userType) {
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
	

	
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName=lastName;
		
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