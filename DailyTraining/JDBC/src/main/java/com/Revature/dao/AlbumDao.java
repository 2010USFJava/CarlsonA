package com.Revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.Revature.beans.Album;

public interface AlbumDao {
	//crud ops
	public List<Album> getAllAlbums() throws SQLException;
	

}
