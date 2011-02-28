/**
Input: word or phrase and directory where to save output.
Output: wav file with the name <word>.wav

TODO: set up wav converter.
*/

import javazoom.jl.converter.*
import javazoom.jl.decoder.*
import static SearchFile.*

class GooglePronunciation {

	static def wordToSpeach(Word word, wavFile) {   
	   
	    def url = new URL("http://translate.google.com/translate_tts?tl=${word.lang}&q=" + URLEncoder.encode(word.value))
	    def connection = url.openConnection()
	    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.0.6) Gecko/2009011912 Firefox/3.0.6")  
	    
	    if(connection.responseCode == 200){              
	        
	        new BufferedInputStream(connection.getInputStream()).withStream() {
	            convertMp3ToWav(it, wavFile)    
	        }        
	    }
	    else{
	        println "An error occurred:"
	        println connection.responseCode
	        println connection.responseMessage
	    }
	
	}
	
    static def convertMp3ToWav(input, wavFile) {
    
        Converter conv = new Converter();     

        Converter.ProgressListener listener =
            new Converter.PrintWriterProgressListener(
                new PrintWriter(System.out, true), Converter.PrintWriterProgressListener.NO_DETAIL);

        try
        {
            conv.convert(input, wavFile, listener, null);
        }
        catch (JavaLayerException ex)
        {
            System.err.println("Convertion failure: "+ex);
        }
    }
	
	static void updateProgress(double progressPercentage) {
	  final int width = 50; // progress bar width in chars
  
	  System.out.print("\r[");
	  int i = 0;
	  for (; i <= (int)(progressPercentage*width); i++) {
		System.out.print(".");
	  }
	  for (; i < width; i++) {
		System.out.print(" ");
	  }
	  System.out.print("]");
	} 
	
	void textToSpeach(List<Word> words, String targetDir, boolean progressBar) {
		
		new File(targetDir).mkdirs()
		
		def assocList = new File(targetDir + '/assoc.lst')
		
		def percentageIndex = 0, sizeDic = words.size()
		
		for(Word word in words) {
			def fileName = (word.trim() =~ / +/).replaceAll('_')
			
			wordToSpeach(word, targetDir + '/' + fileName + '.wav')
			assocList << word.value + '\t' + fileName + '.wav\n'
			
			Thread.sleep 1000
			
			updateProgress((percentageIndex++)/sizeDic)
		}
	}
	
	void process(sourceFile, outputDir) {
		
		def dictionaryMap = SearchFile.getDictionary(sourceFile)
		def wordList = Word.toWordsArray(dictionaryMap.keySet().asList())
		
		println "Size of the dictionary: " + wordList.size()
		
		textToSpeach(wordList, outputDir, true)
	}
	
    public static void main(String[] args) {
        if(args.length != 2) {
            println('Usage: Dictionary_file directory_wavFiles')
            
            System.exit(1);
        } 
        
		def outputDir = args[1]
		def dicFile = args[0]
		
		def engine = new GooglePronunciation()
		engine.process(dicFile, outputDir)
    }

}