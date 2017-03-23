package components;

import java.util.Date;
import java.util.List;

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
	public void deleteStopwords(){
		
	}
	
	
}
