package ninja.sef.simpleindex.query;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import ninja.sef.simpleindex.ConsoleUtilities;
import ninja.sef.simpleindex.Operation;
import ninja.sef.simpleindex.crawler.GameBuilder;
import ninja.sef.simpleindex.domain.Game;

public class BooleanQueryOperation implements Operation {

    private GameBuilder gameBuilder;
    
    public BooleanQueryOperation(GameBuilder gameBuilder) {
        this.gameBuilder = gameBuilder;
    }

    @Override
    public void execute(String indexDir) {
        
        ConsoleUtilities.printHeader();
        
        try {
            Directory indexDirectory = FSDirectory.open(new File(indexDir));
            IndexReader indexReader = DirectoryReader.open(indexDirectory);
            IndexSearcher searcher = new IndexSearcher(indexReader);
            
            
            System.out.println("[BooleanQueryOperation.execute]");
            
            // TODO: Implement and execute BooleanQuery
            PhraseQuery phraseQuery1 = new PhraseQuery();
            phraseQuery1.add(new Term("description", "legendary"));
            phraseQuery1.add(new Term("description", "sacred"));
            phraseQuery1.add(new Term("description", "realm"));
            
            //cursed land
            PhraseQuery phraseQuery2 = new PhraseQuery();
            phraseQuery2.add(new Term("description", "cursed"));
            phraseQuery2.add(new Term("description", "land"));
            
            
            BooleanQuery booleanQuery = new BooleanQuery();
            booleanQuery.add(new BooleanClause(phraseQuery1, Occur.SHOULD));
            booleanQuery.add(new BooleanClause(phraseQuery2, Occur.SHOULD));
            
            int topHitNum = 30;
            TopDocs topDocs = searcher.search(booleanQuery, topHitNum);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                
                String path = doc.get("path");
                
                Game game = gameBuilder.build(new File(path));
                System.out.println(ResultFormatter.format(game, scoreDoc.score));
            }
            
            
            // BooleanClause.Occur.SHOULD means that the clause is optional,
            // whereas BooleanClause.Occur.Must means that the clause is required.

            // However, if a boolean query only has optional clauses, at least one 
            // clause must match for a document to appear in the results.
            
            // For better control over what documents match a BooleanQuery, there is
            // also a minimumShouldMatch parameter which lets you tell Lucene that at
            // least minimumShouldMatch BooleanClause.Occur.SHOULD clauses must match
            // for a document to appear in the results.
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
    }

}
