
public class Spider {
	private String name;
	private int size;
	private int legs;
	private int eyes;
	private int fliesEaten;
	private boolean isTerrifying;
	
	//id info
	private int id;
	private static int nextId=0;
	
	public Spider(String name, int size, int legs, int eyes, int fliesEaten, boolean isTerrifying) {
		this.name=name;
		this.size=size;
		this.legs=legs;
		this.eyes=eyes;
		this.fliesEaten=fliesEaten;
		this.isTerrifying=isTerrifying;
		
	}
	
	public Spider(String name, int size, int eyes, int fliesEaten, boolean isTerrifying) {
		this(name,size,8,eyes,fliesEaten,isTerrifying);
		id=++nextId;
	}
	
	public Spider(String name, int size, int fliesEaten, boolean isTerrifying) {
		this(name,size,8,fliesEaten,isTerrifying);
		
	}
	
	public Spider(String name, int size, boolean isTerrifying) {
		this(name,size,0,isTerrifying);
	}
	
	public Spider(String name, boolean isTerrifying) {
		this(name,5,isTerrifying);
		
	}
	
	public Spider(String name) {
		this(name,true);
	}
	public Spider() {
		this("Unnamed");
	}
	
	@Override
	public String toString() {
		return "Spider #"+id
				+ "\nName: "+name+"\nSize: "+size+"\nLegs: "+legs+"\nEyes: "+eyes+"\nFlies Eaten: "+fliesEaten+
				"\nIs Terrifying: "+ isTerrifying+"\n";
	}

}
