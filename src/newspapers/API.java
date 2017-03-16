package newspapers;

import java.util.Date;
import java.util.List;

import components.Document;

public interface API {
	public List<Document> buscar(Date fecha);
	public List<Document> buscar(Date ini, Date fin);
}
