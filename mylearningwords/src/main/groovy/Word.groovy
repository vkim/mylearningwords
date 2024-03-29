
class Word {

	String value;
	String translation;
	String transcription;
	String lang = 'en';
	
	/**
	 * 
	 * @param word
	 */
	Word(String word) {
		this.value = word;
	}
	
	/**
	 * 
	 * @param word
	 * @param lang
	 */
	Word(String word, String lang) {
		this.value = word
		this.lang = lang
	}
	
	
	/**
	 * 
	 * @param value 
	 * @param translation
	 * @param transcription
	 */
	public Word(String value, String transcription, String translation) {
		super();
		this.value = value;
		this.translation = translation;
		this.transcription = transcription;
	}

	static def toWordsArray(words) {
		
		if(!words) {
			return []
		}
		
		def retValue = []
		
		for(w in words) {
			retValue.add(new Word(w))
		}
		
		return retValue
	}

	/**
	 * Removing block of characters between braces ()
	 * 	
	 * @return
	 */
	def trim() {
		value = (value =~ /\(.+\)/).replaceAll('').trim()
	}
	
	boolean equals(Object o) {
		
		if(! (o instanceof Word)) {
			return false
		}
		
		if(o.value == this.value && o.lang == this.lang) {
			return true
		}
		
		return false
	}
	
	String toString() {
		"$value"
	}
	
}
