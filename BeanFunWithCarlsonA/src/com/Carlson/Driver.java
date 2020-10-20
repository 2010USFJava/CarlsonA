package com.Carlson;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Toy toy = new Toy();
		Toy yoyo=new Toy("Yoyo",4.2f,"Green");
		
		Demon demon = new Demon();
		Demon carlDemon = new Demon("Carl", 4922,yoyo);
		Demon spinyDevil = new Demon("Spiny Devil", 14,toy);

		System.out.println(toy);
		System.out.println(yoyo);
		System.out.println(demon);
		System.out.println(carlDemon);
		System.out.println(spinyDevil);

	}

}
