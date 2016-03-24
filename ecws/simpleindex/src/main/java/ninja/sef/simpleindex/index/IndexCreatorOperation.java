package ninja.sef.simpleindex.index;

import java.util.List;

import ninja.sef.simpleindex.Operation;
import ninja.sef.simpleindex.crawler.Game;
import ninja.sef.simpleindex.crawler.GameCrawler;

public class IndexCreatorOperation implements Operation {

    private static final String GAME_DIR =
            "/home/hourglass/LunchNLearn/TextSearch/demo/kingdom_of_hyrule";
    private GameCrawler gameCrawler;
    private DocumentBuilder documentBuilder;
    
    public IndexCreatorOperation(GameCrawler gameCrawler,
            DocumentBuilder documentBuilder) {
        this.gameCrawler = gameCrawler;
        this.documentBuilder = documentBuilder;
    }

    public void execute(String indexDir) {
        
        // TODO: RL
        System.out.println("[IndexCreatorOperation] execute");
       
        List<Game> games = gameCrawler.crawl(GAME_DIR);
        
        for(Game game : games) {
            System.out.println(game + System.getProperty("line.separator") + "-------------------------------");
        }
        
        System.out.println("This will index multiple documents and store the index here: " + indexDir);
      
        TextIndexer indexer = new LuceneTextIndexer(indexDir, documentBuilder);
        indexer.initializeIndex(games);    

    }
}
