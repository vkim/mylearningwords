import java.util.HashSet;

import junit.framework.TestCase;

import org.junit.*;

public class MhashTableTest extends TestCase { 
	
	private IHashTable table;

	@Before
	protected void setUp() {
		table = new MyHashtable();
	}
	
	@Test
	public void testAddItemsToTable() {
		
		table.add("i");
		table.add("g");
		table.add("e");
		
		assertEquals(3, table.size()); 
	}
	
	public void testAddingTheSameValueDoesnotChangeTheSize() {
		
		table.add("i");
		table.add("i");
		
		assertEquals(1, table.size());
	}
	
	public void testAddingMoreElementsThanLoadFactor() {
		
		for(int i = 0; i < MyHashtable.loadfactor + 2; i ++ ) {
			table.add(String.valueOf(i));
		}
		
		assertEquals(MyHashtable.loadfactor, table.size());
		
		System.out.println(table);
	}
	
	public void testAddingItemsWithTheSameHashValue() {
		
		HashSet tabs = new HashSet();
		
		tabs.add(new Person(4));
		tabs.add(new Person(3));
		
		assertEquals(1, tabs.size());
		
		System.out.println(new Person(4).hashCode());
		System.out.println(new Person(3).hashCode());
	}

	class Person {
		
		int i;
		
		Person(int i) {
			this.i = i;
		}
		
		public boolean equals(Object o) {
			return false;
		}
		
		public int hashCode() {
			
			return 5;
		}
	}
	
}
