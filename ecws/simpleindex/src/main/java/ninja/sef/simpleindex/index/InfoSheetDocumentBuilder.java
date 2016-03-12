package ninja.sef.simpleindex.index;

import ninja.sef.simpleindex.crawler.InfoSheet;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class InfoSheetDocumentBuilder implements DocumentBuilder {

    public Document build(InfoSheet infoSheet) {
        
        Document document = new Document();
        
        document.add(new TextField("title", infoSheet.getTitle(), Store.YES));
        document.add(new TextField("description", infoSheet.getDescription(), Store.NO));
        
        String releaseDate = DateTools.dateToString(infoSheet.getReleaseDate(), Resolution.DAY);
        document.add(new StringField("release_date", releaseDate, Store.NO));
        
        document.add(new StringField("system", infoSheet.getSystem(), Store.YES));
        document.add(new StringField("path", infoSheet.getFilePath(), Store.YES));

        return document;
    }

}
