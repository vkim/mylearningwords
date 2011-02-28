import java.nio.*


class ConvertorPdbToTxt {

	


	public static void main(String[] args) {
		
		def channel = new FileInputStream('new_inter.pdb').channel
 				
		def header = ByteBuffer.allocate(76)
		channel.read(header)
					
		ByteBuffer bb = ByteBuffer.allocate(2);
		//bb.order(ByteOrder.LITTLE_ENDIAN);

		channel.read(bb)
		
		short shortVal = bb.getShort(0);
		println shortVal

		
		
		def recHeader = new Byte[8]
		//channel.read(recHeader)
		
		channel.close()		
		
	}
	
}

