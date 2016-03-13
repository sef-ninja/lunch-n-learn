package ninja.sef.simpleindex;

import ninja.sef.simpleindex.crawler.InfoSheetCrawler;
import ninja.sef.simpleindex.crawler.TikaInfoSheetBuilder;
import ninja.sef.simpleindex.crawler.TikaInfoSheetCrawler;
import ninja.sef.simpleindex.index.DocumentBuilder;
import ninja.sef.simpleindex.index.IndexCreatorOperation;
import ninja.sef.simpleindex.index.InfoSheetDocumentBuilder;
import ninja.sef.simpleindex.query.TermQueryOperation;

public class OperationFactory {
    public static Operation createOperation(String operatorSwitch) {
        
        Operation operation = null;
        
        if("-buildindex".equals(operatorSwitch)) {
            InfoSheetCrawler infoSheetCrawler = new TikaInfoSheetCrawler(new TikaInfoSheetBuilder());
        	DocumentBuilder documentBuilder = new InfoSheetDocumentBuilder();
            operation = new IndexCreatorOperation(infoSheetCrawler, documentBuilder);
        } else if("-termquery".equals(operatorSwitch)) {
            operation = new TermQueryOperation();
        }
        
        return operation;
    }
}
