package ninja.sef.simpleindex.query;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import ninja.sef.simpleindex.Operation;
import ninja.sef.simpleindex.crawler.InfoSheet;
import ninja.sef.simpleindex.crawler.InfoSheetBuilder;

public class FuzzyQueryOperation implements Operation {

    private InfoSheetBuilder infoSheetBuilder;
    
    public FuzzyQueryOperation(InfoSheetBuilder infoSheetBuilder) {
        this.infoSheetBuilder = infoSheetBuilder;
    }

    @Override
    public void execute(String indexDir) {
        int topHitNum = 30;
        
        Directory indexDirectory;
        try {
            indexDirectory = FSDirectory.open(new File(indexDir));
            IndexReader indexReader = DirectoryReader.open(indexDirectory);
            IndexSearcher searcher = new IndexSearcher(indexReader);
            
            Term term = new Term("title", "uhventure");
            FuzzyQuery fuzzyQuery = new FuzzyQuery(term);
            
            TopDocs topDocs = searcher.search(fuzzyQuery, topHitNum);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                String path = doc.get("path");
                
                InfoSheet infoSheet = infoSheetBuilder.build(new File(path));
                System.out.println("*** " + infoSheet.getTitle() + " (" + scoreDoc.score + ") ***");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}