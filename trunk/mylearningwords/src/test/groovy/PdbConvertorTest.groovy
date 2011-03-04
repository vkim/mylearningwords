import static org.junit.Assert.*;

import org.junit.*;

class PdbConvertorTest {

	
	
	@Test
	public void readHeader() {
		
		def pdbFile = new File('resources/test/7_chapters.pdb')
		
		def header = new PdbConvertor(pdbFile: pdbFile).readHeader()
		
		assertNotNull header
	}
	
}
