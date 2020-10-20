package com.Carlson;

public class Toy {
	
	//variables
	//local
	private String name;
	private float inches;
	private String color;
	private int id;
	
	//global
	private static int nextId=1;
	
	//constructor
	public Toy() {		
		updateId();
	}
	
	public Toy(String name, float inches, String color) {
		updateId();
		this.name=name;
		this.inches=inches;
		this.color=color;
		
	}
	
	private void updateId() {
		id=nextId;
		nextId++;
	}
	
	//getters and setters
	public String getName() {
		return name;
		
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public float getInches() {
		return inches;
	}
	
	public void setInches(float inches) {
		this.inches=inches;
	}

	public String getColor() {
		return color;
		
	}
	
	
	public void setColor(String color) {
		this.color=color;
		
	}
	
	@Override
	public String toString() {
		return "Toy #"+id+"\nName: "+name+"\nInches: "+inches+"\nColor: "+color+"\n";
		
	}
	
	

}
