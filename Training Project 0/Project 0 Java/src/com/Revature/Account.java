package com.Revature;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import com.Revature.Account.AccountTypeEnum;

/**
 * @author Owner
 *
 */
public class Account {
	
	public enum AccountStatusEnum {
		OPEN,
		CLOSED,
		IN_APPLICATION;
	}

	
	protected enum AccountTypeEnum{
		SINGLE,
		JOINT;
		
	}
	
	//assisting variables
	private static int idTracker=0;
	private static NumberFormat currencyForm = NumberFormat.getCurrencyInstance();
	private static Map<Integer,Account> accountMap = new HashMap<>();
	
	//primary variables
	private int accountId;
//	Remember to format this later
	private double balance=0;
	private AccountStatusEnum accountStatus=AccountStatusEnum.IN_APPLICATION;
	protected AccountTypeEnum accountType = AccountTypeEnum.SINGLE;
	private Customer accountHolder;
	
	{	incrementId();
		accountMap.put(accountId, this);}
	
	//constructors
	public Account(Customer accountHolder) {
		this.accountHolder=accountHolder;

	}
	
	public Account(String firstName, String lastName) {
		 this(new Customer(firstName,lastName));
	}
	
	public Account(String firstName,  String middleName,String lastName) {
		 this(new Customer(firstName,middleName,lastName));
	}
	
	
	private void incrementId() {
		accountId=idTracker++;
	}
	
	
	
	public Customer getAccountHolder() {
		return accountHolder;
	}


	//change account status
	public String checkAccountStatus() {
		String output="This Account #"+accountId+" is in ";

		switch (accountStatus) {
		case IN_APPLICATION:
;			output+="Application";
			break;
		case OPEN:
;			output+="Open";
			break;
		case CLOSED:
;			output+="Closed";
			break;
		default:
			break;
		}
		
		output+=" status.";
		return output;
	}
	
	public void approveAccount() {
		accountStatus=AccountStatusEnum.OPEN;
	}
	
	
	public void closeAccount() {
		accountStatus=AccountStatusEnum.CLOSED;
	}
	
	public void changeStatus(AccountStatusEnum status) {
		this.accountStatus=status;
	}
	
	protected void setAccountType(AccountTypeEnum accountType) {
		this.accountType=accountType;
	}
	
	//balance adjustments
	public double withdraw(double money) {
		if(money<0) {
			System.out.println();
			money=-money;
		}
		
		if(balance<money) {
			money=balance;
			System.out.println("Not enough money in account. Withdrawing maximum amount: "+convertMoney(money));
		} else {
			System.out.println("Withdrawing: "+convertMoney(money)+".");
			printBalance();
			
		}
		balance-=money;
		return money;
	}
	
	public void deposit(double money) {
		balance+=money;
		System.out.println("Deposited: "+convertMoney(money));
		printBalance();
	}
	
	public void printBalance() {
		System.out.println("Current avaliable balance: "+convertMoney(balance));
		
	}
	
	private static String convertMoney(double money) {
		return currencyForm.format(money);
	}
	
	
	public double transferMoneyToAccount(Account account, double money) {
//may want to add upper limit checker here
		double amountTransfered=withdraw(money);
		account.deposit(amountTransfered);
		return amountTransfered;
	}
	
	public static Map<Integer,Account> getAccountDictionary() {
		//for now anyone can see this. But for security reasons, I'll want to change
		//the if condition to other validation conditions later
		Map<Integer,Account> map=null;
		if (true) {
			map=accountMap;
		}
		return map;

		
	}
	
	public String getAccountInformation() {
		String output="Account #"+accountId+"\n"
				+ "Balance: "+convertMoney(balance)+"\n";
		
		return output;
	}
	
	public String getCustomerPersonalData(Customer cust) {
		String output = "First Name: "+cust.getFirstName()+"\n"
				+ "Last Name: "+cust.getLastName()+"\n";
		return output;
	}
	
	public String getCustomerPersonalData() {
		return getCustomerPersonalData(this.accountHolder);
	}
	

	@Override
	public String toString() {
		String output=checkAccountStatus();
		
		return output;
	}
}
