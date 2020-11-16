package com.revature.Users;

import java.util.Set;
import java.util.logging.FileHandler;

import com.revature.accounts.AbstractAccount;
import com.revature.accounts.CustomerAccountRelationship;
import com.revature.accounts.JointAccount;
import com.revature.accounts.SingleAccount;

public class Customer extends User {
	private Set<AbstractAccount> userAccounts=CustomerAccountRelationship.getAccountSetForCustomer(this);
	
	
	public Customer(String firstname,String lastname) {
		super(firstname,lastname,UserTypeEnum.CUSTOMER);
	}



	//getters and setters
	public Set<AbstractAccount> getUserAccounts() {
		
		if(userAccounts.isEmpty()) {
			System.out.println("This user has no accounts at this time");
			
		}
		return userAccounts;
	}

	//account interaction

	
	private void addToUserAccounts(Customer cust, AbstractAccount acct) {
		if (this.userAccounts==null) {
			CustomerAccountRelationship.updateCustomer(cust, acct);
		} else {

			userAccounts.add(acct);
		}
	}
	public AbstractAccount createAccount() {
		return createAccount(this);
		
	}
	
	public AbstractAccount createAccount(Customer cust) {
		AbstractAccount acct;
		if (cust.equals(this)) {
			acct=new SingleAccount(this);

			addToUserAccounts(this,acct);
		} else {
			acct= new JointAccount(this,cust);

			addToUserAccounts(cust,acct);

			addToUserAccounts(this,acct);			
		}
		
		
		
		return acct;
		
	}
	
	public AbstractAccount createJointAccount(Customer cust) {
		AbstractAccount acct;
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
	
	public AbstractAccount createJointAccount() {
		return createJointAccount(this);
	}
	
	public String printAccounts() {
		StringBuilder output=new StringBuilder();
		if (userAccounts==null || userAccounts.isEmpty()) {
			output.append("This user has no accounts at this time.");
		}else {
			output.append("User Accounts"+"\n");
			int i=0;
			for(AbstractAccount act:userAccounts) {
				//adding some variety for ease of reading 
				if (i++%2==0) {output.append("\t");}
				output.append("\t"+act+"\n");
			}
		}
		
		return output.toString();
		
	}
	
	@Override
	public String toString() {
		return super.toString()+"\n"+printAccounts();
	}

	public int getNumberOfAccounts() {
		
		return userAccounts.size();
	}

}
