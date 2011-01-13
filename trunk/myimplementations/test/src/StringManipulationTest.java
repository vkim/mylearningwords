import org.junit.Test;

import junit.framework.TestCase;


public class StringManipulationTest extends TestCase {

	@Test
	public void testReverseString() {
		
		String string = "mystring";
		
		char[] str = string.toCharArray();
		    
		int middle = str.length / 2;
		
		for(int i=0; i <= middle - 1; i++) {
			char buffer = str[i];
			str[i] = str[str.length - 1 - i];
			str[str.length - 1 - i] = buffer;
		}
		
		assertEquals("gnirtsym", new String(str));
	}
	
}
