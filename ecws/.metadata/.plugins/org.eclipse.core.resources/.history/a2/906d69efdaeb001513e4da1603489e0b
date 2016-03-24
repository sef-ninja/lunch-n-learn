package ninja.sef.simpleindex.query;

import ninja.sef.simpleindex.Operation;
import ninja.sef.simpleindex.crawler.InfoSheetBuilder;

public class BooleanQueryOperation implements Operation {

    private InfoSheetBuilder infoSheetBuilder;
    
    public BooleanQueryOperation(InfoSheetBuilder infoSheetBuilder) {
        this.infoSheetBuilder = infoSheetBuilder;
    }

    @Override
    public void execute(String indexDir) {
        
        System.out.println("[BooleanQueryOperation.execute]");
        
        // TODO: Implement and execute BooleanQuery
        
        
        // BooleanClause.Occur.SHOULD means that the clause is optional,
        // whereas BooleanClause.Occur.Must means that the clause is required.

        // However, if a boolean query only has optional clauses, at least one 
        // clause must match for a document to appear in the results.
        
        // For better control over what documents match a BooleanQuery, there is
        // also a minimumShouldMatch parameter which lets you tell Lucene that at
        // least minimumShouldMatch BooleanClause.Occur.SHOULD clauses must match
        // for a document to appear in the results.
    }

}
