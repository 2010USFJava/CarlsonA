package com.revature.accounts;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;


import com.revature.Meta.LogThis;
import com.revature.Meta.LogThis.LevelEnum;
import com.revature.Users.Customer;

public abstract class AbstractAccount implements Serializable {

//account enums
	public enum AccountStatusEnum {
		OPEN,
		CLOSED,
		IN_APPLICATION,
		REJECTED;
	}

	
	public enum AccountTypeEnum{
		SINGLE,
		JOINT;
		
	}
	

	//variables
	private static final long serialVersionUID = 4544554673719221571L;
	private static NumberFormat currencyForm = NumberFormat.getCurrencyInstance();
	
	private int accountId;
	private long balance=0;
	private AccountStatusEnum accountStatus;
	protected AccountTypeEnum accountType = AccountTypeEnum.SINGLE;
	
	protected Set<Customer> customerSet=CustomerAccountRelationship.getCustomerSetForAccount(this);
	protected static Queue<AbstractAccount> applicationQueue=getApplicationQueue();
//	private transient Random rand = new Random();
	
	//constructor
	public AbstractAccount(Customer accountHolder) {
		this(accountHolder,AccountStatusEnum.IN_APPLICATION);
	}
	
	public AbstractAccount(Customer accountHolder,AccountStatusEnum status) {
		updateCustAcctMaps(accountHolder);
		changeStatus(status);
		}
	
	public AbstractAccount(int id, long balance, Customer accountHolder,AccountStatusEnum status) {
		this(accountHolder,status);
		this.accountId=id;
		this.balance=balance;
	}
	
	//getters and setters
	
	public Set<Customer> getCustomerSet(){
		
		try {
				customerSet=CustomerAccountRelationship.getCustomerSetForAccount(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
			return customerSet;
			
	}
	
	public void setCustomerSet(Set<Customer> customerSetByAccount) {
		this.customerSet=customerSetByAccount;
		
	}
	

	public int getId() {
		return accountId;
	}
	
	public long getBalance() {
		return balance;
	}

	//customer set manipulation
	protected void updateCustAcctMaps(Customer cust) {
		CustomerAccountRelationship.updateCustomer(cust,this);
	}
	
	public static Queue<AbstractAccount> getApplicationQueue() { 
		
		if(applicationQueue==null) {
			applicationQueue=new LinkedList<>();
			LogThis.logIt(LevelEnum.TRACE, "No application queue existed. Createing a new one now");

		}
		
		return applicationQueue;
	}
	
	//Joint accounts now have unlimited membership, not just two
	public void setSecondAccountHolder(Customer secondAccountHolder) {
		if(accountType.equals(AccountTypeEnum.SINGLE)) {
			String output="Can not add another user to a single account";
			System.err.println(output);
			LogThis.logIt(LevelEnum.ERROR, "Attempted to add another user to a single account");
		}else {
			updateCustAcctMaps(secondAccountHolder);
		}
		
	}
	
	
	//balnce manipulation
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
		}
	
	

	
	
	public long transferMoneyToAccount(AbstractAccount account, long money) {
		

		if(accountStatus.equals(AccountStatusEnum.OPEN)&&account.accountStatus.equals(AccountStatusEnum.OPEN)) {
			long amountTransfered=withdraw(money);
			account.deposit(amountTransfered);


			LogThis.logIt(LevelEnum.INFO, "Transfered between accounts "+convertMoney(amountTransfered));
			return amountTransfered;
			
			
		} else {
			System.out.println("One or both of these accounts are not open");
			return 0;
		}
	}
		
	
	//display balance info
	public void printBalance() {
		System.out.println("Current avaliable balance: "+convertMoney(balance));
		
	}
	
	
	private static String convertMoney(double money) {
		return currencyForm.format(money);
	}
	
	public String getAccountInformation() {
		return "Account #"+accountId+"\n"
				+ "Balance: "+convertMoney(balance)+"\n";

	}
	
	
	
	public String getAllCustomerPersonalData() {
		
		StringBuilder output= new StringBuilder();
		int x=1;
		for (Customer cust:customerSet) {
			output.append("Account Holder "+x+":\t"
					
					+"First Name: "+cust.getFirstName()+"\t"
					+ "Last Name: "+cust.getLastName()+"\n");
			
		}
		return output.toString();
	}
	
	//account type
	//*Looking to depreciate this in the future.
	protected void setAccountType(AccountTypeEnum type) {
		this.accountType=type;
	}
	
	//change account status
		public String printAccountStatus() {
			StringBuilder output=new StringBuilder();
			
			switch (accountType) {
			case SINGLE:
				output.append("Single ");
				break;
			case JOINT:
				
				output.append("Joint ");
				break;
			}
			output.append(getAllCustomerPersonalData()+"\nAccount #"+accountId+" is in ");
			
			
			switch (accountStatus) {
			case IN_APPLICATION:
				output.append("Application");
				break;
			case OPEN:
				output.append("Open");
				break;
			case CLOSED:
				output.append("Closed");
				break;
			case REJECTED:
				output.append("Rejected");
				break;
			default:
				String errorMessage="Error: Account Status Does Not Exist";
				System.err.println("\n"+errorMessage+"\n");
				LogThis.logIt(LevelEnum.ERROR,errorMessage);
				break;
			}
			output.append(" status.");
			return output.toString();
		}
		
	//applicationQueue Things
		public static AbstractAccount getNextApplication() {
			return applicationQueue.peek();			
		}
		
		public void approveAccount() {
			changeStatus(AccountStatusEnum.OPEN);
		}
		
		
		public void closeAccount() {
			changeStatus(AccountStatusEnum.CLOSED);
		}
		
		public void changeStatus(AccountStatusEnum status) {
			this.accountStatus=status;
			if(accountStatus.equals(AccountStatusEnum.IN_APPLICATION)){
				//runnint the get statment ensures that a queue is built
				getApplicationQueue();
				applicationQueue.add(this);
			} else {
				applicationQueue.remove(this);	
			}
		}
			
		
		
	@Override
	public String toString() {
		return printAccountStatus()+" Balance:"+convertMoney(balance);
	}

	public static void setApplicationQueue(Queue<AbstractAccount> applicationQueue2) {
		applicationQueue = applicationQueue2;
		
	}



}
