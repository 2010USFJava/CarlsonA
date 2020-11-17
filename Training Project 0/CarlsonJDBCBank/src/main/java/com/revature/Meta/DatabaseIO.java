package com.revature.Meta;

import java.sql.SQLException;

import com.revature.Meta.LogThis.LevelEnum;
import com.revature.database.daoimple.CustAcctRelDaoImple;

public class DatabaseIO {
	public static void loadDatabaseInfo() throws SQLException {
		System.out.println("Loading information from database...");
		LogThis.logIt(LevelEnum.INFO, "Loaded information from database");
		CustAcctRelDaoImple.loadDatabaseInfo();
	}

}
