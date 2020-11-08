
package com.Revature.Users;

import java.io.Serializable;
import java.util.Set;

import com.Revature.AccountInfo.Account;
import com.Revature.AccountInfo.CustomerAccountRelationship;
import com.Revature.AccountInfo.JointAccount;


public class Customer extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5844012040276987566L;
	private Set<Account> userAccounts=CustomerAccountRelationship.getAccountSetForCustomer(this);
	
	{setUserType(UserTypeEnum.CUSTOMER);}
	

	public Customer(String firstName,String middleName,String lastName) {
		super(firstName,middleName,lastName);
		
	}
	
	public Customer(String firstName,String middleName,String lastName,boolean isTest) {
		super(firstName,middleName,lastName,isTest);
		
	}
	
	public Customer(String firstName, String lastName) {
		super(firstName,lastName);
	}
	
	public Customer(String firstName, String lastName,boolean isTest) {
		super(firstName,lastName,isTest);
	}
	
	//getters and setters
	public Set<Account> getUserAccounts() {
		
		if(userAccounts.isEmpty()) {
			System.out.println("This user has no accounts at this time");
			
		}
		return userAccounts;
	}

	//account interaction
	public Account createAccount() {
		return createAccount(this);
		
	}
	
	private void addToUserAccounts(Customer cust, Account acct) {
		if (this.userAccounts==null) {
			CustomerAccountRelationship.updateCustomer(cust, acct);
		} else {

			userAccounts.add(acct);
		}
	}
	
	public Account createAccount(Customer cust) {
		Account acct;
		if (cust.equals(this)) {
			acct=new Account(this);
		} else {
			acct= new JointAccount(this,cust);

			addToUserAccounts(cust,acct);
			
		}
		

		addToUserAccounts(this,acct);
		
		
		return acct;
		
	}
	
	public Account createJointAccount(Customer cust) {
		Account acct;
		if (cust.equals(this)) {
			acct=new JointAccount(this);

			addToUserAccounts(this,acct);
		} else {
			acct= new JointAccount(this,cust);

			addToUserAccounts(this,acct);

			addToUserAccounts(cust,acct);
		}
		return acct;
		
	}
	
	public Account createJointAccount() {
		return createJointAccount(this);
	}
	
	public String printAccounts() {
		String output="";
		if (userAccounts==null || userAccounts.isEmpty()) {
			output+="This user has no accounts at this time.";
		}else {
			output+="User Accounts"+"\n";
			int i=0;
			for(Account act:userAccounts) {
				//adding some veriaty for ease of reading 
				if (i%2==0) {output+="\t";}
				output+="\t"+act+"\n";
			}
		}
		
		return output;
		
	}
	
	@Override
	public String toString() {
		return super.toString()+"\n"+printAccounts();
	}

	public int getNumberOfAccounts() {
		
		return userAccounts.size();
	}
	

}
