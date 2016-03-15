package ninja.sef.simpleindex;

import ninja.sef.simpleindex.crawler.InfoSheetBuilder;
import ninja.sef.simpleindex.crawler.InfoSheetCrawler;
import ninja.sef.simpleindex.crawler.TikaInfoSheetBuilder;
import ninja.sef.simpleindex.crawler.TikaInfoSheetCrawler;
import ninja.sef.simpleindex.index.DocumentBuilder;
import ninja.sef.simpleindex.index.IndexCreatorOperation;
import ninja.sef.simpleindex.index.InfoSheetDocumentBuilder;
import ninja.sef.simpleindex.query.BooleanQueryOperation;
import ninja.sef.simpleindex.query.FuzzyQueryOperation;
import ninja.sef.simpleindex.query.PhraseQueryOperation;
import ninja.sef.simpleindex.query.TermQueryOperation;

public class OperationFactory {
    public static Operation createOperation(String operatorSwitch) {
        
        Operation operation = null;
        InfoSheetBuilder infoSheetBuilder = new TikaInfoSheetBuilder();
        
        if("-buildindex".equals(operatorSwitch)) {
            InfoSheetCrawler infoSheetCrawler = new TikaInfoSheetCrawler(infoSheetBuilder);
        	DocumentBuilder documentBuilder = new InfoSheetDocumentBuilder();
            operation = new IndexCreatorOperation(infoSheetCrawler, documentBuilder);
        } else if("-termquery".equals(operatorSwitch)) {
            operation = new TermQueryOperation(infoSheetBuilder);
        } else if("-booleanquery".equals(operatorSwitch)) {
            operation = new BooleanQueryOperation(infoSheetBuilder);
        } else if("-phrasequery".equals(operatorSwitch)) {
            operation = new PhraseQueryOperation(infoSheetBuilder);
        } else if("-fuzzyquery".equals(operatorSwitch)) {
            operation = new FuzzyQueryOperation(infoSheetBuilder);
        }
        
        return operation;
    }
}
