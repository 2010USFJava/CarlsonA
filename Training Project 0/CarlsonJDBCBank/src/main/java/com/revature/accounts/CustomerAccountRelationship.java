package com.revature.accounts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import com.revature.Users.Customer;


public class CustomerAccountRelationship {
	private static Map<Customer,Set<AbstractAccount>> customerToAccountMap=new HashMap<>();
	private static Map<AbstractAccount,Set<Customer>> accountToCustomerMap=new HashMap<>();
	
	//getters and setters
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

	

	public static void setCustomerToAccountMap(Map<Customer, Set<AbstractAccount>> customerToAccountMap) {
		CustomerAccountRelationship.customerToAccountMap = customerToAccountMap;
	}
	
	public static void setAccountToCustomerMap(Map<AbstractAccount, Set<Customer>> accountToCustomerMap) {
		CustomerAccountRelationship.accountToCustomerMap = accountToCustomerMap;
	}
	
	//adding/updating customers
	public static void updateCustomer(Customer cust, AbstractAccount act) {
		//update customer to account map
		 updateCustomerToAccountMap(cust,act);
		 updateAccountToCustomerMap(cust,act);

	}
	
	private static void updateCustomerToAccountMap(Customer cust, AbstractAccount act) {
		//if the customer is already in the map, update the map
		if (checkIfCustomerKeyExists(cust)) {
			getAccountSetForCustomer(cust).add(act);
			//otherswise, make a new hashset of accounts
		} else {
			Set<AbstractAccount> newAccountSet=new HashSet<>();
			newAccountSet.add(act);
			customerToAccountMap.put(cust, newAccountSet);
		}
	}
	
	private static void updateAccountToCustomerMap(Customer cust, AbstractAccount act) {
		//update account to Customer map
		if(checkIfAccountKeyExists(act)) {
			getCustomerSetForAccount(act).add(cust);
		} else {
			Set<Customer> newCustomerSet=new HashSet<>();
			newCustomerSet.add(cust);
			accountToCustomerMap.put(act, newCustomerSet);
		}
	}
	
	public static void removeCustomerFromAccount(Customer cust, AbstractAccount act) {
		if(checkIfAccountKeyExists(act)) {
			Set workingSet=getCustomerSetForAccount(act);
			workingSet.remove(cust);
		}
	}
	
	//check if key's exists
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
