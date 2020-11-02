package com.Revature;

import java.util.Scanner;

public class StringCheck {
	
	private int []forbiddenNumbers= {0,1,2,3,4,5,6,7,8,9};
	public static Scanner scan= new Scanner(System.in);

	public static String scannerStringOrGoBack(String wantedInput) {
		return scannerStringOrGoBack(wantedInput,false);
	}
	
	public static String scannerStringOrGoBack(String wantedInput,boolean optional) {
		//If I'm running this test, want to make sure slate is clean
		
		RuntimeData runData=RuntimeData.data;
		runData.setGoBack(false);
		runData.setSkipStep(false);
		System.out.println("Please enter your "+wantedInput+"\nPress 0 to go back");
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
	
	public static int numberScanner(String[] options) {
		int num=0;
		for(String i :options) {
			System.out.println(num+++": "+i);
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

	
	
	
	public static boolean passForbbidenNumberCheck(int intAnswer) {
		int []forbiddenNumbers= {0,1,2,3,4,5,6,7,8,9};
		for(int i: forbiddenNumbers) {
			if(intAnswer==i) {
				System.out.println("You cannot use the numbers 0 through 9 as an input here. Please try again.");
				return false;
			}
		}
		return true;
	}
	
	
}
