package ninja.sef.simpleindex.crawler;

import java.util.List;

import ninja.sef.simpleindex.domain.Game;

public interface GameCrawler {
    List<Game> crawl(String dirPath);
}
