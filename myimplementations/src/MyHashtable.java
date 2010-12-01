import java.util.Arrays;


public class MyHashtable implements IHashTable {

	private Object[] table;
	
	public static int loadfactor = 10;
	
	public MyHashtable() {
		table = new Object[loadfactor];
	}
	
	public MyHashtable(int loadf) {
		table = new Object[loadf];
		loadfactor = loadf;
	}
	
	@Override
	public void add(Object item) {
		
		if(item != null) {
			
			int index = firstStep(item.hashCode());
			
			index = findNextAvailableSlot(index, item);
			table[index] = item;
		}
	}
	
	private int findNextAvailableSlot(int index, Object item) {
		
		for(int i = 0; i < loadfactor; i++) {
			
			int k = (i + index) % loadfactor; 
			if((table[k] != null && item.equals(table[k]))      
			         || table[k] == null) {
				
				return k;
			}
		}
		
		return index;
	}

	private int firstStep(int code) {
		int ret = code < 0 ? (code * -1) : code; 
		ret = ret % loadfactor;
		
		return ret;
	}

	@Override
	public boolean contains(Object i) {
		
		int index = firstIndex(i);
		
		int goodslot = findNextAvailableSlot(index, i);
		
		if(table[goodslot] != null && i.equals(table[goodslot])) {
			return true;
		}
		
		return false;
	}

	private int firstIndex(Object i) {
		
		return i.hashCode() % loadfactor;
	}

	@Override
	public int size() {
		
		int count = 0;
		
		for(int i =0 ; i < table.length; i++) {
			if(table[i] != null) {
				count++;
			}
		}
		
		return count;
	}
	
	public String toString() {
		
		return Arrays.toString(table);
	}
	

}
