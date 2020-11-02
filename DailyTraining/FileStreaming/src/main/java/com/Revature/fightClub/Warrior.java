package com.Revature.fightClub;

public class Warrior {
	private String name;
	private int hp;
	private int attackPower;
	
	public Warrior() {};
	
	public Warrior(String name, int hp, int attackPower) {
		super();
		this.name = name;
		this.hp = hp;
		this.attackPower = attackPower;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAttackPower() {
		return attackPower;
	}
	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	
	@Override
	public String toString() {
		return "[Name: "+name+" Hp: "+hp+" Attack Power: "+attackPower+"]";
	}
	
	
}
