package ninja.sef.simpleindex.index;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;

import ninja.sef.simpleindex.domain.Game;

import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class GameDocumentBuilder implements DocumentBuilder {

    public Document build(Game game) {
        
        Document document = new Document();
        
        document.add(new TextField("title", game.getTitle(), Store.YES));
        document.add(new TextField("description", game.getDescription(), Store.NO));
        
        long releaseDate = game.getReleaseDate().getTime();
        document.add(new LongField("release_date", releaseDate, Store.NO));
        
        document.add(new StringField("system", game.getSystem(), Store.YES));
        document.add(new StringField("path", game.getFilePath(), Store.YES));

        return document;
    }

}
