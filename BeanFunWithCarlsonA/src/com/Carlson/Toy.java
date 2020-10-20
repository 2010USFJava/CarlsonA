package com.Carlson;

public class Toy {
	//variables
	private String name;
	private float inches;
	private String color;
	
	//constructor
	public Toy() {		
	}
	
	public Toy(String name, float inches, String color) {
		this.name=name;
		this.inches=inches;
		this.color=color;
		
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
		return "Toy\nName: "+name+"\nInches: "+inches+"\nColor: "+color+"\n";
		
	}
	
	

}
