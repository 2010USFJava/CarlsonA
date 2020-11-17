package com.revature.driver;

import java.sql.SQLException;

import com.revature.Meta.DatabaseIO;
import com.revature.ui.BankUI;

public class Main {

	public static void main(String[] args) throws SQLException {
//
//		CustAcctRelDaoImple custAct = new CustAcctRelDaoImple();
//		ArrayList<Employee> emps =custAct.getEmployeeList();
//		System.out.println(emps);

		BankUI bank=new BankUI();
		DatabaseIO.loadDatabaseInfo();
//		bank.addTestData();
		bank.startPage();
		System.exit(0);
	}

}
