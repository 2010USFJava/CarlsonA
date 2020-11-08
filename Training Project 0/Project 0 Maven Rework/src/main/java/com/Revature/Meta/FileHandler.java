package com.Revature.Meta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.Revature.AccountInfo.Account;
import com.Revature.AccountInfo.CustomerAccountRelationship;
import com.Revature.Users.Customer;
import com.Revature.Users.LoginInfo;

public class FileHandler {
	public static final String loginFile = "loginFile.txt";
	public static final String custAccountFile="custAccount.txt";
	public static final String actCustFile="actCust.txt";
	

	private static Map<Customer,Set<Account>> customerToAccountMap = CustomerAccountRelationship.getCustomerToAccountMap();
	private static Map<Account,Set<Customer>> accountToCustomerMap = CustomerAccountRelationship.getAccountToCustomerMap();
	
	
	//write method
	public static void writeLoginFile( Map<String,LoginInfo> loginMap) {
	
		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(loginFile));
			objectOut.writeObject(loginMap);
			objectOut.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeCustAccountFile() {
		
		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(custAccountFile));
			objectOut.writeObject(customerToAccountMap);
			objectOut.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeActCustFile() {
		
		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(actCustFile));
			objectOut.writeObject(accountToCustomerMap);
			objectOut.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//read methiod
	public static void readLoginFile() {
		try {
			ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(loginFile));
			
			LoginInfo.setLoginMap((Map<String,LoginInfo>)objIn.readObject());
		}catch (FileNotFoundException e) {
			System.err.println("Login file does not exist. Creating it now...");
//			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void readCustActFile() {
		try {
			ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(custAccountFile));
			CustomerAccountRelationship.setCustomerToAccountMap((Map<Customer,Set<Account>>)objIn.readObject());
			
		}catch (FileNotFoundException e) {
			System.err.println("Customer->Account file does not exist. Creating it now...");
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void readActCustFile() {
		try {
			ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(actCustFile));
			CustomerAccountRelationship.setAccountToCustomerMap((Map<Account,Set<Customer>>)objIn.readObject());
//			LoginInfo.setLoginMap((Map<String,LoginInfo>)objIn.readObject());
		}catch (FileNotFoundException e) {
			System.err.println("Accont->Customer file does not exist. Creating it now...");
//			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveAll() {
		writeLoginFile(LoginInfo.getLoginMap());
		writeCustAccountFile();
		writeActCustFile();
	}
	
	public static void loadAll() {
		
		readLoginFile();
		readCustActFile();
		readActCustFile();

		//if first time running, make sure to create new items
		if(customerToAccountMap==null ||accountToCustomerMap==null) {
			new CustomerAccountRelationship();
		}

	
	}

}
