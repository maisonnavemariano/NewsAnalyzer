package components;

import java.util.Date;
import java.util.List;

public class Document {
	protected String title;
	protected List<String> words;
	protected Date date;
	
	protected Document(String t, List<String> w, Date d){
		this.title = t;
		this.words = w;
		this.date = d;
	}
	
	
	
}
