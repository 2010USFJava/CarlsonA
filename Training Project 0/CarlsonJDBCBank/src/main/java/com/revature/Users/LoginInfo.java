package com.revature.Users;

import java.util.HashMap;
import java.util.Map;

import com.revature.Meta.LogThis;
import com.revature.Meta.LogThis.LevelEnum;
import com.revature.Meta.RuntimeData;




public class LoginInfo  {
	
	private String username;
	private String password;
	private User user;

	
	//static variables
	private static Map<String,LoginInfo> loginMap;
	
	//constructor
	public LoginInfo(String username, String password,User user) {

		this.username=username;
		this.password=password;
		this.user=user;
		
	}
	


	public String getUsername() {
		return this.username;
	}

	public static LoginInfo createLoginInfoAndAddToMap(String username, String password, User user) {
		LoginInfo loginInfo=new LoginInfo(username,password,user);
		loginInfo.addToLoginMap();
		return loginInfo;
	}

	//loginMapManipulators
	public void addToLoginMap() {
		this.user.setLoginInfo(this);
		Map<String,LoginInfo> lm=getLoginMap();
		lm.put(username, this);		
	}

	public void removeFromLoginMap() {
		loginMap.remove(username);
	}
	

//login action
	public static User logIn(String username, String password) {
//		try retrieving LoginInfo
		//Should hide a failed log in attempt here in final run to make sure
//		fishers cannot get in
		User defaultUser = null;
		LoginInfo info = getLoginMap().get(username);
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
	


	
	public static boolean checkIfUsernameIsTaken(String username) {
		Map<String,LoginInfo> lm=getLoginMap();
		if (lm.containsKey(username)) {
			return true;
		}
		return false;
		
	}
	
	private static Map<String,LoginInfo> getLoginMap() {
		if(loginMap==null) {
			loginMap=new HashMap<>();
		}
		return loginMap;
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



	


}
