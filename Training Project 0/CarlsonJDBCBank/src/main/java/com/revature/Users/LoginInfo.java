package com.revature.Users;

import java.util.HashMap;
import java.util.Map;

import com.revature.Users.interfaces.LoginInfoInterface;

public class LoginInfo implements LoginInfoInterface {
	
	private String username;
	private String password;
	private User user;

	
	//static variables
	private static Map<String,LoginInfo> loginMap;
	
	//constructor
	public LoginInfo(String username, String password,User user) {
//		In process of moving this into UI 
		
		//		boolean keepGoing=false;
//		do {
//			username=StringCheck.scannerStringCheck("username");
//			
//			if (!checkIfUsernameIsTaken(username)) {
//				keepGoing=true;
//			}
//			
//			if(!keepGoing) {
//				System.out.println("Unfortunatly, that username is unavaliable. Please try again.");	
//			}
//			
//		}while(!keepGoing);
		
		this.username=username;
		this.password=password;
		this.user=user;
		
	}
	
	
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginInfo createLoginInfo(String username, String password, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToRecords() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromRecords() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
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


}
