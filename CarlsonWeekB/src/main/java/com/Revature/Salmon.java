package com.Revature;
import java.io.Serializable;

//Pulling salmon facts from https://en.wikipedia.org/wiki/Salmon
public class Salmon implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6545827706694391183L;
	private String commonName;
	private String scientificName;
	private int maxLengthCm;
	private double maxWeightKg;
	
	
	//constructor
	{Aquarium.salmonList.add(this);
	}
	
	public Salmon() {
		commonName="Atlantic salmon";
		scientificName="Salmo salar";
		maxLengthCm=150;
		maxWeightKg=46.8;



		init();
	}
	
	public Salmon(String commonName, String scientificName, int maxLengthCm, double maxWeight) {
		this.commonName = commonName;
		this.scientificName = scientificName;
		this.maxLengthCm = maxLengthCm;
		this.maxWeightKg = maxWeight;
		
		init();

	}
	
	private void init() {
		FileHandler.writeSalmonFile(Aquarium.salmonList);
		LogThis.LogIt("info", this.toString());
	}
	
	//getters and setters
	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getScientificName() {
		return scientificName;
	}
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	public int getMaxLengthCm() {
		return maxLengthCm;
	}
	public void setMaxLengthCm(int maxLengthCm) {
		this.maxLengthCm = maxLengthCm;
	}
	public double getMaxWeight() {
		return maxWeightKg;
	}
	public void setMaxWeight(double maxWeight) {
		this.maxWeightKg = maxWeight;
	}
	
	@Override
	public String toString() {
		return "Common Name:"+commonName+"\tScientific Name:"+scientificName+"\tMaximum Length:"+maxLengthCm+"cm\t Max Weight:"+maxWeightKg+"kg";
	}
	

}
