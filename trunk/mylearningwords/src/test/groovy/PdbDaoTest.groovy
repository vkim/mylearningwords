import static org.junit.Assert.*;

import org.junit.*;

class PdbDaoTest {

	@Test
	public void bytesToInt() {
		
		assertEquals(1, PdbDao.byteArrayToInt([0,1]));
		assertEquals(256, PdbDao.byteArrayToInt([1,0]));
		
		assertEquals(1, PdbDao.byteArrayToInt([0,0,0,1]));
		assertEquals(1, PdbDao.byteArrayToInt([0,0,0,1,5]));
		assertEquals(256, PdbDao.byteArrayToInt([0,0,1,0]));
		assertEquals(65536, PdbDao.byteArrayToInt([0,1,0,0]));
		assertEquals(16777216, PdbDao.byteArrayToInt([1,0,0,0]));
	}
	
	@Test
	public void isTwoBytesArray() {
		assertTrue(PdbDao.isTwoBytesArray([0,1]));
		assertTrue(PdbDao.isTwoBytesArray(new byte[2]));
		
		assertFalse(PdbDao.isTwoBytesArray([0,1,1,1]));
		assertFalse(PdbDao.isTwoBytesArray(new byte[4]));

		//wrong format
		assertFalse(PdbDao.isTwoBytesArray([1]));
		assertFalse(PdbDao.isTwoBytesArray(new byte[1]));
		
		assertFalse(PdbDao.isTwoBytesArray([]));
		assertFalse(PdbDao.isTwoBytesArray(new byte[0]));
		
		assertFalse(PdbDao.isTwoBytesArray([0,0,0,0,0]));
		assertFalse(PdbDao.isTwoBytesArray(new byte[5]));
	}
	
	@Test
	public void readHeader() {
		
		PdbDao.PdbDatabase db = new PdbDao.PdbDatabase();
		db.numberOfRecords = 45;
		
		PdbDao.readPdbHeader(
				new FileInputStream(new File('resources/test/oneword.pdb')),
				db
			)
		
		assertNotNull db
		assertEquals(1, db.numberOfRecords)
	}
	
	@Test
	public void integrationImportDb() {
		
		PdbDao.PdbDatabase db = PdbDao.importDb(new File('resources/test/oneword.pdb'));
		
		assertNotNull db
		assertEquals(1, db.getNumberOfRecords())
	}
	
	
	@Test
	public void readPdbOneRecordToDb() {
		
		def db = new PdbDao.PdbDatabase()
		db.numberOfRecords = 1
		def reclist = PdbDao.readPdbRecordsToDb(
					new FileInputStream(new File('resources/test/oneword.pdb')),
					db
				)
		
		assertNotNull reclist
		assertEquals(1, reclist.size())
	}
	
	@Test
	public void readPdbManyRecordToDb() {
		
		def db = new PdbDao.PdbDatabase();
		db.numberOfRecords = 45
		def reclist = PdbDao.readPdbRecordsToDb(
					new FileInputStream(new File('resources/test/prolog.pdb')),
					db)
		
		assertNotNull reclist
		assertEquals(45,reclist.size())
		
		reclist.each { println "${it.value} ${it.transcription} ${it.translation}  " }
	}
	
}
