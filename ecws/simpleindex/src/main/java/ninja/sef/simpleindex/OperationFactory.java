package ninja.sef.simpleindex;

import ninja.sef.simpleindex.crawler.GameBuilder;
import ninja.sef.simpleindex.crawler.GameCrawler;
import ninja.sef.simpleindex.crawler.TikaGameBuilder;
import ninja.sef.simpleindex.crawler.TikaGameCrawler;
import ninja.sef.simpleindex.index.DocumentBuilder;
import ninja.sef.simpleindex.index.IndexCreatorOperation;
import ninja.sef.simpleindex.index.GameDocumentBuilder;
import ninja.sef.simpleindex.query.BooleanQueryOperation;
import ninja.sef.simpleindex.query.FuzzyQueryOperation;
import ninja.sef.simpleindex.query.PhraseQueryOperation;
import ninja.sef.simpleindex.query.QueryParserOperation;
import ninja.sef.simpleindex.query.TermQueryOperation;

public class OperationFactory {
    public static Operation createOperation(String operatorSwitch) {
        
        Operation operation = null;
        GameBuilder gameBuilder = new TikaGameBuilder();
        
        if("-buildindex".equals(operatorSwitch)) {
            GameCrawler gameCrawler = new TikaGameCrawler(gameBuilder);
        	DocumentBuilder documentBuilder = new GameDocumentBuilder();
            operation = new IndexCreatorOperation(gameCrawler, documentBuilder);
        } else if("-termquery".equals(operatorSwitch)) {
            operation = new TermQueryOperation(gameBuilder);
        } else if("-booleanquery".equals(operatorSwitch)) {
            operation = new BooleanQueryOperation(gameBuilder);
        } else if("-phrasequery".equals(operatorSwitch)) {
            operation = new PhraseQueryOperation(gameBuilder);
        } else if("-fuzzyquery".equals(operatorSwitch)) {
            operation = new FuzzyQueryOperation(gameBuilder);
        } else if("-queryparser".equals(operatorSwitch)) {
            operation = new QueryParserOperation(gameBuilder);
        }
        
        return operation;
    }
}
