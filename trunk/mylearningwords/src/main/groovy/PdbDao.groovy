import java.io.FileInputStream;

import java.nio.*;
import java.nio.channels.FileChannel;


class PdbDao {

	public final static int HEADER_OFFSET = 78;
	
	public static PdbDatabase importDb(File file) {
		
		FileInputStream is = new FileInputStream(file);
		
		PdbDatabase db = new PdbDao.PdbDatabase();
		
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
	protected static void readPdbHeader(FileInputStream is, PdbDatabase db) {
		
		FileChannel channel = is.channel;
		
		ByteBuffer buff = ByteBuffer.allocate(HEADER_OFFSET);
		
		channel.read buff;
		
		buff.flip();
		
		int numRecs = buff.getShort(76);

		db.numberOfRecords = numRecs
	}
	
	protected static def readPdbRecordsToDb(FileInputStream is, PdbDatabase db) {
		
		FileChannel channel = is.channel;
		
		def records = []
		
		int recOffset = getRecordOffset(channel, 0);
		int nextRecOffset;
		
		for(int i=1; i <=  db.numberOfRecords; i++) {

			if(i == db.numberOfRecords) {
				nextRecOffset = (int)channel.size();
			} else {
				nextRecOffset = getRecordOffset(channel, i)
			}
			
			ByteBuffer buf = ByteBuffer.allocate(nextRecOffset - recOffset);
			
			println "recOffset: $recOffset, nextRecOffset: $nextRecOffset"
			
			channel.read(buf, recOffset);
			records.add(getWord(buf.array()));
			
			recOffset = nextRecOffset;
		}		
		
		return records;
	}
	
	private static Word getWord(byte[] source) {
		
		String record = new String(source);
		
		String[] recsplit = record.split("\\x00");
		Word word = new Word(recsplit[0], recsplit[1], recsplit[2]);
		
		return word
	}
	
	/**
	 * Returns offset of the Record 
	 * from the beginning of the fileDb
	 * 
	 * @param channel
	 * @param item
	 * @return
	 */
	private static int getRecordOffset(FileChannel channel, int item) {
		
		ByteBuffer recHeader = ByteBuffer.allocate(8);
		
		channel.read(recHeader, HEADER_OFFSET + item*8);
		recHeader.flip();
		return recHeader.getInt();
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

		def records;
		int numberOfRecords = 0;
		
		/**
		 * closed class
		 */
		private PdbDatabase() {
		}		
		
		public def getRecords() {
			return records;
		}
		
		public void setRecords(records) {
			this.records = records;
		}
		
		
	}
	
	
}

