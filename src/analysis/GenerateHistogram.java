package analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import components.Document;
import components.Histogram;

public class GenerateHistogram {
	protected final static String myfile = "base de datos/Enero_noticias/Enero";
	protected static final String WEB_TITLE = "webTitle: ";
	protected static final String SECTION_NAME = "sectionName: ";
	protected static final String HEADLINE = "headline: ";
	protected static final String TRAILTEXT = "trailText: ";
	protected static final String WEB_DATE = "webPublicationDate: ";
	protected static final String BODYTEXT = "bodytext: ";
	
	public static void main (String[] args){
		StopwordsFilter filter = new StopwordsFilter();
		List<Document> lista = loadDocumentsFile(myfile);

		memoriaUsada();
		for(Document d : lista){
			d.deleteStopwords(filter);
		}
		Histogram histograma_total = new Histogram();
		for(Document d : lista){
			histograma_total = histograma_total.mergeHistogram(d.getHistogram());
		}
		
		

		memoriaUsada();
	}
	
	public static List<Document> loadDocumentsFile(String file){
		List<Document> documentos = new ArrayList<Document>();
		Document documento_auxiliar = null;
		//CÓDIGO
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       if(line.startsWith(WEB_TITLE)){
		    	   if(documento_auxiliar != null){
		    		   documentos.add(documento_auxiliar);
		    	   }
		    	   documento_auxiliar = new Document();
		    	   documento_auxiliar.setWebTitle(line.equals(WEB_TITLE)?"":line.substring("webTitle: ".length(),line.length()));
		       }

		       if(line.startsWith(SECTION_NAME)){
		    	   documento_auxiliar.setSectionName(line.equals(SECTION_NAME)?"":line.substring(SECTION_NAME.length(),line.length()));
		    	   
		       }
		       if(line.startsWith(HEADLINE)){
		    	   documento_auxiliar.setHeadLine(line.equals(HEADLINE)?"":line.substring(HEADLINE.length(),line.length()));		    	   
		       }
		       if(line.startsWith(TRAILTEXT)){
		    	   documento_auxiliar.setTrailText(line.equals(TRAILTEXT)?"":line.substring(TRAILTEXT.length(),line.length()));		    		    	   
		       }
		       if(line.startsWith(WEB_DATE)){
		    	   documento_auxiliar.setTrailText(line.equals(WEB_DATE)?"":line.substring(WEB_DATE.length(),line.length()));	
		    	   
		       }
		       if(line.startsWith(BODYTEXT)){
		    	   documento_auxiliar.setBodyText(line.equals(BODYTEXT)? "":line.substring(BODYTEXT.length(),line.length()));	
		    	   
		       }

		       
//		       webTitle: Steve Bell on David Cameron's trip to Libya â€“ cartoon
//		       sectionName: Opinion
//		       headline: Steve Bell on David Cameron's trip to Libya â€“ cartoon
//		       trailText: <p>The prime minister makes an unexpected visit to Tripoli</p>
//		       webPublicationDate: 2013-01-31T23:45:38Z
//		       bodytext: 
//		       webTitle: Stoke sign goalkeeper Jack Butland from Birmingham City for Â£4m
//		       sectionName: Football
//		       headline: Stoke sign goalkeeper Jack Butland from Birmingham City for Â£4m
//		       trailText: Stoke have signed the Birmingham goalkeeper Jack Butland, who made his England debut in August, for Â£4m

		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documentos;
	}
	
	private static void memoriaUsada(){
		NumberFormat formatter = new DecimalFormat("#0.00");     
		Runtime.getRuntime().gc();
		long used  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		double used_mem = ((used / 1024.0) / 1024.0);
		System.out.println("Memoria usada: "+formatter.format(used_mem)+" MB");
	}
}
