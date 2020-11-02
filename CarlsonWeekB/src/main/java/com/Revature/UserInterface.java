package com.Revature;

import java.util.Scanner;

public class UserInterface {
	private static String applicationName="Salmon Tracker";
	private static Scanner scan = new Scanner(System.in);
	
	private static void addSalmon() {
		LogThis.LogIt("info","User started to add a salmon.");
		System.out.println("Please enter the common name for your salmon:");
		String cName=scan.nextLine();
		
		System.out.println("Please enter the sceintific name for your salmon:");
		String sName=scan.nextLine();
		
		System.out.println("Please enter the maximum length (Cm) for your salmon:");
		String maxLengthCatcher=scan.nextLine();
		int maxLength=Integer.parseInt(maxLengthCatcher);
		
		System.out.println("Please enter the maximum weight (Kg) for your salmon:");
		String maxWeightCatcher=scan.nextLine();
		double maxWeight=Double.parseDouble(maxWeightCatcher);

		
		
		new Salmon(cName,sName,maxLength,maxWeight);
		
	}
	
	private static void viewTheAquarium() {
		LogThis.LogIt("info","User looked at all the pretty salmon.");
		System.out.println("Check out all the salmon in my aquarium");
		for(Salmon s:Aquarium.salmonList) {
			System.out.println(s);
		}
	}
	
	public static void startMenu() {
		String[] choices= new String[5];
		choices[0]="Welcome to "+applicationName;
		choices[1]="What would you like to do?";
		choices[2]="\t[a]dd a salmon";
		choices[3]="\t[v]iew the aquarium";
		choices[4]="\t[e]xit "+applicationName;
		for(String ch:choices) {
			System.out.println(ch);
			
		}
		
		String input = scan.nextLine().trim().toLowerCase();
		
		switch (input) {
		case "a":
			addSalmon();
			startMenu();
			break;
		case "v":
			viewTheAquarium();
			startMenu();
			break;
		case "e":
			System.out.println("Have a nice day. Good bye.");
			break;
		default:
			System.out.println("I'm afraid I can't do that, Hall. Please try again.");
			startMenu();
			break;
		}
		
	}
	
	

}
