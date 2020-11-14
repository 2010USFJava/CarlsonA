package com.revature.Users.interfaces;


import com.revature.Users.LoginInfo;
import com.revature.Users.User;

public interface LoginInfoInterface {
	
	public String getUsername();

	public LoginInfo createLoginInfo(String username, String password,User user);
	
	public void addToRecords();
	
	public void removeFromRecords();
	
	public User login(String username, String password);
	
	

}
