import static org.junit.Assert.*;

import org.junit.*;

class PdbConvertorTest {

	@Test
	public void bytesToInt() {
		
		assertEquals(1, PdbConvertor.byteArrayToInt([0,1]));
		assertEquals(256, PdbConvertor.byteArrayToInt([1,0]));
		
		assertEquals(1, PdbConvertor.byteArrayToInt([0,0,0,1]));
		assertEquals(1, PdbConvertor.byteArrayToInt([0,0,0,1,5]));
		assertEquals(256, PdbConvertor.byteArrayToInt([0,0,1,0]));
		assertEquals(65536, PdbConvertor.byteArrayToInt([0,1,0,0]));
		assertEquals(16777216, PdbConvertor.byteArrayToInt([1,0,0,0]));
	}
	
	@Test
	public void isTwoBytesArray() {
		assertTrue(PdbConvertor.isTwoBytesArray([0,1]));
		assertTrue(PdbConvertor.isTwoBytesArray(new byte[2]));
		
		assertFalse(PdbConvertor.isTwoBytesArray([0,1,1,1]));
		assertFalse(PdbConvertor.isTwoBytesArray(new byte[4]));

		//wrong format
		assertFalse(PdbConvertor.isTwoBytesArray([1]));
		assertFalse(PdbConvertor.isTwoBytesArray(new byte[1]));
		
		assertFalse(PdbConvertor.isTwoBytesArray([]));
		assertFalse(PdbConvertor.isTwoBytesArray(new byte[0]));
		
		assertFalse(PdbConvertor.isTwoBytesArray([0,0,0,0,0]));
		assertFalse(PdbConvertor.isTwoBytesArray(new byte[5]));
	}
	
	@Test
	public void readHeader() {
		
		PdbConvertor.PdbDatabase db = PdbConvertor.readPdbHeader(
			new FileInputStream(new File('resources/test/oneword.pdb')),
			 new PdbConvertor.PdbDatabase())
		
		assertNotNull db
		
		assertEquals(1, PdbConvertor.byteArrayToInt(db.pHeader[76..77]))
	}
	
	@Test
	public void integrationImportDb() {
		
		PdbConvertor.PdbDatabase db = PdbConvertor.importDb(new File('resources/test/oneword.pdb'));
		
		assertNotNull db
		assertEquals(1, db.getNumberOfRecords())
	}
	
	
	@Test
	public void readPdbRecordsToDb() {
		
		PdbConvertor.PdbDatabase db = PdbConvertor.readPdbRecordsToDb(
					new FileInputStream(new File('resources/test/oneword.pdb')),
					new PdbConvertor.PdbDatabase()
				)
		
		assertNotNull db
		assertTrue(db.records.size() == 1)
	}
	
}
