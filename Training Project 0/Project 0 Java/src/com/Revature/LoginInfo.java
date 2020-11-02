package com.Revature;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginInfo {
	/*note to self: add better security for this
	 * Maybe add a separate class for security
	 */
	private String username;
	private String password;
	private User user;

//	Stretch Goal do double checks in the future
	private static Map<String,LoginInfo> loginMap = new HashMap<>();
	
	
	//constructor
	public LoginInfo(String username, String password) {
		boolean keepGoing=false;
		do {
			if (!checkIfUsernameIsTaken(username)) {
				keepGoing=true;
			}
			
			if(!keepGoing) {
				System.out.println("Unfortunatly, that username is unavaliable. Please try again.");
				username=StringCheck.scannerStringCheck("username");
			}
			
		}while(!keepGoing);
		
		this.username=username;
		this.password=password;
		loginMap.put(username, this);
		
	}
	
	
	//getters and setters
	public String getUsername() {
		return username;
	}
	
	public static User logIn(String username, String password) {
//		try retrieving LoginInfo
		//Should hide a failed log in attempt here in final run to make sure
//		fishers cannot get in
		User user = null;
		LoginInfo info = loginMap.get(username);
		boolean infoExists = checkIfUsernameIsTaken(username);
		if(infoExists) {
			return info.logInMeat(username, password);
		} else {
			System.out.println("Login was not successful - No such user");
			return user;
		}
		
		
	}
	
	//Try to access user data by logging in
	private User logInMeat(String username, String password) {
		User user = null;
		if ((this.username==username)&&(this.password==password)) {
			System.out.println("Login successful");
			return this.user;
		} 

		System.out.println("Login was not successful- No such password");
		return user;
	}
	
	
	
	//username checks
	private static boolean checkIfUsernameIsTaken(String username) {
			if (loginMap.containsKey(username)) {
				return true;
			} else {
				return false;
			}	
		
	}
	


	
	
	
	


}
