package analysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StopwordsFilter {
	protected String stop_words_file = "stopwords/stopwords.txt"; //archivo por defecto
	protected Set<String> stopwords;
	
	public StopwordsFilter(String myfile){
		stop_words_file = myfile;
		loadStopwords();
	}
	
	public StopwordsFilter(){
		loadStopwords();
	}
	
	public void loadStopwords(){
		stopwords = new HashSet<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(stop_words_file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       stopwords.add(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isStopword(String s){
		return stopwords.contains(s);
	}
}
