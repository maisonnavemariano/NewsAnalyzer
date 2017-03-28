package components;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Histogram {
	public Map<String,Integer> histogram;
	
	public Histogram(){
		histogram = new HashMap<String,Integer>();
	}
	public void addWord(String s){
		if(!histogram.containsKey(s))
			histogram.put(s, 1);
		else
			histogram.put(s, histogram.get(s) + 1);
	}
	
	public int getFrecuency(String word){
		return histogram.get(word);
	}
	public void setFrecuency(String word, int frec){
		histogram.put(word, frec);
	}
	public Set<String> getPalabras(){
		return histogram.keySet();
	}
	
	public Histogram mergeHistogram(Histogram h){
		Histogram new_histogram = new Histogram();
		
		for(String palabra: h.getPalabras()){
			Integer frec1 = histogram.get(palabra);
			int frec_1 = ( frec1 == null?0:frec1);
			new_histogram.setFrecuency(palabra, h.getFrecuency(palabra) + frec_1);
		}
		return new_histogram;
	}
}
