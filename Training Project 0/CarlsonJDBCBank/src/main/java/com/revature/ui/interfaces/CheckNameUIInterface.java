package com.revature.ui.interfaces;

public interface CheckNameUIInterface {
	String checkName(String name,String partOfName);
	
	//For now, drop middle name handler
	//it's complex and not needed for this project
	//Also: Create a seperate MiddleName object to handle it if I move forward with this.
//	String checkMiddleName(String name,MiddleName middleName);
}
