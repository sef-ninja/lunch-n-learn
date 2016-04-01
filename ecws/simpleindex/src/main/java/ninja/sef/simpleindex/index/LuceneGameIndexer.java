package ninja.sef.simpleindex.index;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import ninja.sef.simpleindex.domain.Game;

public class LuceneGameIndexer implements GameIndexer {

    private DocumentBuilder documentBuilder;
    
    public LuceneGameIndexer(DocumentBuilder documentBuilder) {
        this.documentBuilder = documentBuilder;
    }

    public void initializeIndex(String indexDirPath, List<Game> games) {
        try {
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44,
                    analyzer);
            
            Directory indexDir = FSDirectory.open(new File(indexDirPath));
            IndexWriter indexWriter = new IndexWriter(indexDir, indexWriterConfig);
            
            for(Game game : games) {
                
                System.out.println("Indexing: " + game.getTitle());
                
                Document document = documentBuilder.build(game);
                indexWriter.addDocument(document);
            }
            
            indexWriter.commit();
            indexWriter.close();
            
            System.out.println("Initialized full-text index");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
