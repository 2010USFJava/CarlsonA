package com.revature.database.factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {
	
//	singleton factory
	//private static indtance of itself
	private static ConnFactory cf;
	
	//private no args construcor
	private ConnFactory() {
		super();
		}
	
	public static synchronized ConnFactory getInstance() {
		if(cf==null) {
			cf=new ConnFactory();
		}
		return cf;
	}

	
	//methods
	public Connection getConnection() {
		Connection conn=null;
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("database.properties"));
			conn=DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
