package com.revature;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.Users.Customer;
import com.revature.accounts.AbstractAccount;
import com.revature.accounts.SingleAccount;

public class AccountTests {
  
	Customer custA = new Customer(900,"Joe","Smith","joe","pass");
	
	@Test
	public void checkStatusInApplicationOnCreation() {

		SingleAccount testAccount=new SingleAccount(custA);
		assertEquals(SingleAccount.AccountStatusEnum.IN_APPLICATION,testAccount.getAccountStatus());
	}
	
	@Test
	public void closeAccount() {

		AbstractAccount testAccount=new SingleAccount(custA);
		testAccount.closeAccount();
		assertEquals(AbstractAccount.AccountStatusEnum.CLOSED,testAccount.getAccountStatus());
	}
	
	@Test
	public void approveAccount() {

		AbstractAccount testAccount=new SingleAccount(custA);
		testAccount.approveAccount();
		assertEquals(AbstractAccount.AccountStatusEnum.OPEN,testAccount.getAccountStatus());
	}
	
	@Test
	public void tryToAddMoneyToInApplicationAccount() {

		AbstractAccount testAccount=new SingleAccount(custA);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.IN_APPLICATION);
		testAccount.deposit(100);
		assertEquals(0,testAccount.getBalance());
	}
	
	@Test
	public void tryToAddMoneyToClosedAccount() {

		AbstractAccount testAccount=new SingleAccount(custA);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.CLOSED);
		testAccount.deposit(100);
		assertEquals(0,testAccount.getBalance());
	}
	
	@Test
	public void tryToithdrawMoneyFromInApplicationAccount() {

		AbstractAccount testAccount=new SingleAccount(custA);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.OPEN);
		testAccount.deposit(100);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.IN_APPLICATION);
		testAccount.withdraw(50);
		assertEquals(100,testAccount.getBalance());
	}
	
	@Test
	public void tryToWithdrawMoneyFromClosedAccount() {

		AbstractAccount testAccount=new SingleAccount (custA);

		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.OPEN);
		testAccount.deposit(100);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.CLOSED);
		testAccount.withdraw(50);
		assertEquals(100,testAccount.getBalance());
	}
	
	
	
	
	@Test
	public void tryToAddMoneyToOpenAccount() {
		AbstractAccount testAccount=new SingleAccount(custA);
		testAccount.changeStatus(AbstractAccount.AccountStatusEnum.OPEN);
		testAccount.deposit(100);
		assertEquals(100,testAccount.getBalance());
	}
	
	@Test
	public void withdrawMoneyFromOpenAccount() {
		AbstractAccount act = new SingleAccount(custA);
		act.approveAccount();
		act.deposit(200);
		act.withdraw(100);
		assertEquals(100,act.getBalance());
	}
	
	@Test
	public void withdrawTooMuchMoneyFromOpenAccount() {
		AbstractAccount act = new SingleAccount(custA);
		act.approveAccount();
		act.withdraw(100);
		assertEquals(0,act.getBalance());
	}
	
	@Test
	public void withdrawNegitiveAmountFromOpenAccount() {
		AbstractAccount act = new SingleAccount(custA);
		act.approveAccount();
		act.withdraw(-100);
		assertEquals(0,act.getBalance());
	}
	
	@Test
	public void vaildFundsTransferTest() {
		AbstractAccount actA = new SingleAccount(custA);
		actA.approveAccount();
		actA.deposit(200);
		AbstractAccount actB = new SingleAccount(custA);
		actB.approveAccount();
		actA.transferMoneyToAccount(actB,100);

		assertEquals(100,actB.getBalance());
	}
	
	@Test
	public void invalidFundsTransferTest() {
		AbstractAccount actA = new SingleAccount(custA);
		actA.approveAccount();
		actA.deposit(200);
		actA.closeAccount();
		AbstractAccount actB = new SingleAccount(custA);
		actB.approveAccount();
		actA.transferMoneyToAccount(actB,100);

		assertEquals(0,actB.getBalance());
	}
	
	
	
	

}
