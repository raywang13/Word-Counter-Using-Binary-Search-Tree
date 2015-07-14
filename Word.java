package project4;

public class Word implements Comparable<Object> {

	String aWord;
	int wordCount;
	
	Word(String aWord, int wordCount) {
		this.aWord = aWord;
		this.wordCount = 1;
	}
	public String getaWord() {
		return aWord;
	}
	public void setaWord(String aWord) {
		this.aWord = aWord;
	}
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	
	@Override
	public String toString() {
		return "Word: " + aWord + ", count: " + wordCount;
	}
	
	@Override
	public int compareTo(Object o) {
		
		Word newWord = (Word)o; 

		return this.aWord.compareTo(newWord.getaWord()); 
	}
	
}
