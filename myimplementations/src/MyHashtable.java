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
			
			int hash = adjustHash(item.hashCode());
			
			table[hash] = item;
			
		}
	}
	
	private int adjustHash(int code) {
		int ret = code < 0 ? (code * -1) : code; 
		ret = ret % loadfactor;
		
		return ret;
	}

	@Override
	public boolean contains(Object i) {
		// TODO Auto-generated method stub
		return false;
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
