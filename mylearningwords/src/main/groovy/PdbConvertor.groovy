
class PdbConvertor {

	File pdbFile;
	def pHeader = new byte[78]; 

	def readHeader() {
		
		InputStream is = new FileInputStream(pdbFile);
		
		long length = pdbFile.length();
		
		is.read(pHeader);
	}
	
	
}

