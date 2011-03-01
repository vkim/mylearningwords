import java.util.List;

import org.junit.Before;
import org.junit.After;
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
	
	@After
	void after() {
		SearchFile.metaClass = null
	}
	
	@Test
	void testWordToSpeachEnglish() {
		
		String wavFile = buildDir + "/word.wav"
		
		GooglePronunciation.wordToSpeach(new Word('word'), wavFile)
		
		assertTrue(new File(wavFile).exists())		
	}
	
	@Test
	void testWordToSoundRussian() {
		
		String wavFile = buildDir + "яблочко.wav"
		
		GooglePronunciation.wordToSpeach(new Word('яблочко', 'ru'), wavFile)
		
		assertTrue(new File(wavFile).exists())
	}
	
	@Test
	void testWordsToSpeachFiles() {
		
		def progressBar = false
		
		def words = Word.toWordsArray(['I', 'love', 'you', 'so', 'much'])
		words.add(new Word('танцевать', 'ru'))
		def wavDir = buildDir + '/wordsList'
		
		new GooglePronunciation().textToSpeach(words, wavDir, progressBar)
		
		assertEquals(7, new File(wavDir).list()?.length)
	}
	
	@Test
	void testProcess() {
		
		def sourceFile = 'myfile.txt'
		def outputDir = 'outputdir/mdir'
		
		def engine = new GooglePronunciation()
		
		//mock
		engine.metaClass.textToSpeach = { List<Word> words, String targetDir, boolean progressBar ->

		}
		SearchFile.metaClass.static.getDictionary = { src ->
			assertEquals('myfile.txt', src)
			
			return ['i':'ti', 'love':'tlove', 'you':'tyou']
		}
		
		engine.process(sourceFile, outputDir)
	}
	
}
