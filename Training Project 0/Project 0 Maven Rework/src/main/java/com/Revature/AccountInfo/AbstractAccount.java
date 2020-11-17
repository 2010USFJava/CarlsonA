package com.Revature.AccountInfo;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import com.Revature.Meta.FileHandler;
import com.Revature.Meta.LogThis;
import com.Revature.Meta.RuntimeData;
import com.Revature.Meta.LogThis.LevelEnum;
import com.Revature.Users.Customer;
import com.Revature.Users.User;

/**
 * @author Owner
 *
 */
public class AbstractAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1422180158411166919L;


	public enum AccountStatusEnum {
		OPEN,
		CLOSED,
		IN_APPLICATION,
		REJECTED;
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
	private long balance=0;
	private AccountStatusEnum accountStatus=AccountStatusEnum.IN_APPLICATION;
	protected AccountTypeEnum accountType = AccountTypeEnum.SINGLE;
	protected Set<Customer> customerSet=CustomerAccountRelationship.getCustomerSetForAccount(this);
	protected static Queue<AbstractAccount> applicationQueue=new LinkedList<>();
	private Customer accountHolder;
	private transient Random rand = new Random();
	
	
	{	randomizeId();}
//		accountMap.put(accountId, this);}
	
	private void randomizeId() {
		accountId=rand.nextInt(100000);
	}
	
	protected void updateCustAcctMaps(Customer cust) {
		CustomerAccountRelationship.updateCustomer(cust,this);
	}
	
	//constructors
	public AbstractAccount(Customer accountHolder,AccountStatusEnum status) {
		this.accountHolder=accountHolder;
		updateCustAcctMaps(accountHolder);
		this.accountStatus=status;
		
		try {

			if(status.equals(AccountStatusEnum.IN_APPLICATION)) {
				applicationQueue.add(this);
			}	
			
		}catch(NullPointerException e) {
			applicationQueue=new LinkedList<>();
			applicationQueue.add(this);
			LogThis.logIt(LevelEnum.TRACE, "No application queue existed when attempted to add "+this+". Createing a new one now");
		}
		
		FileHandler.saveAll();
		
		

	}
	public AbstractAccount(Customer accountHolder) {
		this(accountHolder,AccountStatusEnum.IN_APPLICATION);
	}
	
	
	
	public Customer getAccountHolder() {
		return accountHolder;
	}


	
	public static Queue<AbstractAccount> getApplicationQueue() {
		return applicationQueue;
	}

	public static void setApplicationQueue(Queue<AbstractAccount> newApplicationQueue) {
		applicationQueue = newApplicationQueue;

	}

	//change account status
	public String printAccountStatus() {
		String output="";
		
		switch (accountType) {
		case SINGLE:
			output+="Single "+this.getAccountHolder().getLastName()+" ";
			break;
		case JOINT:
			JointAccount joint=(JointAccount)this;
			output+="Joint "+joint.getAccountHolder().getLastName()+", ";
			
			if(joint.getSecondAccountHolder()!=null) {

				output+=joint.getSecondAccountHolder().getLastName()+" ";
					
			}
			break;
		}
		
		
		output+="Account #"+accountId+" is in ";

		switch (accountStatus) {
		case IN_APPLICATION:
			output+="Application";
			break;
		case OPEN:
			output+="Open";
			break;
		case CLOSED:
			output+="Closed";
			break;
		case REJECTED:
			output+="Rejected";
			break;
		default:
			break;
		}
		
		output+=" status.";
		return output;
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
				
			}
			balance-=money;
			printBalance();

			LogThis.logIt(LevelEnum.INFO, "Withdrew "+convertMoney(money));
			
			}else {
			System.out.println("Did not withdraw money. Account not open");

			money= 0;
			}
		

		FileHandler.saveAll();
		return money;
		
		

	}
	
	public void deposit(long money) {
		if(accountStatus.equals(AccountStatusEnum.OPEN)) {
			
			if(money<0) {
				System.out.println("Can not deopsit a negative amount");
				money=0;
			}
			balance+=money;
			System.out.println("Deposited: "+convertMoney(money));
			printBalance();
			

			LogThis.logIt(LevelEnum.INFO, "Deposited "+convertMoney(money));

			
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
	
	
	public long transferMoneyToAccount(AbstractAccount account, long money) {
		

		if(accountStatus.equals(AccountStatusEnum.OPEN)&&account.accountStatus.equals(AccountStatusEnum.OPEN)) {
			long amountTransfered=withdraw(money);
			account.deposit(amountTransfered);
			FileHandler.saveAll();

			LogThis.logIt(LevelEnum.INFO, "Transfered between accounts "+convertMoney(amountTransfered));
			return amountTransfered;
			
			
		} else {
			System.out.println("One or both of these accounts are not open");
			return 0;
		}
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
		String output=printAccountStatus()+" Balance:"+convertMoney(balance);
		
		return output;
	}
	
	
	
	

	public static void enterApplicationQueue(AbstractAccount act) {
		applicationQueue.add(act);
		FileHandler.saveAll();
	}
	

	
	
	public static AbstractAccount getNextApplication() {
		try {
			return applicationQueue.peek();
		} catch(NullPointerException e){
			LogThis.logIt(LevelEnum.TRACE, "No application Queue existed when Employee tried to pull from it. Creating one now...");
			applicationQueue=new LinkedList<>();
			return applicationQueue.peek();
		}
		
	}
	
//	public void approveApplication() {
//		this.accountStatus=AccountStatusEnum.OPEN;
//		applicationQueue.remove(this);
//		FileHandler.saveAll();
//
//	}
//	
//	public void rejectApplication() {
//
//		this.accountStatus=AccountStatusEnum.REJECTED;
//
//		applicationQueue.remove(this);
//		//In the future add a function to eventually delete rejected applicatons
//		FileHandler.saveAll();
//		
//	}
	
	
	public void approveAccount() {
		changeStatus(AccountStatusEnum.OPEN);
	}
	
	
	public void closeAccount() {
		changeStatus(AccountStatusEnum.CLOSED);
	}
	
	public void changeStatus(AccountStatusEnum status) {
		this.accountStatus=status;
		applicationQueue.remove(this);
		FileHandler.saveAll();
	}
	
	public AccountStatusEnum getAccountStatus() {
		return accountStatus;
	}
	
}
