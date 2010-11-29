import junit.framework.TestCase;

import org.junit.*;

public class MhashTableTest extends TestCase {
	
	private IHashTable table;

	@Before
	protected void setUp() {
		table = new MyHashtable();
	}
	
	@Test
	public void testAddItemToTable() {
		
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
	
}
