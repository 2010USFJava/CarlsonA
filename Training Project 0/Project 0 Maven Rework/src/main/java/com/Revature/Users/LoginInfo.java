package com.Revature.Users;

import java.util.HashMap;
import java.util.Map;

import com.Revature.Meta.StringCheck;

public class LoginInfo {
	/*note to self: add better security for this
	 * Maybe add a separate class for security
	 */
	private String username;
	private String password;
	private User user;

	private static Map<String,LoginInfo> loginMap = new HashMap<>();
	
	
	//constructor
	public LoginInfo(String username, String password,User user) {
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
		this.user=user;
		
	}
	
	//constructor plus addToMap
	public static LoginInfo createLoginInfoAndAddToMap(String username, String password,User user) {
		LoginInfo loginInfo=new LoginInfo(username,password,user);
		loginInfo.addToLoginMap();
		return loginInfo;
		
	}
	
	
	
	//getters and setters
	public String getUsername() {
		return username;
	}
	
	//loginMapManipulators
	public void addToLoginMap() {
		loginMap.put(username, this);		
	}
	
	public void removeFromLoginMap() {
		loginMap.remove(username);
	}
	
	
	public static User logIn(String username, String password) {
//		try retrieving LoginInfo
		//Should hide a failed log in attempt here in final run to make sure
//		fishers cannot get in
		User defaultUser = null;
		LoginInfo info = loginMap.get(username);
		System.out.println(info);
		boolean infoExists = checkIfUsernameIsTaken(username);
		if(infoExists) {
			return info.logInMeat(username, password);
		} else {
			System.out.println("Login was not successful - No such user");
			return defaultUser;
		}
		
		
	}
	
	//Try to access user data by logging in
	private User logInMeat(String username, String password) {
		User user = null;
		if ((this.username.equals(username))&&(this.password.equals(password))) {
			System.out.println("Login successful");
			return this.user;
		} else if(this.username.equals(username)) {
			System.out.println("Login was not successful- No such user");
		}else {
			System.out.println("Login was not successful- No such password");
		}

		return user;
	}
	
	
	
	//username checks
	public static boolean checkIfUsernameIsTaken(String username) {
			if (loginMap.containsKey(username)) {
				return true;
			} else {
				return false;
			}	
		
	}
	
	@Override
	public String toString() {
		return "Username: "+username+ " FirstName: "+user.getFirstName(); 
		
	}


	
	
	
	


}
