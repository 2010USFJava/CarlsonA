package com.Revature.Meta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.Revature.AccountInfo.Account;
import com.Revature.AccountInfo.CustomerAccountRelationship;
import com.Revature.Meta.LogThis.LevelEnum;
import com.Revature.Users.Customer;
import com.Revature.Users.LoginInfo;

public class FileHandler {
	public static final String loginFile = "loginFile.txt";
	public static final String custAccountFile="custAccount.txt";
	public static final String actCustFile="actCust.txt";
	public static final String applicationQueueFile="applicationQueueFile.txt";
	

	private static Map<Customer,Set<Account>> customerToAccountMap = CustomerAccountRelationship.getCustomerToAccountMap();
	private static Map<Account,Set<Customer>> accountToCustomerMap = CustomerAccountRelationship.getAccountToCustomerMap();
//	private static Queue<Account> applicationQueue=Account.getApplicationQueue();
	
	
	//write method
	public static void writeApplicationQueueFile(Queue<Account> applicationQueue) {
//		LogThis.logIt(LevelEnum.DEBUG, "Writing ApplicationQueueFile");
		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(applicationQueueFile));
			objectOut.writeObject(applicationQueue);
			objectOut.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
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
	
	public static void readApplicationQueueFile() {
//		LogThis.logIt(LevelEnum.DEBUG, "Read ApplicationQueueFile");
		
		try {
			ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(applicationQueueFile));
			Account.setApplicationQueue((Queue<Account>)objIn.readObject());
			
		}catch (FileNotFoundException e) {
			System.err.println("Application file does not exist. Creating it now...");
//			applicationQueue=new LinkedList<>();
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveAll() {

//		LogThis.logIt(LevelEnum.DEBUG, "Saving All Files");

//		LogThis.logIt(LevelEnum.DEBUG, "BigS: MAP");
		writeLoginFile(LoginInfo.getLoginMap());
		
//		LogThis.logIt(LevelEnum.DEBUG, "BigS: CustAccount");
		writeCustAccountFile();
		
//		LogThis.logIt(LevelEnum.DEBUG, "BigS: AccountCust");
		writeActCustFile();
		
//		LogThis.logIt(LevelEnum.DEBUG, "BigS: ApplicationQueue");
		writeApplicationQueueFile(Account.getApplicationQueue());
	}
	
	public static void loadAll() {

//		LogThis.logIt(LevelEnum.DEBUG, "Loading All Files");

//		LogThis.logIt(LevelEnum.DEBUG, "BigW ApplicationQueue");
		readApplicationQueueFile();
		
//		LogThis.logIt(LevelEnum.DEBUG, "BigW LoginFile");
		readLoginFile();
		
//		LogThis.logIt(LevelEnum.DEBUG, "BigW CustActFile");
		readCustActFile();
		

//		LogThis.logIt(LevelEnum.DEBUG, "BigW ActCustFile");
		readActCustFile();

		//if first time running, make sure to create new items
		if(customerToAccountMap==null ||accountToCustomerMap==null) {
			new CustomerAccountRelationship();
		}

	
	}

}
