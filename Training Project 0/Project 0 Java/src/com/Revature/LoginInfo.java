package com.Revature;

import java.util.HashMap;

public class LoginInfo {
	/*note to self: add better security for this
	 * Maybe add a separate class for security
	 */
	private String username;
	private String password;

//	Stretch Goal do double checks in the future
	private static HashMap<String,String> takenUsernames=new HashMap<>();
	
	public LoginInfo(String username, String password) {
		this.username=checkIfUsernameIsTaken(username);
		this.password=password;
		
	}
	
	private String checkIfUsernameIsTaken(String username) {
		String checkedName=username;
		return checkedName;
	}

	private boolean checkIfUsernameExists() {
		if (username==null) {
			return false;
		}
		return true;
	}
	
	private boolean checkIfPasswordExists() {
		if (password==null) {
			return false;
		}
		return true;
	}
	
	


}
