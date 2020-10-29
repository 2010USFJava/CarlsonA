import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class IOWithCollections {
	private static final String personFile="person.txt";
	public static List<Person> personList = new ArrayList<Person>();
	
	
	//write arraylist to file
	 public static void writePersonFile() {
		 try {
			 ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(personFile));
			 objOut.writeObject(personList);
			 objOut.close();
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 
	 }


	 public static void readPersonFile() {
		 try {
			 ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(personFile));
			 personList=(ArrayList<Person>)objectIn.readObject();
			 objectIn.close();
			 
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	 }
}
