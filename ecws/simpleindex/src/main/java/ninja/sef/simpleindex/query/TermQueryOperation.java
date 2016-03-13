package ninja.sef.simpleindex.query;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import ninja.sef.simpleindex.Operation;

public class TermQueryOperation implements Operation {

    @Override
    public void execute(String indexDir) {
        int topHitNum = 30;
        Term term = new Term("description", "adventure");
        TermQuery termQuery = new TermQuery(term);
        
        try {
            Directory indexDirectory = FSDirectory.open(new File(indexDir));
            IndexReader indexReader = DirectoryReader.open(indexDirectory);
            IndexSearcher searcher = new IndexSearcher(indexReader);
            
            
            TopDocs topDocs = searcher.search(termQuery, topHitNum);
            
            
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                System.out.println(doc.get("title"));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
