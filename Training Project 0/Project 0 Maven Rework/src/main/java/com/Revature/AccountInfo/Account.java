package com.Revature.AccountInfo;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Set;

import com.Revature.Meta.FileHandler;
import com.Revature.Users.Customer;

/**
 * @author Owner
 *
 */
public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1422180158411166919L;


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
//	private static Map<Integer,Account> accountMap = new HashMap<>();
	
	//primary variables
	private int accountId;
//	Remember to format this later
	private long balance=0;
	private AccountStatusEnum accountStatus=AccountStatusEnum.IN_APPLICATION;
	protected AccountTypeEnum accountType = AccountTypeEnum.SINGLE;
	protected Set<Customer> customerSet=CustomerAccountRelationship.getCustomerSetForAccount(this);
	private Customer accountHolder;
	
	{	incrementId();}
//		accountMap.put(accountId, this);}
	
	private void incrementId() {
		accountId=idTracker++;
	}
	
	protected void updateCustAcctMaps(Customer cust) {
		CustomerAccountRelationship.updateCustomer(cust,this);
	}
	
	//constructors
	public Account(Customer accountHolder,AccountStatusEnum status) {
		this.accountHolder=accountHolder;
		updateCustAcctMaps(accountHolder);
		this.accountStatus=status;
		FileHandler.saveAll();
		
		

	}
	public Account(Customer accountHolder) {
		this(accountHolder,AccountStatusEnum.IN_APPLICATION);
	}
	
	
	
	public Customer getAccountHolder() {
		return accountHolder;
	}


	//change account status
	public String printAccountStatus() {
		String output="";
		
		
		output+="This Account #"+accountId+" is in ";

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
		changeStatus(AccountStatusEnum.OPEN);
	}
	
	
	public void closeAccount() {
		changeStatus(AccountStatusEnum.CLOSED);
	}
	
	public void changeStatus(AccountStatusEnum status) {
		this.accountStatus=status;
		FileHandler.saveAll();
	}
	
	public AccountStatusEnum getAccountStatus() {
		return accountStatus;
	}
	
	protected void setAccountType(AccountTypeEnum accountType) {
		this.accountType=accountType;

		FileHandler.saveAll();
	}
	
	
	
	//balance adjustments
	public long withdraw(long money) {
		if(accountStatus.equals(AccountStatusEnum.OPEN)) {
			if(balance<money) {
				money=balance;
				System.out.println("Not enough money in account. Withdrawing maximum amount: "+convertMoney(money));
			} else if(money<0){
				System.out.println("Cannot withdraw a negative amount");
				money=0;
			}else {
				System.out.println("Withdrawing: "+convertMoney(money)+".");
				printBalance();
			}
			balance-=money;
			

			
			}else {
			System.out.println("Did not withdraw money. Account not open");

			money= 0;
			}
		

		FileHandler.saveAll();
		return money;
		
		

	}
	
	public void deposit(long money) {
		if(accountStatus.equals(AccountStatusEnum.OPEN)) {
			balance+=money;
			System.out.println("Deposited: "+convertMoney(money));
			printBalance();

			
			}else {
			System.out.println("Did not deposit money. Account not open");
			}

		FileHandler.saveAll();
		}
	
	public void printBalance() {
		System.out.println("Current avaliable balance: "+convertMoney(balance));
		
	}
	
	public long getBalance() {
		return balance;
	}
	
	private static String convertMoney(double money) {
		return currencyForm.format(money);
	}
	
	
	public long transferMoneyToAccount(Account account, long money) {
		

		if(accountStatus.equals(AccountStatusEnum.OPEN)&&account.accountStatus.equals(AccountStatusEnum.OPEN)) {
			long amountTransfered=withdraw(money);
			account.deposit(amountTransfered);
			FileHandler.saveAll();
			return amountTransfered;
			
			
		} else {
			System.out.println("One or both of these accounts are not open");
			return 0;
		}
		

		
//may want to add upper limit checker here
	}
	
//	public static Map<Integer,Account> getAccountDictionary() {
//		//for now anyone can see this. But for security reasons, I'll want to change
//		//the if condition to other validation conditions later
//		Map<Integer,Account> map=null;
//		if (true) {
//			map=accountMap;
//		}
//		return map;
//
//		
//	}
	
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
		String output=printAccountStatus();
		
		return output;
	}
}
