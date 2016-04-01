package ninja.sef.simpleindex.index;

import java.util.List;

import ninja.sef.simpleindex.Operation;
import ninja.sef.simpleindex.crawler.GameCrawler;
import ninja.sef.simpleindex.domain.Game;

public class IndexCreatorOperation implements Operation {

    private static final String GAME_DIR =
            "/home/hourglass/LunchNLearn/TextSearch/demo/kingdom_of_hyrule";
    private GameCrawler gameCrawler;
    private GameIndexer gameIndexer;
        
    public IndexCreatorOperation(GameCrawler gameCrawler, GameIndexer gameIndexer) {
        this.gameCrawler = gameCrawler;
        this.gameIndexer = gameIndexer;
    }

    public void execute(String indexDir) {
      
        List<Game> games = gameCrawler.crawl(GAME_DIR);
        
        for(Game game : games) {
            System.out.println(game + System.getProperty("line.separator") + "-------------------------------");
        }
        
        System.out.println("This will index multiple documents and store the index here: " + indexDir);
      
        gameIndexer.initializeIndex(indexDir, games);    
    }
}
