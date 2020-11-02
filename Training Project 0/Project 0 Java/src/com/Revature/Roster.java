package com.Revature;

import java.util.ArrayList;

public class Roster {
	static {
		loginRoster = new ArrayList<>();
		LoginInfo testLogin = new LoginInfo("guest","pass");
		addToLoginRoster(testLogin);
	}
	
	private static ArrayList<LoginInfo> loginRoster;
	
	
	
	public static void addToLoginRoster(LoginInfo info) {
		loginRoster.add(info);
	}
	
	public static User login(String username, String password) {
		User user = null;
		boolean userExit=false;
		
		user=LoginInfo.logIn(username, password);

		return user;
	}
	
	
	
	
}
