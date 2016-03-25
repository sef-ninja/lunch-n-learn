package ninja.sef.simpleindex;

import ninja.sef.simpleindex.crawler.GameCrawler;
import ninja.sef.simpleindex.crawler.TikaGameCrawler;
import ninja.sef.simpleindex.index.DocumentBuilder;
import ninja.sef.simpleindex.index.GameDocumentBuilder;
import ninja.sef.simpleindex.index.IndexCreatorOperation;
import ninja.sef.simpleindex.query.BooleanQueryOperation;
import ninja.sef.simpleindex.query.DateRangeFilterOperation;
import ninja.sef.simpleindex.query.FuzzyQueryOperation;
import ninja.sef.simpleindex.query.PhraseQueryOperation;
import ninja.sef.simpleindex.query.QueryParserOperation;
import ninja.sef.simpleindex.query.TermQueryOperation;

public class SimpleIndexApp {

    public static final String INDEX_DIR =
            "/home/hourglass/LunchNLearn/TextSearch/demo/index/simpleindex";

    public static void main(String[] args) {
        
        if(args.length != 1){
            printUsageStatement();
            System.exit(1);
        }
        
        OperationFactory.createOperation(args[0]).execute(INDEX_DIR);
    }
    
    private static void printUsageStatement() {
        System.out.println("");
        System.out.println("Usage:");
        System.out.println("    demoapp <operation>");
        System.out.println("");
        System.out.println("    Valid operations:");
        System.out.println("            -buildindex");
        System.out.println("            -termquery");
        System.out.println("            -daterangefilter");
        System.out.println("            -booleanquery");
        System.out.println("            -phrasequery");
        System.out.println("            -fuzzyquery");
        System.out.println("            -queryparser");
        System.out.println("");
    }
}
