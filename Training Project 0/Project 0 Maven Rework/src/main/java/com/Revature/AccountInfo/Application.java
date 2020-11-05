package com.Revature.AccountInfo;

import java.util.LinkedList;
import java.util.Queue;

import com.Revature.Meta.RuntimeData;
import com.Revature.Users.Customer;
import com.Revature.Users.User;

public class Application {
	
	private User user;
	
	//Constructor
	
	
	private static Queue<Application>applicationQueue=new LinkedList<>();
	
	public Application(User user) {
		this.user=user;
	}

	public static void enterApplicationQueue(Application application) {
		applicationQueue.add(application);
	}
	
	public static boolean checkIfEmployeeIsLoggedIn() {
		if (RuntimeData.data.isLoggedIn()) {
			//check if user is an employee
			if(RuntimeData.data.getUser().checkIfEmployee()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	public static Application getNextApplication() {
		if (checkIfEmployeeIsLoggedIn()) {
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
		

	}
	
	public void rejectApplication() {
		
	}
	
	
	
	

}
