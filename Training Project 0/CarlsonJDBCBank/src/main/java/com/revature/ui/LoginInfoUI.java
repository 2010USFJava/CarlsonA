package com.revature.ui;

import com.revature.Meta.StringCheck;
import com.revature.Users.LoginInfo;
import com.revature.Users.User;

public class LoginInfoUI {
	
	//constructor
	public static LoginInfo createNewLoginInfo(String username, String password,User user) {
		username= createNewUsername(username);
		return new LoginInfo(username,password,user);
		
		
	}
	

	private static String createNewUsername(String username) {
		boolean keepGoing=false;
		do {
			username=StringCheckUI.scannerStringCheck("username");
			
			if (!LoginInfo.checkIfUsernameIsTaken(username)) {
				keepGoing=true;
			}
			
			if(!keepGoing) {
				System.out.println("Unfortunatly, that username is unavaliable. Please try again.");	
			}
			
		}while(!keepGoing);
		return username;
	}
}
