package ninja.sef.simpleindex.query;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import ninja.sef.simpleindex.Operation;
import ninja.sef.simpleindex.crawler.InfoSheet;
import ninja.sef.simpleindex.crawler.InfoSheetBuilder;

public class PhraseQueryOperation implements Operation {

    private InfoSheetBuilder infoSheetBuilder;
    
    public PhraseQueryOperation(InfoSheetBuilder infoSheetBuilder) {
        this.infoSheetBuilder = infoSheetBuilder;
    }

    @Override
    public void execute(String indexDir) {
        int topHitNum = 30;
        
        try {
            Directory indexDirectory = FSDirectory.open(new File(indexDir));
            IndexReader indexReader = DirectoryReader.open(indexDirectory);
            IndexSearcher searcher = new IndexSearcher(indexReader);
            
            PhraseQuery phraseQuery = new PhraseQuery();
            phraseQuery.add(new Term("description", "legendary"));
            phraseQuery.add(new Term("description", "sacred"));
            phraseQuery.add(new Term("description", "realm"));
            
            TopDocs topDocs = searcher.search(phraseQuery, topHitNum);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                
                String path = doc.get("path");
                
                InfoSheet infoSheet = infoSheetBuilder.build(new File(path));
                System.out.println("*** " + infoSheet.getTitle() + " (" + scoreDoc.score + ") ***");
                System.out.println(infoSheet.getDescription());
                System.out.println("-------------------------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}