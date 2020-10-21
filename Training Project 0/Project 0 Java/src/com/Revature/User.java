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
		this.firstName=checkName(firstName, "First Name");
	}
	
	//checks if the name has spaces between it and breaks it up
	private String checkName(String name,String partOfName) {

			boolean usableAnswer=false;
			
			do {
				//remove white space on ends
				name= name.trim();
				//Make sure first letter is upper case while rest is lower case
				name=name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
				String[]nameParts=name.split(" ");
				int arraySize=nameParts.length;
				if (arraySize==1) {
					return name;
				} else {
				String dashOption=dashifyName(nameParts);
				String removeSpace=removeSpacesFromName(nameParts);
					
					System.out.println("There are extra spaces in the "+partOfName+" \""+name+"\". \nWhat would you like to do?\n"
							+ "0 : Re-enter Name\n"
							+ "1 : Use "+dashOption+"\n"
							+ "2 : Use "+removeSpace+"\n");
					
				String responseString = scan.nextLine();
				
				int answerNumber=0;
//				If the catch block does not initiate, this will be true and the loop will break
				usableAnswer=true;
				try{
					answerNumber=Integer.parseInt(responseString);
					
//					Also check if number is in range
					if(answerNumber==1) {
						name=dashOption;
					} else if(answerNumber==2) {
						name=removeSpace;
					} else if(answerNumber==0) {
						usableAnswer=false;
						System.out.println("Please reenter the "+partOfName+":\n");
						name = scan.nextLine();
						
					} else {
						System.out.println(responseString+" is not a vailid choice. Please try again.");
						usableAnswer=false;
					}
					
				}catch(NumberFormatException e){
					System.out.println("That was not a number, please try again.");	
					usableAnswer=false;
				}
				
			} 
			
		} while(usableAnswer==false);
		return name;
	}
	

	
	//internal use String Modification methods
	
	private String[] splitName(String name) {
		name= name.trim();
		String[]nameParts=name.split(" ");
		return nameParts;
		
	}
	
	private String dashifyName(String name) {
		return(dashifyName(splitName(name)));
	}
	
	private String dashifyName(String[]nameParts) {
		int arraySize=nameParts.length;
		String dashOption="";
		
		for(int i=0;i<arraySize;i++) {
			if(i==0) {

				
				dashOption+=nameParts[i].substring(0,1).toUpperCase()+nameParts[i].substring(1).toLowerCase();
				
			}else {
				dashOption+="-"+nameParts[i].substring(0,1).toUpperCase()+nameParts[i].substring(1).toLowerCase();				
			}
		}
		return dashOption;
		
	}
	
	private String removeSpacesFromName(String name) {
		return(removeSpacesFromName(splitName(name)));
		
	}
	
	private String removeSpacesFromName(String[]nameParts) {
		int arraySize=nameParts.length;
		String removeSpace="";
		for(int i=0;i<arraySize;i++) {
			removeSpace+=nameParts[i].trim();
		}
		return removeSpace;
	}
	
	

}
