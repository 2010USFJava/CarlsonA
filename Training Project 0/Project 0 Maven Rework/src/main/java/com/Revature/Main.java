package com.Revature;

import com.Revature.Meta.Bank;
import com.Revature.Meta.FileHandler;
import com.Revature.Users.LoginInfo;

public class Main {
	

	public static void main(String[] args) {
		FileHandler.loadAll();
		FileHandler.saveAll();
//		
		Bank bank = new Bank();
		
		
		bank.startPage();
		FileHandler.saveAll();

	}

}
