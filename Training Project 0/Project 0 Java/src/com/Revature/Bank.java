package com.Revature;

import java.util.Scanner;

public class Bank {
	
	private String name="Fancy Bank of Holding";
	private final int estYear=2020;
	private static Scanner scan=new Scanner(System.in);
	private RuntimeData runData= new RuntimeData();
	
	
	
	public void startPage() {
		System.out.println("Welcome to "+name);
		System.out.println("Est. "+estYear);
		selectUserType();
		
	}
	
	private void selectUserType() {
		String[] options = new String[2];
		options[0] = "I am a new customer";
		options[1] = "I am a returning user";
		

		System.out.println("What type of user are you?");

		int selection=StringCheck.numberScanner(options);
		User user;
		switch (selection) {
		case 0://new customer
			user=applyForANewAccount();
			break;
		case 1://returning user
			user = login();
			if (user==null) {
				startPage();
			}
			break;
		default: //Unprogrammed
			System.out.println("Unfortunantly that has not been added yet. Please reselect.");
			selectUserType();
			break;
		}
		
	}
	
	public static String scanner0ForMain1ForForgotPassword(String searchItem) {
		System.out.println("Press 0 to cancel and go back to the main menu");
		System.out.println("Press 1 if you do not have Login Credentials, or have forgotten them");
		System.out.println("Please enter your "+searchItem);
		
		RuntimeData runData=RuntimeData.data;
		runData.setGoBack(false);
		runData.setLoginProblems(false);
		String stringAnswer = scan.nextLine().trim();
		
		//Opted to try parsing instead of "0" since user could enter 0000000 or the like
				boolean userEnteredNumber=true;
				int intAnswer=9000;
				try {
					intAnswer=Integer.parseInt(stringAnswer);
					
				} catch(NumberFormatException e) {
					userEnteredNumber=false;
				
					if (userEnteredNumber) {
//						Making this a switch in case of future updates that move beyound the 0 option
						switch (intAnswer) {
						case 0:
							runData.setGoBack(true);
							break;
						case 1:
							runData.setLoginProblems(true);
							break;
						default:
							boolean passFobiddenNumbers=StringCheck.passForbbidenNumberCheck(intAnswer);
//							If fails to pass forbidden number check run again
							if(!passFobiddenNumbers) {
								stringAnswer=scanner0ForMain1ForForgotPassword(searchItem);
							}
							break;
						}
					}
				}

				return stringAnswer;
		
		
		
	}

	
	//returning user
	private User login() {
		boolean loginProblems=false;
		
		String userName=scanner0ForMain1ForForgotPassword("username");
		if(runData.getGoBack()) {
			loginProblems();
			
		} else {

			String password=scanner0ForMain1ForForgotPassword("password");
			if(runData.getGoBack()) {
				loginProblems();
			} else {

				return Roster.login(userName,password);
				
			}
			
		}
		
		return null;
	}
	
	//user has lost or is missing log in credentials
	private void loginProblems() {
		String [] problemsOptions=new String[3];
		problemsOptions[0]="I selected this by accident. Send me back";
		problemsOptions[1]="I never made log-in information.";
		problemsOptions[2]="I forgot my username or password.";
		
		System.out.println("You've hit 0. What is your problem?");
		int selection=StringCheck.numberScanner(problemsOptions);
		
		switch (selection) {
		case 0:
			System.out.println("WIP");
			break;
		case 1:

			System.out.println("WIP");
			break;
		case 2:

			System.out.println("WIP");
			break;

		default:
			System.out.println("This selection has not been implemented at this time. Please try again");
			loginProblems();
			break;
		}
		
		
		
		
	}
	
	//new customer
	private Customer applyForANewAccount() {
		
		String fName=StringCheck.scannerStringOrGoBack("first name");
		String lName=StringCheck.scannerStringOrGoBack("last name");
		String mName=StringCheck.scannerStringOrGoBack("middle name",true);
		System.out.println("Just a moment while we create your application...");
		Customer newCustomer;
		if(runData.getSkipStep()) {
			newCustomer=new Customer(fName,lName);
		} else {
			newCustomer=new Customer(fName,mName,lName);
		}
		System.out.println("Account Application Completed.\nNext please create a username and password");
		String username=StringCheck.scannerStringOrGoBack("username");
//		Check username and to see if we will continue to password.
		String password=StringCheck.scannerStringOrGoBack("password");
		System.out.println("Creating log in information....");
		newCustomer.setLoginInfo(username, password);
		System.out.println("Finished creating log in information.");
		
		return newCustomer;
		
		
		
		
	}
	
	
	
	
	
	
	

}
