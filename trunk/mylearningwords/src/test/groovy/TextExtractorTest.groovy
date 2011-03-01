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
	
	@Test
	void testBookAnalysis() {
		
		println 'mystery ' +  mystery(2)
		println 'mystery1 ' +  mystery1(2)
		
	}
	
	def mystery(n) {
		def r = 0
		for(def i = 1; i <= n-1; i++ ) {
			for(def j = i+1; j <= n ; j++) {
				for(def k=1; k <= j; k++) {
					r += 1
				}
			}
		} 
		
		return r
	}
	
	def mystery1(n) {
		def sum = 0;
		for( def i = 0; i < n; i++)
			for( def j = i; j < n; j++)
				sum++;
		
		return sum
	}
	
}

