
public class ArrayResize implements SimpleList {

	Object[] localarray;
	int size;
	
	ArrayResize(int capacity) {
		localarray = new Object[capacity];
	}

	@Override
	public void add(Object item) {
		
		if(size  < localarray.length ) {
			localarray[size] = item;
			size++;
		} 
		
	}

	@Override
	public int size() {
		return this.size;
	}
	
}
