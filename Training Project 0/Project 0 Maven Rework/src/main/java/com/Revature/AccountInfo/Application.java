package com.Revature.AccountInfo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import com.Revature.Meta.FileHandler;
import com.Revature.Meta.RuntimeData;
import com.Revature.Users.Customer;
import com.Revature.Users.User;

public class Application implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7427064385572409687L;

	private User user;
	
	//Constructor
	
	
	private static Queue<Application>applicationQueue=new LinkedList<>();
	
	public Application(User user) {
		this.user=user;
		FileHandler.saveAll();
	}

	public static void enterApplicationQueue(Application application) {
		applicationQueue.add(application);
		FileHandler.saveAll();
	}
	

	
	
	public static Application getNextApplication() {
		if (RuntimeData.checkIfEmployeeIsLoggedIn()) {
			return applicationQueue.peek();
		}
		else {
			System.out.println("Only employees may see this information");
			System.out.println("You are either not an employee or are not logged in.");
			return null;
		} 
		
	}
	
	public void approveApplication() {
		Customer cust;
		FileHandler.saveAll();

	}
	
	public void rejectApplication() {
		FileHandler.saveAll();
		
	}
	
	
	
	

}
