package ninja.sef.simpleindex.query;

import ninja.sef.simpleindex.Operation;
import ninja.sef.simpleindex.crawler.GameBuilder;

public class QueryParserOperation implements Operation {

    private GameBuilder gameBuilder;
    
    public QueryParserOperation(GameBuilder gameBuilder) {
        this.gameBuilder = gameBuilder;
    }

    @Override
    public void execute(String indexDir) {
        // TODO: Implement and execute QueryParser logic
    }
}
