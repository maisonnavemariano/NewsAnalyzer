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
	public boolean pertenece(String w){
		return histogram.containsKey(w);
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
			int frec1_int = ( frec1 == null?0:frec1);
			new_histogram.setFrecuency(palabra, h.getFrecuency(palabra) + frec1_int);
		}
		
		for(String palabra : histogram.keySet()){
			if(!new_histogram.pertenece(palabra)){
				new_histogram.setFrecuency(palabra, histogram.get(palabra));
			}	
		}
		return new_histogram;
	}
	public int size(){
		return histogram.size();
	}
	public String toString(){
		String toReturn = "";
		for(String palabra : histogram.keySet()){
			toReturn = toReturn + "["+palabra+", "+histogram.get(palabra)+"]\n";
		}
		return toReturn;
	}
	public String toStringLimit(int frec_min){
		String toReturn = "";
		for(String palabra : histogram.keySet()){
			if(histogram.get(palabra)>=frec_min)
				toReturn = toReturn + "["+palabra+", "+histogram.get(palabra)+"]\n";
		}
		return toReturn;
	}
	
}
