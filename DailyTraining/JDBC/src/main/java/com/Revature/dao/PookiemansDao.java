package com.Revature.dao;

import java.sql.SQLException;

import com.Revature.beans.Pookiemans;

//import com.Revature.daoimple.Pookieman;


public interface PookiemansDao {

	public void catchNewPookimans(Pookiemans p) throws SQLException;
	

	public Pookiemans retrieveById( int id) throws SQLException;
		
	

}
