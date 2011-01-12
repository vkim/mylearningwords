import java.util.BitSet;

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
	
	@Test
	public void testStringUniqueIntArrayMap() {
		
//		String sequence = "qwerqwer" ; 
		String sequence = "qwertyuiop[]asdfghjkl;'zxcvbnm,.";
		
		int[] map = new int[255];
		
		boolean unique = true;
		
		for(int i =0; i < sequence.length(); i++) {
			
			if(map[sequence.charAt(i)] == 0) {
				map[sequence.charAt(i)] = 1;
			} 
			else {
				unique = false;
				break;
			}
		}
		
		assertTrue(unique);
	}
	
	@Test
	public void testStringUniqueBitMapArray() {
		assertTrue(areAllCharactersUniqueBitMap("qwertyuiop[]asdfghjkl;'zxcvbnm,."));
		assertFalse(areAllCharactersUniqueBitMap("qwerqwer"));
	}
	
	private boolean areAllCharactersUniqueBitMap(String sequence) {
		
		BitSet map = new BitSet(255);
		boolean unique = true;
		
		for(int i = 0; i < sequence.length(); i++) {
			
			if(!map.get(sequence.charAt(i))) {
				map.set(sequence.charAt(i));
			}
			else {
				unique = false;
				break;
			}
		}
		
		return unique;
	}
	
}
