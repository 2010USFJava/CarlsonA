package com.Revature;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
	public static final String salmonFile="salmonList.txt";
	
	//write method
	public static void writeSalmonFile(List<Salmon> sList) {
		try {
			ObjectOutputStream objectOut=new ObjectOutputStream(new FileOutputStream(salmonFile));
			objectOut.writeObject(sList);
			objectOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//read method
	@SuppressWarnings("unchecked")
	public static void readSalmonFile() {
		try {
			ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(salmonFile));
			Aquarium.salmonList=(ArrayList<Salmon>)objIn.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	

}
