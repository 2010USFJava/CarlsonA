package com.Revature.beans;

public class Pookiemans {
	
	private int id;
	private String name;
	
	
	
	public Pookiemans() {
		super();
	}
	public Pookiemans(int pId, String pName) {
		super();
		this.id = pId;
		this.name = pName;
	}
	public int getpId() {
		return id;
	}
	public void setpId(int pId) {
		this.id = pId;
	}
	public String getpName() {
		return name;
	}
	public void setpName(String pName) {
		this.name = pName;
	}
	@Override
	public String toString() {
		return "Pookiemans [pId=" + id + ", pName=" + name + "]";
	}
	
	
	

}
