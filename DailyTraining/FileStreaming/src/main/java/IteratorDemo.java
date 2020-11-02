import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorDemo {
	
	public static void main(String[]hamSandwich) {
		List<String> a1=new ArrayList<String>();
		a1.add("C");
		a1.add("D");
		a1.add("F");
		a1.add("S");
		a1.add("Z");
		
		System.out.println("Original Contents of a1:");
		Iterator<String> itr=a1.iterator();
		
		while(itr.hasNext()) {
			String element=itr.next();
			System.out.print(element+" ");
		}
		//Modify objects being iterated
		ListIterator<String>litr=a1.listIterator();
		while(litr.hasNext()) {
			String bettyWhite=litr.next();
			litr.set(bettyWhite+"+");
		}
		
		System.out.println("\nModified Contents of a1:");
		itr=a1.iterator();	
		while(itr.hasNext()) {
			String element=itr.next();
			System.out.print(element+" ");
		}
		
		System.out.println("\nModified List backwards");
		while(litr.hasPrevious()) {
			String element = litr.previous();
			System.out.print(element+" ");
		}
		
	}

}
