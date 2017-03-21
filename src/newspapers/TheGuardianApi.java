package newspapers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.*;

import components.Document;

public class TheGuardianApi {
	

	protected static String apikey =  "5c622da5-f682-49f4-aaa5-a3d5e014b416";
	protected static final int page_size = 50;
	protected static String[] from_date = {"2013-01-01","2013-02-01","2013-03-01","2013-04-01","2013-05-01","2013-06-01","2013-07-01","2013-08-01", 
			"2013-09-01","2013-10-01","2013-11-01","2013-12-01"};
	protected static String[] to_date   = { "2013-01-31","2013-02-28","2013-03-31","2013-04-30","2013-05-31","2013-06-30","2013-07-31","2013-08-31",
			"2013-09-30","2013-10-31","2013-11-30","2013-12-31"};
	public static String[] period = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
	
	
	
	public static void main(String[] args){
		generarPeriodo(0);
		

	}
	
	public static void generarPeriodo(int periodo){
		List<Document> lista = new ArrayList<Document>();

		String directorio = period[0]+"_noticias";
		File dir = new File(directorio);
		dir.mkdir();
		PrintWriter writer = null;
	    try {
			 writer = new PrintWriter(directorio+"/"+period[periodo], "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document d;
		
		String API_url = "http://content.guardianapis.com/search?from-date="+from_date[periodo]+"&to-date="+to_date[periodo]+""
				+ "&page=1&page-size="+page_size+"&show-fields=all&api-key="+apikey;
		
		String documento = fetchPage(API_url);


		JSONObject response = (new JSONObject(documento)).getJSONObject("response");
		JSONObject document_aux,fields_aux;
		int cantPaginas = response.getInt("pages"); 
		
		for(int page = 1;page <=cantPaginas; page++){
			delay(1);
			API_url = "http://content.guardianapis.com/search?from-date="+from_date[periodo]+"&to-date="+to_date[periodo]+""
					+ "&page="+page+"&page-size="+page_size+"&show-fields=all&api-key="+apikey;
			documento = fetchPage(API_url);
			
			JSONArray results = response.getJSONArray("results");
			for (int i = 0; i < results.length(); i++)
			{
				document_aux = results.getJSONObject(i);
				d = new Document();
				d.setWebPublicationDate(document_aux.getString("webPublicationDate"));
				d.setWebTitle(document_aux.getString("webTitle"));
				d.setSectionName(document_aux.getString("sectionName"));
				fields_aux = document_aux.getJSONObject("fields");
				d.setHeadLine(fields_aux.getString("headline"));
				d.setTrailText(fields_aux.getString("trailText"));
				d.setBodyText(fields_aux.getString("bodyText"));
				
				lista.add(d);
				if(lista.size()==200){
					for(Document doc: lista){
						writer.write(doc.toString());
					}
					lista = new ArrayList<Document>();
				}
	
			}
		}

	    writer.close();
		

	}
	public static void delay(int seg){
		try {
			TimeUnit.SECONDS.sleep(seg);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String fetchPage(String direccion){
		String toReturn = "";
		URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line;

	    try {
	        url = new URL(direccion);
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));

	        while ((line = br.readLine()) != null) {
	        	toReturn = toReturn + line;
	        }
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	    return toReturn;
	    
	}

	
	
}
