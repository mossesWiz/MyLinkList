import java.awt.List;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) 
	{
		MyLinkList<Integer> newList = new MyLinkList<>();
		
		newList.add(1);
		newList.add(2);
		newList.add(3);
		newList.add(4);	
		newList.add(5);
				
		LinkedList<Integer> newList2 = new LinkedList<Integer>();
		newList2.add(2);
		newList2.add(9);
		newList2.add(6);
		newList2.add(7);
		newList2.add(3);
		
		System.out.println("first list : "+newList);
		System.out.println("second list : "+newList2);
		
		System.out.println("Intersection : "+newList.intersection(newList2));

	}

}
