package com.Revature;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
	
	//basic user name information
	private String firstName;
	private String lastName;
	private String middleName;
	
	//in the event the user has multiple middle names this can be called
	private ArrayList<String> additionalMiddleNames = new ArrayList<>();
	//Checks if additional steps should be run for the extra middle names
	private boolean hasAdditionalMiddleNames=false;
	
	//Scanner
	private Scanner scan = new Scanner(System.in);
	
	//getters and setters
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getMiddleName() {
		String output=middleName;
		
		//if
		if (hasAdditionalMiddleNames==true) {
//			Run through each item and add it with a space to the new output
			
			
		}
		return output;
		
		
	}
	
	public void setFirstName(String firstName) {
		this.firstName=checkName(firstName);
	}
	
	//checks if the name has spaces between it and breaks it up
	private String checkName(String name) {
		name= name.trim();
		String[]nameParts=name.split(" ");
		int arraySize=nameParts.length;
		if (arraySize==1) {
			return name;
		} else {
			String dashOption="";
			String removeSpace="";
			for(int i=0;i<arraySize;i++) {
				dashOption+="-"+nameParts[i].trim();
				removeSpace+=nameParts[i].trim();
			}
			
			System.out.println("There are extra spaces in this part of the name. \nWhat would you like to do?\n"
					+ "0 : Re-enter Name\n"
					+ "1 : Use "+dashOption+"\n"
					+ "2 : Use "+removeSpace+"\n");
			
			String responseString = scan.nextLine();
			int answerNumber;
			boolean usableAnswer=false;
			
			try{
				answerNumber=Integer.parseInt(responseString);
				usableAnswe
			}catch(NumberFormatException e){
				do {
					System.out.println("That was not a number, please try again.");
					
					
				} while(usableAnswer==false);
				
				
			}
			
		}
		
		return name;
		
		
	}
	
	

}
