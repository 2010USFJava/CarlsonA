package com.Revature;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
	
	//basic user name information
	private String firstName;
	private String lastName;
	//Some people don't have middle names. Defaulting this to ""
	private String middleName="";
	
	//in the event the user has multiple middle names this can be called
	private ArrayList<String> additionalMiddleNames = new ArrayList<>();
	//Checks if additional steps should be run for the extra middle names
	private boolean hasAdditionalMiddleNames=false;
	
	//Scanner
	private Scanner scan = new Scanner(System.in);
	
//	constructors
	public User() {}
	public User(String firstName,String middleName, String lastName) {
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
	}
	
	public User(String firstName,String lastName) {
		setFirstName(firstName);
		setLastName(lastName);
		
	}
	
	//getters and setters
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	//most of the time I'll need just the first middle name
	public String getMiddleName() {
		return getMiddleName(false,1);
	}
	
	public String getMiddleName(int nameCap) {
		return getMiddleName(true, nameCap);
	}
	
	public String getMiddleName(boolean useMultipleMiddleNames) {
		return getMiddleName(useMultipleMiddleNames, 20);
	}
	
	public String getMiddleName(boolean useMultipleMiddleNames,int nameCap) {
		String output=middleName;
		
//		Run through each item and add it with a space to the new output
		if (useMultipleMiddleNames==true) {
			if (hasAdditionalMiddleNames==true) {
				output="";
				int returnLimit=nameCap;
				if (additionalMiddleNames.size()<returnLimit) {
					returnLimit=additionalMiddleNames.size();
				}
				for (int i =0;i<returnLimit;i++) {
					if(i==0) {
						output+=additionalMiddleNames.get(i);
					}else {
						output+=" "+additionalMiddleNames.get(i);
					}
				}	
			}
		}
		
		return output;
		
		
	}
	
	public void setFirstName(String firstName) {
		this.firstName=checkName(firstName, "First Name");
	}
	
	public void setLastName(String lastName){
		this.lastName=checkName(lastName,"Last Name");
		
	}
	
	public void setMiddleName(String middleName) {
		this.middleName=checkName(middleName, "Middle Name",true);
		
	}
	
	private String checkName(String name,String partOfName) {
		return checkName(name,partOfName,false);
	}
	
	//checks if the name has spaces between it and breaks it up
	private String checkName(String name,String partOfName,boolean isMiddleName) {
		
		
		
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
					
					//declare varibles if I need to break down a name
				String dashOption=dashifyName(nameParts);
				String removeSpace=removeSpacesFromName(nameParts);
				ArrayList<String>middleNameArrayList=new ArrayList<>();
				String longMiddleNameString="";
				String firstMiddleName="";
				
				
				if(isMiddleName) {
					middleNameArrayList=makeNameArrayList(nameParts);
					for (int i=0;i<middleNameArrayList.size();i++) {
						if(i==0) {
							String workingString=middleNameArrayList.get(i);
							firstMiddleName=workingString;
							longMiddleNameString=workingString;
						} else {
							longMiddleNameString+=middleNameArrayList.get(i);
						}
						
					}
					
				}
					
					String output="There are extra spaces in the "+partOfName+" \""+name+"\". \nWhat would you like to do?\n"
							+ "0 : Re-enter Name\n"
							+ "1 : Use "+dashOption+"\n"
							+ "2 : Use "+removeSpace+"\n";
					
					if(isMiddleName==true) {
						output+="3 : Please use \""+longMiddleNameString+"\" as my middle name\n";
					}
					System.out.println(output);
					
				String responseString = scan.nextLine();
				
				int answerNumber=0;
//				If the catch block does not initiate, this will be true and the loop will break
				usableAnswer=true;
				try{
					answerNumber=Integer.parseInt(responseString);	
//					Also check if number is in range
					if(answerNumber==1) {
						name=dashOption;
						System.out.println(partOfName+" has been set to "+dashOption);
					} else if(answerNumber==2) {
						name=removeSpace;
						System.out.println(partOfName+" has been set to "+removeSpace);
					} else if(answerNumber==0) {
						usableAnswer=false;
						System.out.println("Please reenter the "+partOfName+":\n");
						name = scan.nextLine();
						
					} else {
						if (isMiddleName==true && answerNumber==3) {
							System.out.println(partOfName+" has been set to "+longMiddleNameString);
							hasAdditionalMiddleNames=true;
							additionalMiddleNames=middleNameArrayList;
							name=firstMiddleName;
						}else {
							System.out.println(responseString+" is not a vailid choice. Please try again.");
							usableAnswer=false;							
						}

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
			System.out.println("Working Part: "+nameParts[i].trim());
			if(nameParts[i].trim().length()>0) {
				if (nameParts[i].length()>1){
					nameParts[i]=capitilizeOnlyFirstLetter(nameParts[i]);
				}else {
					nameParts[i].toUpperCase();
				}
				
				if(i==0) {					
					dashOption+=nameParts[i];
					
				}else {
					dashOption+="-"+nameParts[i];				
				}
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
			if(i==0) {
				removeSpace+=capitilizeOnlyFirstLetter(nameParts[i]);
			}else {
				removeSpace+=nameParts[i].trim().toLowerCase();
			}
		}
		return removeSpace;
	}
	
	private ArrayList<String> makeNameArrayList(String[]nameParts) {
		int arraySize=nameParts.length;
		ArrayList <String>outputArrayList= new ArrayList();
		int maxNum=20;
		for(int i=0;i<(maxNum-1);i++) {
			if (nameParts[i].trim().length()>0) {
				outputArrayList.add(capitilizeOnlyFirstLetter(nameParts[i]).trim());
			}
		}
		return outputArrayList;
		
	}
	
	private String capitilizeOnlyFirstLetter(String word) {
		return word.substring(0,1).toUpperCase()+word.substring(1).toLowerCase();
	}
	
	

}
