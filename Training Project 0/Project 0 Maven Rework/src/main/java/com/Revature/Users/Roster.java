package com.Revature.Users;

import java.util.ArrayList;

public class Roster {
	static {
		loginRoster = new ArrayList<>();
		Customer cust = new Customer("Joe","Smith");
		LoginInfo testLogin = new LoginInfo("guest","pass",cust);
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
