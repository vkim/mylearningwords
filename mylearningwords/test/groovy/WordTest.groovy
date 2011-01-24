import org.junit.*

import static org.junit.Assert.*;


class WordTest {

	@Test
	void testArrayToWordsArray() {
		
		assertEquals([new Word('i'), new Word('love'), new Word('you')],   Word.toWordsArray(['i', 'love','you']))
	}
	
	@Test
	void testTrimWord() {
		
		assertEquals('word', new Word('word').trim())
		assertEquals('word1', new Word(' word1 ').trim())
		assertEquals('word2', new Word('	word2	').trim())
		
		assertEquals('word3', new Word('word3	(word)').trim())
		assertEquals('word4', new Word('word4 (word)').trim())
		assertEquals('word4', new Word('word4 (word, asduyfoaiud)').trim())
	}
	
	@Test
	void testTrimWordRussian() {
		
		assertEquals('слово', new Word('слово').trim())
		assertEquals('слово1', new Word(' слово1 ').trim())
		assertEquals('слово2', new Word('	слово2	').trim())
		
		assertEquals('слово3', new Word('слово3	(слово)').trim())
		assertEquals('слово4', new Word('слово4 (слово, adf . йцук)').trim())
	}
	
}
