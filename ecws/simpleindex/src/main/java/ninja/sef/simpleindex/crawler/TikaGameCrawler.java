package ninja.sef.simpleindex.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ninja.sef.simpleindex.domain.Game;

public class TikaGameCrawler implements GameCrawler {

    private GameBuilder gameBuilder;
    
    public TikaGameCrawler(GameBuilder gameBuilder) {
        this.gameBuilder = gameBuilder;
    }

    public List<Game> crawl(String dirPath) {

        List<Game> gameList = new ArrayList<Game>();
		
        File dir = new File(dirPath);
		for(final File gameFile : dir.listFiles()) {
		    gameList.add(gameBuilder.build(gameFile));
		}    
		
        return gameList;
    }

}
