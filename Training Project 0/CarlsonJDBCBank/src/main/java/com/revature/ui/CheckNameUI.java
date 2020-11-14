package com.revature.ui;

import java.util.ArrayList;

import com.revature.Meta.StringCheck;

public class CheckNameUI {
	//checks if the name has spaces between it and breaks it up
			private String checkName(String name,String partOfName,boolean isMiddleName) {
				boolean usableAnswer=false;			
				
				
					do {
						String[]nameParts=StringCheck.splitName(name);
						int arraySize=nameParts.length;
						if (arraySize==1) {
							return name;
							
							
						} else {
							
							//declare varibles if I need to break down a name
						String dashOption=StringCheck.dashifyName(nameParts);
						String removeSpace=StringCheck.removeSpacesFromName(nameParts);
						ArrayList<String>middleNameArrayList=new ArrayList<>();
						String longMiddleNameString="";
						
						
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
//						If the catch block does not initiate, this will be true and the loop will break
						usableAnswer=true;
						try{
							answerNumber=Integer.parseInt(responseString);	
//							Also check if number is in range
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
			
			
			
}
