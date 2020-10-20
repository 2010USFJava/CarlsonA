package com.Carlson;

public class Demon {
	
	//variables
	private String name;
	private int age;
	private Toy hauntedToy=new Toy();
	private int id;
	
	//global
	private static int nextId=1;
	
	
	//constructors
	public Demon() {
		updateId();
	}
	public Demon(String name, int age, Toy hauntedToy) {
		updateId();
		this.name=name;
		this.age=age;
		this.hauntedToy=hauntedToy;
		
	}
	
	private void updateId() {
		id=nextId;
		nextId++;
	}
	
	//getters and setters
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public Toy getHauntedToy() {
		return hauntedToy;
	}
	
	//to string
	@Override
	public String toString() {
		return "Demon #"+id+"\nName: "+name+"\nAge: "+age+"\nHauntedToy: "+hauntedToy.getName()+"\n";
		
	}

}
