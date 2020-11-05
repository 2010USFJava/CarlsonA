package pro0MavenBank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.Revature.AccountInfo.Account;
import com.Revature.Users.Customer;

public class AccountTests {
	public static Customer custA=new Customer("John","Doe",true);

	
	@Test
	public void checkStatusInApplicationOnCreation() {

		Account testAccount=new Account(custA);
		assertEquals(Account.AccountStatusEnum.IN_APPLICATION,testAccount.getAccountStatus());
	}
	
	@Test
	public void closeAccount() {

		Account testAccount=new Account(custA);
		testAccount.closeAccount();
		assertEquals(Account.AccountStatusEnum.CLOSED,testAccount.getAccountStatus());
	}
	
	@Test
	public void approveAccount() {

		Account testAccount=new Account(custA);
		testAccount.approveAccount();
		assertEquals(Account.AccountStatusEnum.OPEN,testAccount.getAccountStatus());
	}
	
	@Test
	public void tryToAddMoneyToInApplicationAccount() {

		Account testAccount=new Account(custA);
		testAccount.changeStatus(Account.AccountStatusEnum.IN_APPLICATION);
		testAccount.deposit(100);
		assertEquals(0,testAccount.getBalance());
	}
	
	
	
	@Test
	public void tryToAddMoneyToOpenAccount() {

		Account testAccount=new Account(custA);
		testAccount.changeStatus(Account.AccountStatusEnum.OPEN);
		testAccount.deposit(100);

		assertEquals(100,testAccount.getBalance());
		
	}
	
	@Test
	public void withdrawMoneyFromOpenAccount() {
		Account act = new Account(custA);
		act.approveAccount();
		act.deposit(200);
		act.withdraw(100);
		assertEquals(100,act.getBalance());
	}
	
	@Test
	public void withdrawTooMuchMoneyFromOpenAccount() {
		Account act = new Account(custA);
		act.approveAccount();
		act.withdraw(100);
		assertEquals(0,act.getBalance());
	}
	
	@Test
	public void withdrawNegitiveAmountFromOpenAccount() {
		Account act = new Account(custA);
		act.approveAccount();
		act.withdraw(-100);
		assertEquals(0,act.getBalance());
	}
	
	
	
	

}
