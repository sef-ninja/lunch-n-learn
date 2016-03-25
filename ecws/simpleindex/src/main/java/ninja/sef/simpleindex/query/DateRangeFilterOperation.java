package ninja.sef.simpleindex.query;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import ninja.sef.simpleindex.Operation;
import ninja.sef.simpleindex.crawler.Game;
import ninja.sef.simpleindex.crawler.GameBuilder;

public class DateRangeFilterOperation implements Operation {

    private GameBuilder gameBuilder;
    
    public DateRangeFilterOperation(GameBuilder gameBuilder) {
        this.gameBuilder = gameBuilder;
    }

    @Override
    public void execute(String indexDir) {
        int topHitNum = 30;
        Term term = new Term("description", "adventure");
        TermQuery termQuery = new TermQuery(term);
        
        try {
            Directory indexDirectory = FSDirectory.open(new File(indexDir));
            IndexReader indexReader = DirectoryReader.open(indexDirectory);
            IndexSearcher searcher = new IndexSearcher(indexReader);
            
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date minDate = dateFormat.parse("01/01/2000");
            Date maxDate = dateFormat.parse("12/31/2015");
            
            Query dateRangeQuery = NumericRangeQuery.newLongRange("release_date",
                    minDate.getTime(), maxDate.getTime(), true, true);
                        
            QueryWrapperFilter filter = new QueryWrapperFilter(dateRangeQuery);
            
            TopDocs topDocs = searcher.search(termQuery, filter, topHitNum);
            
            
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                String path = doc.get("path");
                
                Game game = gameBuilder.build(new File(path));
                                
                System.out.println("*** " + game.getTitle() + ", " +
                        game.getFileName() + ", " +
                        dateFormat.format(game.getReleaseDate()) +
                        " (" + scoreDoc.score + ") ***");
                System.out.println();
                System.out.println(game.getDescription());
                System.out.println("-------------------------------------------------------");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
