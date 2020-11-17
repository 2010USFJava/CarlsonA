package com.revature.Meta;

import java.sql.SQLException;

import com.revature.database.daoimple.CustAcctRelDaoImple;

public class DatabaseIO {
	public static void loadDatabaseInfo() throws SQLException {
		CustAcctRelDaoImple.loadDatabaseInfo();
	}

}
