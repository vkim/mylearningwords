/**
* Main goal, create big dictionary to keep all known words in one place.
* subsequent goal, extract from new dicrionary new words only.

So there two inputs, one BIG dictionary and second new dictionary
output, dictinary only with new words in it.

*/

class SearchFile {

    /**       
    Create map[word: transcription and translation]
        1) all known words
        2) new dictionary
    there will be two maps
    truncate one from another to determine new words
    
    BUGFIX:
        - encoding    
    */
    public static void main(String[] args) {               
        
		if(args.length != 4) {
            println('Usage: {sum|sub} Dictionary1  Dictionary2 difference' + 
					'\n - sum Dictionary1 + Dictionary2 = result' +
					'\n - sub Dictionary1 - Dictionary2 = difference')
            
            System.exit(1);
        } 
        
		def operation = args[0]
		
        def myDic = getDictionary(args[2])        
        println "size 1: " + myDic.keySet().size()
        
        def newDic = getDictionary(args[1])
        println "size 2: " + newDic.keySet().size()
        
		if(operation == 'sub') {
			newDic.keySet().removeAll(myDic.keySet())
		}
		else if(operation == 'sum') {
			newDic.putAll(myDic)
		} 
		else {
			println('Operation is not defined: ')
			System.exit(1);
		}
		
        println "Size of new dictionary: " + newDic.size()        
        
        def output = getOutputFile(args[3])                          
        
        for(def line in newDic.keySet()) {
            output << line + '\t' + newDic[line] + '\n'
        }
        
        output.close()
        
    }   
    
    /**
		Cut the any dictionary definition in 3d column.
		
        return Map key:translation
    */
    def static getDictionary(filepath) {
        
        def dicMap = [:]      
 
        def wordList = readFile(filepath)  
        
        for(item in wordList) {
            def wordArray = item.split(/\t/)
            
            try {
                if(dicMap.containsKey(wordArray[0])) {
                    println 'Dublicated word: ' + wordArray[0] + ' ----- merging'
					dicMap[wordArray[0]] += '; ' + wordArray[2]
                }
                else {
					dicMap[wordArray[0]] = wordArray[1] + '\t' + wordArray[2]
				}
            } catch(ArrayIndexOutOfBoundsException e ) {
                println "e.getMessage() : in line: " + item
            }
        } 
        
        return dicMap        
    } 
    
    /**
    all files are in utf16 encoding
    */
    def static readFile(filename) {
        
        def mylist = []
    
        def rdr = new InputStreamReader(new FileInputStream(filename), 'utf16')
        println rdr.encoding
        rdr.eachLine{ mylist<< it }
        
        println 'THere is ' + mylist.size() + ' lines'                      
        
        return mylist
    }
    
    def static getOutputFile(outfile) {
        def wtr= new OutputStreamWriter(new FileOutputStream(outfile), 'UnicodeLittle')        
        wtr.write('') //empty string
        return wtr
    }
    
	def static produceWordsStatistics(dictionary, text) {
	
		def fulltext = text.toLowerCase()
		
		dictionary.keySet().each {
				println it + ' - ' + getWordCounts(it, fulltext) 
			 }
	}
	
	def static getWordCounts(word, text) {
			
		def finder = text =~ word
		
		return finder.count
	}
		
	def static getWordContext(word, text) {
		
		//([^\.\?\!]*)[\.\?\!]
		//([^.]*[.]\s*)
		def finder = text =~ word
	}
}