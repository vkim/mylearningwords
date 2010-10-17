/**
Input: word or phrase and directory where to save output.
Output: wav file with the name <word>.wav

TODO: set up wav converter.
*/

import javazoom.jl.converter.*
import javazoom.jl.decoder.*
import static SearchFile.*

class GooglePronunciation {

static def textToSpeach(word, wavFile) {   
   
    def url = new URL("http://translate.google.com/translate_tts?q=" + URLEncoder.encode(word))
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
	

    public static void main(String[] args) {
        if(args.length != 2) {
            println('Usage: Dictionary_file directory_wavFiles')
            
            System.exit(1);
        } 
        
        def dictionaryMap = SearchFile.getDictionary(args[0])
        println "Size of the dictionary: " + dictionaryMap.keySet().size()
        
		def outputDir = args[1]
		new File(outputDir).mkdirs()
		
		def assocList = new File(outputDir + '/assoc.lst')
		
		def percentageIndex = 0, sizeDic = dictionaryMap.keySet().size() 		
		
        for(def word in dictionaryMap.keySet()) {
			def trimWord = (word =~ /\([a-zA-Z ,]+\)/).replaceAll('').replaceAll(/\s*$/, '')
			def fileName = (trimWord =~ / +/).replaceAll('_')
            textToSpeach(trimWord, outputDir + '/' + fileName + '.wav')
			assocList << trimWord + '\t' + fileName + '.wav\n' 
			
			Thread.sleep 1000
			
			updateProgress((percentageIndex++)/sizeDic)
        }
    }

}