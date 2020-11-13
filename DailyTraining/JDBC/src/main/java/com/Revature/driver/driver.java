package com.Revature.driver;

import java.sql.SQLException;

import com.Revature.beans.Pookiemans;
import com.Revature.dao.AlbumDao;
import com.Revature.dao.PookiemansDao;
import com.Revature.daoimple.AlbumDaoImpl;
import com.Revature.daoimple.PookiemanDaoImpl;

public class driver {

	public static void main(String[] args) {
		AlbumDao adi=new AlbumDaoImpl();
		PookiemansDao pkD=new PookiemanDaoImpl();
		
		Pookiemans meoth=new Pookiemans(52,"Meoth");
		
		try {
//			ArrayList<Album> aList=(ArrayList<Album>) adi.getAllAlbums();
//			System.out.println(aList.toString());
//			pkD.catchNewPookimans(meoth);
			Pookiemans p=pkD.retrieveById(66);
			System.out.println(p);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

}
