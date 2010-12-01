import java.awt.Container;
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
	}
	
	public void testAddingItemsWithTheSameHashValue() {
		
		table.add(new Person(4));
		table.add(new Person(3));
		
		assertEquals(2, table.size());
	}
	
	public void testContainItemWithDifferentHashes() {
		
		for(int i = 4; i < MyHashtable.loadfactor + 4; i++ ) {
			table.add(String.valueOf(i));
		}
		
		assertEquals(MyHashtable.loadfactor, table.size());
		assertTrue(table.contains(String.valueOf(4)));  
	}
	
	public void testContainsItemWithTheSameHash() {
		
		for(int i = 0; i < MyHashtable.loadfactor; i++ ) {
			table.add(new Person(i));
		}
		
		assertEquals(MyHashtable.loadfactor, table.size());
		assertFalse(table.contains(new Person(1000)));
	}

	class Person {
		
		public int i;
		
		Person(int i) {
			this.i = i;
		}
		
		public boolean equals(Object o) {
			return o instanceof Person && ((Person)o).i == this.i;
		}
		
		public int hashCode() {
			return 5;
		}
		
		public String toString() {
			return "hash: " + hashCode() + ", value: " + i;
		}
	}
	
}
