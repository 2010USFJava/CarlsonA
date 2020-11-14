package com.revature.Meta;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.Users.User;

public class StringCheckTest {

	@Test 
	public void capitilizeOnlyFirstLetter() {
		String result=StringCheck.capitilizeOnlyFirstLetter("ddEEEdD");
		assertEquals(result,"Ddeeedd");
	}
	
	@Test 
	public void splitName() {
		String[] result=StringCheck.splitName("Jow Joe Jooe");
		String[] expected = {"Jow","Joe","Jooe"};
		assertEquals(expected,result);
	}
	
	public void dashifyName() {
		String result=StringCheck.dashifyName("Bob the BuIlDer");
		assertEquals("Bob-The-Builder",result);
	}
	
	@Test
	public void removeSpacesFromName() {
		String result=StringCheck.removeSpacesFromName("   d   dd ddd  ");
		assertEquals("Dddddd",result);
	}

}
