package components;

import java.util.Date;

import analysis.StopwordsFilter;

public class Document {
	protected String webTitle;
	protected String sectionName;
	protected String headline;
	protected String trailText;
	protected String bodyText;
	protected String webPublicationDate;
	
	//protected List<String> words;
	protected Date date;
	
	public Document(){
	}
	
	public void setWebTitle(String w){
		this.webTitle = w;
	}
	public void setSectionName(String s){
		this.sectionName = s;
	}
	public void setHeadLine(String h){
		this.headline = h;
	}
	public void setTrailText(String t){
		this.trailText = t;
	}
	public void setBodyText(String b){
		this.bodyText = b;
	}
	public void setWebPublicationDate(String d){
		this.webPublicationDate = d;
	}
	public String toString(){
		return "webTitle: "+this.webTitle+"\nsectionName: "+this.sectionName+"\nheadline: "+this.headline+"\ntrailText: "+this.trailText+"\nwebPublicationDate: "+this.webPublicationDate+"\nbodytext: "+this.bodyText+"\n";
	}
	public void deleteStopwords(StopwordsFilter filter){
		this.bodyText = this.eliminateStopwordsFromString(filter, bodyText);
		this.trailText= this.eliminateStopwordsFromString(filter, trailText);
		this.headline = this.eliminateStopwordsFromString(filter, headline);
		this.webTitle = this.eliminateStopwordsFromString(filter, webTitle);

	}
	
	private String eliminateStopwordsFromString(StopwordsFilter filter, String texto){
		String new_bodyText = "";
		for(String word : eliminateNonCharAndWhiteSpaces(texto).split(" ")){
			if(! filter.isStopword(word))
				new_bodyText += word;
		}
		return new_bodyText;
	}
	private String eliminateNonCharAndWhiteSpaces(String s){
		return s.replaceAll("\\s+", " ").trim().replaceAll("[^A-Za-z0-9 ']", "");
	}
	
	public Histogram getHistogram(){
		Histogram histogram = new Histogram();
		
		for(String palabra: webTitle.split(" ")){
			histogram.addWord(palabra);
		}
		for(String palabra: headline.split(" ")){
			histogram.addWord(palabra);
		}
		for(String palabra : trailText.split(" ")){
			histogram.addWord(palabra);
		}
		for(String palabra : bodyText.split(" ")){
			histogram.addWord(palabra);
		}
		return histogram;
	}
}
