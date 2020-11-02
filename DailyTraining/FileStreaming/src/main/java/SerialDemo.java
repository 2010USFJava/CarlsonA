
public class SerialDemo {
	public static void main(String[]args) {
//		System.out.println("Hello world");
		IO io = new IO();
		String a = "banana";

		Person p1= new Person("Carl",55,400);
		Person p2= new Person("Billy Joel",55,400);
		IOWithCollections.personList.add(p1);
		IOWithCollections.personList.add(p2);
		
		IOWithCollections.writePersonFile();
		IOWithCollections.readPersonFile();
		System.out.println(IOWithCollections.personList.toString());
	}
	
}
