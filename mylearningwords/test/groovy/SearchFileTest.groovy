

public class SearchFileTest extends GroovyTestCase {
	
	
	void testWordCount() {
		
		def text = new File('resources/fulltext/hobbit.txt').getText()
		
		def counts = SearchFile.getWordCounts('bilbo', text)
		
		assert counts > 100
	}
	
	void testGetWordStatisticAgainstDictionary() {
		
		def text = new File('resources/fulltext/hobbit.txt').getText()
		
		def dic = SearchFile.getDictionary('resources/test/7_chapters.txt')
		
		SearchFile.produceWordsStatistics(dic, text) 
	}
	
	void testWordContext() {
		
		//def text = new File('resources/test/wordcontext.txt').getText().toLowerCase()
		
		def text = '''Chance to come out. The chance never arrived. Until bilbo Baggins was grown up. Being about fifty years old or so.'''
		
		def finder = text =~ /([^.]*bilbo+[.]\s*)/
		
		def counter = 0
		
		(0..<finder.count).each {
			def chunk = finder[it][0]
			counter++
			println chunk
		}
		
		assert counter > 0 
		
	}
	
}
