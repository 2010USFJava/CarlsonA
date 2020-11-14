package com.revature.Meta;

import com.revature.Users.LoginInfo;

public class StringCheck {
	
	public static String capitilizeOnlyFirstLetter(String word) {
		String updatedWord;
		if (word.length()>1){
			updatedWord=word.substring(0,1).toUpperCase()+word.substring(1).toLowerCase();	
		}else {
			updatedWord=word.toUpperCase();
		}
		return updatedWord;
	}
	
	public static String[] splitName(String name) {
		name= name.trim();
		String [] nameArray= name.split(" ");
		for (String each:nameArray) {
			each=capitilizeOnlyFirstLetter(each);
		}
		
		return nameArray;
	
	}


	
//returning name in dashify form
	public static String dashifyName(String name) {
		return(dashifyName(splitName(name)));
	}
	
	public static String dashifyName(String[]nameParts) {
		int arraySize=nameParts.length;
		StringBuilder dashOption=new StringBuilder();
		for(int i=0;i<arraySize;i++) {
			if(nameParts[i].trim().length()>0) {
				nameParts[i]=capitilizeOnlyFirstLetter(nameParts[i]);
				if(i==0) {					
					dashOption.append(nameParts[i]);
					
				}else {
					dashOption.append("-"+nameParts[i]);		
				}
			}
		}
		return dashOption.toString();
		
	}
	
	//remove spaces from names
	
	public static String removeSpacesFromName(String name) {
		return(removeSpacesFromName(splitName(name)));
		
	}
	
	public static String removeSpacesFromName(String[]nameParts) {
		int arraySize=nameParts.length;
		StringBuilder removeSpace = new StringBuilder();
		
		for(int i=0;i<arraySize;i++) {
			if(i==0) {
				removeSpace.append(capitilizeOnlyFirstLetter(nameParts[i]));
			}else {
				removeSpace.append(nameParts[i].trim().toLowerCase());
			}
		}
		return removeSpace.toString();
	}
	
	
	
	



	
}
