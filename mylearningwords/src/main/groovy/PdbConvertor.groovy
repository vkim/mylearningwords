import java.io.FileInputStream;

import java.nio.*;
import java.nio.channels.FileChannel;

import java.io.InputStream;

class PdbConvertor {

	public static PdbDatabase importDb(File file) {
		
		FileInputStream is = new FileInputStream(file);
		
		PdbDatabase db = new PdbConvertor.PdbDatabase();
		
		readPdbHeader(is, db)
		
		is.close();
		
		return db;
	}
	
	/**
	 * Read from the file header information
	 * 
	 * @param is
	 * @param db
	 * @return
	 */
	protected static def readPdbHeader(FileInputStream is, PdbDatabase db) {
		
		is.read(db.pHeader);
		
		return db;
	}
	
	protected static def readPdbRecordsToDb(FileInputStream is, PdbDatabase db) {
		
		FileChannel channel = is.channel;
		
		ByteBuffer recOffset = ByteBuffer.allocate(4);
		
		println 'next available bytes: ' + is.available()
		println 'current position: ' + channel.position()
		
		if(channel.read(recOffset, PdbDatabase.HEADER_OFFSET) != 4) 
			println "Error in reading file"

		int filesize =  (int)channel.size();
		recOffset.flip();
		int offset = recOffset.getInt();
		println 'filesize: ' + filesize + ', offset: ' + offset
		ByteBuffer buf = ByteBuffer.allocate(filesize - offset);
		
		channel.read(buf, offset);
		println (new String(buf.array()))  
		
		int nextRecOffset;
		
		return db;
	}

	/**
	 * Convert first 2 or 4 bytes
	 * 
	 * @param b
	 * @return
	 */
	public static final int byteArrayToInt(b) {
		
		if(!b) {
			return 0;
		}
		
		if(isTwoBytesArray(b)) {
			return (b[1] & 0xFF) + ((b[0] & 0xFF) <<8);
		}
		
		if(b.size() >=4) {
			return (b[3] & 0xFF) + ((b[2] & 0xFF) <<8) + ((b[1] & 0xFF) <<16) + ((b[0] & 0xFF) <<24);
		}
		
		return 0;
	}
	
	public static final boolean isTwoBytesArray(b) {
		
		if(b) {
			if(b instanceof List && b.size() == 2) {
				return true
			} 
			else if(b instanceof Object[] && b.length == 2) {
				return true
			}
			else if(b.getClass().isArray() && b.length == 2) {
				return true
			}
		}
		
		return false
	}
	
	class PdbDatabase {

		public final static int HEADER_OFFSET = 78;
		
		byte[] pHeader = new byte[HEADER_OFFSET];
		
		def records;
		
		/**
		 * closed class
		 */
		private PdbDatabase() {
		}		
		
		public int getNumberOfRecords() {
			if(pHeader) {
				return PdbConvertor.byteArrayToInt(pHeader[76..77])
			}
			
			return 0
		}
		
		public def getRecords() {
			return records;
		}
		
		public void setRecords(records) {
			this.records = records;
		}
		
		
	}
	
	
}

