package com.Revature;

import java.util.Scanner;

public class RuntimeData {
	private User user;
	private boolean goBack;
	private boolean skipStep;
	private boolean loginProblems;
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
	
	
	public void setSkipStep(boolean skipStep) {
		this.skipStep=skipStep;
	}
	
	public boolean getSkipStep() {
		return skipStep;
	}

	public boolean isLoginProblems() {
		return loginProblems;
	}

	public void setLoginProblems(boolean loginProblems) {
		this.loginProblems = loginProblems;
	}
	
	


}
