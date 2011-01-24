import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*

public class GooglePronunciationTest {

	public String buildDir = "build/test";
	
	@Before
	void setUp() {
		
		//prepare temp directory
		new File(buildDir).deleteDir()
		new File(buildDir).mkdirs()
	}
	
	@Test
	void testWordToSpeachEnglish() {
		
		String wavFile = buildDir + "/word.wav"
		
		GooglePronunciation.wordToSpeach(new Word('word'), wavFile)
		
		assertTrue(new File(wavFile).exists())		
	}
	
	@Test
	void testWordToSoundRussian() {
		
		String wavFile = buildDir + "привет.wav"
		
		GooglePronunciation.wordToSpeach(new Word('привет', 'ru'), wavFile)
		
		assertTrue(new File(wavFile).exists())
	}
	
	@Test
	void testWordsToSpeachFiles() {
		
		def progressBar = false
		
		def words = Word.toWordsArray(['I', 'love', 'you', 'so', 'much'])
		words.add(new Word('любовь', 'ru'))
		def wavDir = buildDir + '/wordsList'
		
		GooglePronunciation.textToSpeach(words, wavDir, progressBar)
		
		assertEquals(7, new File(wavDir).list()?.length)
	}
	
}
