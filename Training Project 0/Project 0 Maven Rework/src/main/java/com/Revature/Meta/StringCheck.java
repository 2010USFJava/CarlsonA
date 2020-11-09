package com.Revature.Meta;

import java.util.Scanner;

public class StringCheck {
	
	private static int []forbiddenNumbers= {0,1,2,3,4,5,6,7,8,9};
//	I may use these for test purposes
	private static String[]forbiddenUsernames= {"guest","admin","cust","custA","custB","custC","custD","custE","custF","custG","emp","empA","empB","empC","empD","empE","empF","empG","user","userA","userB","userC","userD","userE","userF","userG"};
	
	public static Scanner scan= new Scanner(System.in);
	
	public static String scannerUsernameStringOrGoBack() {
		String output= scannerStringOrGoBack("username");
		output=scannerUsernameCheck(output);
		return output;
		
	}

	public static String scannerStringOrGoBack(String wantedInput) {
		return scannerStringOrGoBack(wantedInput,false);
	}
	
	public static String scannerStringOrGoBack(String wantedInput,boolean optional) {
		//If I'm running this test, want to make sure slate is clean
		
		RuntimeData runData=RuntimeData.data;
		runData.setGoBack(false);
		runData.setSkipStep(false);
		System.out.println("Press 0 if you wish exit");
		System.out.println("Please enter your "+wantedInput);
		if(optional) {
			System.out.println("You may also press 1 to skip this step");
		}
		String stringAnswer = scan.nextLine().trim();
		
		//Opted to try parsing instead of "0" since user could enter 0000000 or the like
		boolean userEnteredNumber=true;
		int intAnswer=9000;
		try {
			intAnswer=Integer.parseInt(stringAnswer);
			
		} catch(NumberFormatException e) {
			userEnteredNumber=false;
		}
		
		if (userEnteredNumber) {
//			Making this a switch in case of future updates that move beyound the 0 option
			switch (intAnswer) {
			case 0:
				//can test for this later
				runData.setGoBack(true);
				break;
			case 1:
				runData.setSkipStep(true);
				break;
			default:
				boolean passFobiddenNumbers=passForbbidenNumberCheck(intAnswer);
//				If fails to pass forbidden number check run again
				if(!passFobiddenNumbers) {
					stringAnswer=scannerStringOrGoBack(wantedInput,optional);
				}
				break;
			}
		}
		if(stringAnswer.trim()=="") {
			if(optional) {
				runData.setSkipStep(true);
			} else {
				stringAnswer=scannerStringOrGoBack(wantedInput,optional);
			}	
		}
		
		return stringAnswer;
		
	} 
	

	
	public static String scannerStringCheck(String wantedInput) {
		System.out.println("Please enter your "+wantedInput);
		String stringAnswer = scan.nextLine().trim();
		
		
		boolean keepGoing=false;
		//Opted to try parsing instead of "0" since user could enter 0000000 or the like
		do {
			boolean userEnteredNumber=true;
			int intAnswer=9000;
			try {
				intAnswer=Integer.parseInt(stringAnswer);
				
			} catch(NumberFormatException e) {
				userEnteredNumber=false;
			}
			
			keepGoing=true;
			if (userEnteredNumber) {
				keepGoing=passForbbidenNumberCheck(intAnswer);
			}
		} while(keepGoing==false);
		
		return stringAnswer;
		
	} 
	
	public static String scannerUsernameCheck(String wantedInput) {
		
		boolean pass=passFobbidenUsernameCheck(wantedInput);
		while(pass==false) {
			wantedInput=scannerStringCheck("username");
			
		}
		return wantedInput;
	}
	
	public static long moneyMiddleMan() {
		boolean keepGoing=true;
		//initilizeing this so that I don't throw an error, but making it too long to be selected by accident
		long selection=0;
		
		do {
		
			try {
				keepGoing=true;
				String stringAnswer = scan.nextLine().trim();
				
				selection=Long.parseLong(stringAnswer);
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
		
		return selection;
		
	}
	
	public static int numberScanner(String[] choice) {
		return numberScanner(choice,null,null,false);
	}

	
	public static int numberScanner(String[] options,String submenuName, String[] subMenuChoices,boolean runSubmunuCode) {
		int num=0;
		for(String i :options) {
			System.out.println(num+++": "+i);
		}
		
		//run this bit of code If I want to have a submenu
		if(runSubmunuCode) {
			System.out.println(submenuName);	
			for(String i :subMenuChoices) {
				System.out.println("\t"+num+++": "+i);
			}
			
			//merge menu and sub menu for selection
			int mainL=options.length;
			int subL=subMenuChoices.length;
			String[]newMenu=new String[mainL+subL];
			System.arraycopy(options,0,newMenu,0,mainL);
			System.arraycopy(subMenuChoices,0,newMenu,mainL,subL);
			
			//replace options for remainder of code
			options=newMenu;
		
		}
		
		boolean keepGoing=true;
		//initilizeing this so that I don't throw an error, but making it too long to be selected by accident
		int selection=options.length+1;
		
		do {
		
			try {
				keepGoing=true;
				String stringAnswer = scan.nextLine().trim();
				
				selection=Integer.parseInt(stringAnswer);
				System.out.println("You chose \""+options[selection]+"\"");
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
		
		return selection;
		
	}

	
	public static boolean passFobbidenUsernameCheck(String attemptedUser) {
		for(int i=0;i<forbiddenUsernames.length;i++) {
			if(attemptedUser==forbiddenUsernames[i]) {
				System.out.println("You cannot use username\""+i+"\". Please try again.");
				return false;
			}
		}
		return true;
		
	}
	
	public static boolean passForbbidenNumberCheck(int intAnswer) {
		for(int i=0;i<forbiddenNumbers.length;i++) {
			if(intAnswer==forbiddenNumbers[i]) {
				System.out.println("You cannot use the numbers 0 through 9 as an input here. Please try again.");
				return false;
			}
		}
		return true;
	}


	
	
}
