package com.Revature.Meta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.Revature.Users.LoginInfo;

public class FileHandler {
	public static final String loginFile = "loginFile.txt";
	//write method
	public static void writeLoginFile( Map<String,LoginInfo> loginMap) {
		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(loginFile));
			objectOut.writeObject(loginMap);
			objectOut.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//read methiod
	public static void readLoginFile() {
		try {
			ObjectInputStream objIn=new ObjectInputStream(new FileInputStream(loginFile));
			
			LoginInfo.setLoginMap((Map<String,LoginInfo>)objIn.readObject());
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
