package com.revature.Meta;

import com.revature.Users.User;

public class RuntimeData {
	
	private boolean loggedIn;
	private User user;
	private boolean goBack;
//	private boolean loginProblems;
	public static RuntimeData data=new RuntimeData();
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setGoBack(boolean goBack) {
		if(goBack) {
			System.out.println("Going back...");
		}
		this.goBack=goBack;
	}
	
	public boolean getGoBack() {
		return goBack;
	}
	

//	public boolean isLoginProblems() {
//		return loginProblems;
//	}
//
//	public void setLoginProblems(boolean loginProblems) {
//		this.loginProblems = loginProblems;
//	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public static boolean checkIfEmployeeIsLoggedIn() {
		if (data.isLoggedIn()) {
			//check if user is an employee
			if(data.getUser().checkIfEmployee()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	

}
