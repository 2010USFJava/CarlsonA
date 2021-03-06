package pro0MavenBank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.Revature.AccountInfo.AbstractAccount;
import com.Revature.Users.Customer;

public class AccountTests {
	public static Customer custA=new Customer("John","Doe",true);

	
	@Test
	public void checkStatusInApplicationOnCreation() {

		AbstractAccount testAccount=new AbstractAccount(custA);
		assertEquals(AbstractAccount.AccountStatusEnum.IN_APPLICATION,testAccount.getAccountStatus());
	}
	
	@Test
	public void closeAccount() {

		AbstractAccount testAccount=new AbstractAccount(custA);
		testAccount.closeAccount();
		assertEquals(AbstractAccount.AccountStatusEnum.CLOSED,testAccount.getAccountStatus());
	}
	
	@Test
	public void approveAccount() {

		AbstractAccount testAccount=new AbstractAccount(custA);
		testAccount.approveAccount();
		assertEquals(AbstractAccount.AccountStatusEnum.OPEN,testAccount.getAccountStatus());
	}
	
	@Test
	public void tryToAddMoneyToInApplicationAccount() {

		AbstractAccount testAccount=new AbstractAccount(custA);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.IN_APPLICATION);
		testAccount.deposit(100);
		assertEquals(0,testAccount.getBalance());
	}
	
	@Test
	public void tryToAddMoneyToClosedAccount() {

		AbstractAccount testAccount=new AbstractAccount(custA);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.CLOSED);
		testAccount.deposit(100);
		assertEquals(0,testAccount.getBalance());
	}
	
	@Test
	public void tryToithdrawMoneyFromInApplicationAccount() {

		AbstractAccount testAccount=new AbstractAccount(custA);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.OPEN);
		testAccount.deposit(100);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.IN_APPLICATION);
		testAccount.withdraw(50);
		assertEquals(100,testAccount.getBalance());
	}
	
	@Test
	public void tryToWithdrawMoneyFromClosedAccount() {

		AbstractAccount testAccount=new AbstractAccount(custA);

		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.OPEN);
		testAccount.deposit(100);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.CLOSED);
		testAccount.withdraw(50);
		assertEquals(100,testAccount.getBalance());
	}
	
	
	
	
	@Test
	public void tryToAddMoneyToOpenAccount() {
		AbstractAccount testAccount=new AbstractAccount(custA);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.OPEN);
		testAccount.deposit(100);
		assertEquals(100,testAccount.getBalance());
	}
	
	@Test
	public void withdrawMoneyFromOpenAccount() {
		AbstractAccount act = new AbstractAccount(custA);
		act.approveAccount();
		act.deposit(200);
		act.withdraw(100);
		assertEquals(100,act.getBalance());
	}
	
	@Test
	public void withdrawTooMuchMoneyFromOpenAccount() {
		AbstractAccount act = new AbstractAccount(custA);
		act.approveAccount();
		act.withdraw(100);
		assertEquals(0,act.getBalance());
	}
	
	@Test
	public void withdrawNegitiveAmountFromOpenAccount() {
		AbstractAccount act = new AbstractAccount(custA);
		act.approveAccount();
		act.withdraw(-100);
		assertEquals(0,act.getBalance());
	}
	
	@Test
	public void vaildFundsTransferTest() {
		AbstractAccount actA = new AbstractAccount(custA);
		actA.approveAccount();
		actA.deposit(200);
		AbstractAccount actB = new AbstractAccount(custA);
		actB.approveAccount();
		actA.transferMoneyToAccount(actB,100);

		assertEquals(100,actB.getBalance());
	}
	
	@Test
	public void invalidFundsTransferTest() {
		AbstractAccount actA = new AbstractAccount(custA);
		actA.approveAccount();
		actA.deposit(200);
		actA.closeAccount();
		AbstractAccount actB = new AbstractAccount(custA);
		actB.approveAccount();
		actA.transferMoneyToAccount(actB,100);

		assertEquals(0,actB.getBalance());
	}
	
	
	
	

}
