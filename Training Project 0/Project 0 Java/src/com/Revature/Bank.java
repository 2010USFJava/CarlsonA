package com.Revature;

import java.util.Scanner;

public class Bank {
	private String name="Fancy Bank of Holding";
	private final int estYear=2020;
	private Scanner scan=new Scanner(System.in);
	private RuntimeData runData= new RuntimeData();
	
	public void startPage() {
		System.out.println("Welcome to "+name);
		System.out.println("Est. "+estYear);
		selectUserType();
		
	}
	
	private void selectUserType() {
		String[] Options = new String[3];
		Options[0] = "I am a new customer";
		Options[1] = "I am a returning customer";
		Options[2] = "I am an employee";
		
		System.out.println("What type of user are you?");
		int num=0;
		for(String i :Options) {
			System.out.println(num+++": "+i);
		}
		
		boolean keepGoing=true;
		int selection=30;
		
		do {
		
			try {
				keepGoing=true;
				String stringAnswer = scan.nextLine().trim();
				selection=Integer.parseInt(stringAnswer);
				System.out.println("You chose \""+Options[selection]+"\"");
			}catch(ArrayIndexOutOfBoundsException e){
				keepGoing=false;
				System.out.println("Unfortunently, that was not a listed choice.\nPlease try again.");
			}catch(NumberFormatException e){
				keepGoing=false;
				System.out.println("Please enter a number and try again.");
			} 
			catch (Exception e) {
				keepGoing=false;
				e.printStackTrace();
				System.err.println("Why do you do this to me?");
			}
			
		}while(!keepGoing);
		
		

		
		switch (selection) {
		case 0://new customer
			break;
		case 1://returning customer
			break;
		case 2://Employee
			break;
		default: //Unprogrammed
			System.out.println("Unfortunantly that has not been added yet. Please reselect.");
			selectUserType();
			break;
		}
		
	}
	
	private void login() {
		System.out.println("Please Enter Your Login Credentials\nPress 0 if you do not have Login Credentials, or have forgotten them");
		
	
	}
	
	
	
	
	
	
	
	

}
