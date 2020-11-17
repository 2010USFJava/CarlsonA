package com.Revature.AccountInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.Revature.Meta.FileHandler;
import com.Revature.Users.Customer;

public class CustomerAccountRelationship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4409635919393685407L;
	private static Map<Customer,Set<AbstractAccount>> customerToAccountMap=new HashMap<>();
	private static Map<AbstractAccount,Set<Customer>> accountToCustomerMap=new HashMap<>();
	
	
	
	
//	Getters and setters
	
	public static Set<Customer> getCustomerSetForAccount(AbstractAccount act){
		if(!checkIfAccountKeyExists(act)) {
			Set<Customer> newCustomerSet=new HashSet<>();
			accountToCustomerMap.put(act, newCustomerSet);
		}	
		return accountToCustomerMap.get(act);
	}
	
	public static Set<AbstractAccount> getAccountSetForCustomer(Customer cust) {
		if(!checkIfCustomerKeyExists(cust)) {
			Set<AbstractAccount> newAccountSet=new HashSet<>();
			customerToAccountMap.put(cust, newAccountSet);
		}

		return customerToAccountMap.get(cust);

		
	}
	
	

	public static Map<Customer, Set<AbstractAccount>> getCustomerToAccountMap() {
		return customerToAccountMap;
	}

	public static void setCustomerToAccountMap(Map<Customer, Set<AbstractAccount>> customerToAccountMap) {
		CustomerAccountRelationship.customerToAccountMap = customerToAccountMap;
	}

	public static Map<AbstractAccount, Set<Customer>> getAccountToCustomerMap() {
		return accountToCustomerMap;
	}

	public static void setAccountToCustomerMap(Map<AbstractAccount, Set<Customer>> accountToCustomerMap) {
		CustomerAccountRelationship.accountToCustomerMap = accountToCustomerMap;
	}
	
	public static void updateCustomer(Customer cust, AbstractAccount act) {
		Set<AbstractAccount> workingAccountSet;
		Set<Customer> workingCustomerSet;
		//update customer to account map
		if (checkIfCustomerKeyExists(cust)) {
			workingAccountSet=getAccountSetForCustomer(cust);
			workingAccountSet.add(act);
		} else {
			Set<AbstractAccount> newAccountSet=new HashSet<>();
			newAccountSet.add(act);
			customerToAccountMap.put(cust, newAccountSet);
		}
		
		//update account to Customer map
		if(checkIfAccountKeyExists(act)) {
			workingCustomerSet=getCustomerSetForAccount(act);
			workingCustomerSet.add(cust);
		} else {
			Set<Customer> newCustomerSet=new HashSet<>();
			newCustomerSet.add(cust);
			accountToCustomerMap.put(act, newCustomerSet);
		}
		FileHandler.saveAll();
	}
	
	public static void removeCustomerFromAccount(Customer cust, AbstractAccount act) {
		if(checkIfAccountKeyExists(act)) {
			Set workingSet=getCustomerSetForAccount(act);
			workingSet.remove(cust);
		}
		FileHandler.saveAll();
	}
	
	private static boolean checkIfCustomerKeyExists(Customer cust) {

			if(customerToAccountMap.get(cust)==null) {
				return false;
			} else {
				return true;
			}
			
			
	}
	


	private static boolean checkIfAccountKeyExists(AbstractAccount act) {
		
		if(accountToCustomerMap.get(act)==null) {
			return false;
		} else {
			return true;
		}
	
	}
	

	
	
	

	
}
