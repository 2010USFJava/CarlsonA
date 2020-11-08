package com.Revature.Users;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import com.Revature.Meta.FileHandler;
import com.Revature.Meta.LogThis;

public abstract class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5050831815034332607L;

	protected enum UserTypeEnum{
		CUSTOMER,
		EMPLOYEE;
	}
	
	//basic user name information
	private String firstName;
	private String lastName;
	//Some people don't have middle names. Defaulting this to ""
	private String middleName="";
	private LoginInfo loginInfo;
	private int userId;
	private UserTypeEnum userType;


	//in the event the user has multiple middle names this can be called
	private ArrayList<String> additionalMiddleNames = new ArrayList<>();
	//Checks if additional steps should be run for the extra middle names
	private boolean hasAdditionalMiddleNames=false;
	
	//Scanner
	private transient Scanner scan = new Scanner(System.in);
	
	//static
	public static int idTracker=0;
	
	
	//constructor assists
	private void generateId() {
		userId=idTracker++;
	}
	
//	constructors
	private void init(){
		LogThis.logIt(LogThis.LevelEnum.INFO, "Created new user"+toString());
	}
	
	
	{generateId();}
	

	public User(String firstName,String middleName, String lastName,boolean isTestObject) {
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		FileHandler.saveAll();
	}
	
	public User(String firstName,String middleName, String lastName) {
		 this(firstName,middleName,lastName,false);
	}

	
	public User(String firstName,String lastName) {
		this(firstName,"",lastName,false);
	}
	public User(String firstName,String lastName,boolean isTestObject) {
		this(firstName,"",lastName,isTestObject);
	}
	
	
	
	//Name handling: Getters/Setters and Helpers
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
		FileHandler.saveAll();
	}
	
	public void setLastName(String lastName){
		this.lastName=checkName(lastName,"Last Name");
		FileHandler.saveAll();
		
	}
	
	public void setMiddleName(String middleName) {
		if(!middleName.trim().equals("")) {
			this.middleName=checkName(middleName, "Middle Name",true);
		}
		FileHandler.saveAll();
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
					if(isMiddleName) {
						//in case this is overriding, make sure other middle name details are cleared
						additionalMiddleNames = new ArrayList<>();
						hasAdditionalMiddleNames=false;
						
					}
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
							longMiddleNameString+=" "+middleNameArrayList.get(i);
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
//			System.out.println("Working Part: "+nameParts[i].trim());
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
		if (nameParts.length<maxNum) {
			maxNum=nameParts.length;
		}
		for(int i=0;i<(maxNum);i++) {
			if (nameParts[i].trim().length()>0) {
				outputArrayList.add(capitilizeOnlyFirstLetter(nameParts[i]).trim());
			}
		}
		return outputArrayList;
		
	}
	
	private String capitilizeOnlyFirstLetter(String word) {
		return word.substring(0,1).toUpperCase()+word.substring(1).toLowerCase();
	}


	//getters and setters
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		FileHandler.saveAll();
	}
	
	public void setLoginInfo(String username,String password) {
		this.loginInfo=new LoginInfo(username,password,this);
		FileHandler.saveAll();
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

	protected void setUserType(UserTypeEnum userType) {
		this.userType = userType;
		FileHandler.saveAll();
	}

	public int getUserId() {
		return userId;
	}
	
	public boolean checkIfEmployee() {
		if(Employee.class.isInstance(this)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkIfCustomer() {
		if(Customer.class.isInstance(this)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String userTypeToString() {
		if(checkIfCustomer()) {
			return "customer";
		} else if (checkIfEmployee()) {
			return "employee";
		} else {
			return "unspecified user";
		}
	}
	
	@Override
	public String toString() {
		String output=userTypeToString()+"ID: "+userId;
		output+= "\n\tFirstName: "+firstName;
		if (middleName!=null) {
			output+="\tMiddleName: "+middleName;
		}
		output+="\tLastName: "+lastName;
		return output;
	}
	

	

}
