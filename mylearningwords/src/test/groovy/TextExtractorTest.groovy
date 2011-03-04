import static org.junit.Assert.*;

import org.junit.*;

class TextExtractorTest {
	
	/*
	 * TODO to be implemented
	 */
	void testExtractWords() {
		
		def extractor = new TextExtractor()
		
		def list = extractor.createExtractedList(sample1)

		assert list
		assert list.size() > 20 && list.size() < 100
	}
	
	def sample1 = '''
	Introduction 
For Hush Puppies — the classic American 
brushed-suede shoes with the lightweight crepe 
sole —- the Tipping Point came somewhere 
between late 1994 and early 1995. The brand 
had been all but dead until that point. Sales were down to 
	
	'''	
	
}

