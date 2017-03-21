package newspapers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;

import components.Document;

public class TheGuardianApi {

	protected String apikey =  "5c622da5-f682-49f4-aaa5-a3d5e014b416";
	protected final int page_size = 50;
	protected String[] from_date = {"2013-01-01","2013-02-01","2013-03-01","2013-04-01","2013-05-01","2013-06-01","2013-07-01","2013-08-01", 
			"2013-09-01","2013-10-01","2013-11-01","2013-12-01"};
	protected String[] to_date   = { "2013-01-31","2013-02-28","2013-03-31","2013-04-30","2013-05-31","2013-06-30","2013-07-31","2013-08-31",
			"2013-09-30","2013-10-31","2013-11-30","2013-12-31"};
	public String[] period = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
	
	
	
	public static void main(String[] args){
	}
	
	public void generarPeriodo(int periodo){
		Document d;
		
		String API_url = "http://content.guardianapis.com/search?from-date="+from_date[periodo]+"&to-date="+to_date[periodo]+""
				+ "&page=1&page-size="+page_size+"&show-fields=all&api-key="+apikey;
		
		String documento = fetchPage(API_url);


		JSONObject response = (new JSONObject(documento)).getJSONObject("response");
		JSONObject document_aux,fields_aux;
		int cantPaginas = Integer.parseInt(response.getString("pages"));
		
		for(int page = 1; page< cantPaginas; page++){
			API_url = "http://content.guardianapis.com/search?from-date="+from_date[periodo]+"&to-date="+to_date[periodo]+""
					+ "&page="+page+"&page-size="+page_size+"&show-fields=all&api-key="+apikey;
			documento = fetchPage(API_url);
			
			JSONArray results = response.getJSONArray("results");
			for (int i = 0; i < results.length(); i++)
			{
				document_aux = results.getJSONObject(i);
				d = new Document();
				d.setWebTitle(document_aux.getString("webTitle"));
				d.setSectionName(document_aux.getString("sectionName"));
				fields_aux = document_aux.getJSONObject("fields");
				d.setHeadLine(fields_aux.getString("headline"));
				d.setTrailText(fields_aux.getString("trailText"));
				d.setBodyText(fields_aux.getString("bodyText"));
	
			}
		}
		

	}
	
	public String fetchPage(String direccion){
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
