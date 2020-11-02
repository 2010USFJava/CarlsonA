import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7204759613325306183L;
	private String name;
	private int age;
	private int weight;
	static String homePlanet="earth";
	
	
	static {System.out.println("I am in a Static code block");}
	
	{System.out.println("I am in an instance code block");}
	
	public static String getHomePlanet() {
		return homePlanet;
	}
	
	public static void setHomePlanet(String homePlanet) {
		Person.homePlanet=homePlanet;
	}
	
	
	public Person() {}
	public Person(String name,int age,int weight) {
		this.name=name;
		this.age=age;
		this.weight=weight;
	}
	
	@Override
	public String toString() {
		return "Name: "+name+" Age: "+age+" Weight: "+weight;
	}

}
