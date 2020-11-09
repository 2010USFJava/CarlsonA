package com.Revature.Users;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.Revature.AccountInfo.Account;
import com.Revature.Meta.FileHandler;
import com.Revature.Meta.LogThis;
import com.Revature.Meta.LogThis.LevelEnum;
import com.Revature.Meta.RuntimeData;
import com.Revature.Meta.StringCheck;

public class LoginInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2260561606317849663L;
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
		FileHandler.saveAll();
		
	}
	
	//constructor plus addToMap
	public static LoginInfo createLoginInfoAndAddToMap(String username, String password,User user) {
		LoginInfo loginInfo=new LoginInfo(username,password,user);
		loginInfo.addToLoginMap();
		FileHandler.saveAll();
		return loginInfo;
		
	}
	
	
	
	
	
	//getters and setters
	public String getUsername() {
		return username;
	}
	
	
	public static void setLoginMap(Map<String,LoginInfo> loadedMap) {
		loginMap=loadedMap;
		FileHandler.saveAll();
	}
	
	public static Map<String,LoginInfo> getLoginMap() {
		return loginMap;
	}
	
	
	
	//loginMapManipulators
	public void addToLoginMap() {
		this.user.setLoginInfo(this);
		loginMap.put(username, this);		
	}
	
	public void addToLoginMapAndSave() {
		this.user.setLoginInfo(this);
		loginMap.put(username, this);
		FileHandler.writeLoginFile(loginMap);
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
		boolean infoExists = checkIfUsernameIsTaken(username);
		if(infoExists) {
			return info.logInMeat(username, password);
		} else {
			System.out.println("Login was not successful - No such user");
			return defaultUser;
		}
		
		
	}
	
	public static User employeeCheckingUserInfo(String username) {
		if(!RuntimeData.data.getUser().checkIfEmployee()) {
			System.out.println("Sorry, Only employees can use this search. Please return to the main menu and log in");
			LogThis.logIt(LevelEnum.ERROR, "Non-Employee somehow called Employee User Check");
			return null;
		} else {
			User defaultUser = null;
			LoginInfo info = loginMap.get(username);
			boolean infoExists = checkIfUsernameIsTaken(username);
			if(infoExists) {
				LogThis.logIt(LevelEnum.INFO, "Employee pulled info on "+username);	
				return info.user;
			} else {
				System.out.println("Pull was not successful - No such user");
				return defaultUser;
			}
			
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
