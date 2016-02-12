package ninja.sef.simpleindex.index;

import java.io.File;
import java.io.IOException;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneTextIndexer implements TextIndexer {

    private String indexDirPath = "";
    
    public LuceneTextIndexer(String indexDirPath) {
        this.indexDirPath = indexDirPath;
    }

    public void initializeIndex() {
        try {
            Directory indexDir = FSDirectory.open(new File(indexDirPath));
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, new StandardAnalyzer(Version.LUCENE_44));
            IndexWriter indexWriter = new IndexWriter(indexDir, indexWriterConfig);
            
            Document document1 = new Document();
            document1.add(new TextField("title", "A really short title", Field.Store.YES));
            document1.add(new TextField("introduction", "This is a pretty long introduction.", Field.Store.NO));
            document1.add(new StringField("location", "http://sef.ninja/ganondorf.jpg", Field.Store.YES));
            
            Document document2 = new Document();
            document2.add(new TextField("title", "The Adventures of Some Hero", Field.Store.YES));
            document2.add(new TextField("introduction", "Link is a hero in the land of Hyrule.", Field.Store.NO));
            document2.add(new StringField("location", "http://sef.ninja/link.jpg", Field.Store.YES));
            
            indexWriter.addDocument(document1);
            indexWriter.addDocument(document2);
            
            indexWriter.commit();
            indexWriter.close();
            
            System.out.println("Initialized full-text index");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
