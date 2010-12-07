import org.junit.Test;

import junit.framework.TestCase;

public class ArrayResizeTest extends TestCase {
	
	
	@Test
	public void testSizeDoubled() {
		
		int capacity = 5;
		ArrayResize array = new ArrayResize(capacity); 
		
		for(int i = 0; i<capacity+1; i++) {
			array.add(new Object());
		}
		
		assertEquals(10, array.localarray.length);
	}
	
}
