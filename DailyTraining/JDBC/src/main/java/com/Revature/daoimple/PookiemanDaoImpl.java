package com.Revature.daoimple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Revature.beans.Pookiemans;
import com.Revature.dao.PookiemansDao;
import com.Revature.util.ConnFactory;

public class PookiemanDaoImpl implements PookiemansDao{

    public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void catchNewPookimans(Pookiemans p) throws SQLException {
		Connection conn = cf.getConnection();

        //PreparedStatement way
        String sql= "insert into pookiemans values(?,?)";
        PreparedStatement ps= conn.prepareStatement(sql);
        ps.setInt(1, p.getpId());
        ps.setString(2, p.getpName());
        ps.executeUpdate();
	}

	@Override
	public Pookiemans retrieveById(int id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql="select * from pookiemans where pid=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Pookiemans hannah=null;
		
		
		while(rs.next()) {
			hannah=new Pookiemans(rs.getInt(1),rs.getString(2));
		}
		return hannah;
	}

}
