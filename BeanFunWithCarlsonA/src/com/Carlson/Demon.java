package com.Carlson;

public class Demon {
	
	//variables
	private String name;
	private int age;
	private Toy hauntedToy=new Toy();
	
	
	//constructors
	public Demon() {}
	public Demon(String name, int age, Toy hauntedToy) {
		this.name=name;
		this.age=age;
		this.hauntedToy=hauntedToy;
		
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
		return "Demon\nName: "+name+"\nAge: "+age+"\nHauntedToy: "+hauntedToy.getName()+"\n";
		
	}

}
