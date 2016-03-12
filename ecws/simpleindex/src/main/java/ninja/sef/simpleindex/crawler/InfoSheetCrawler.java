package ninja.sef.simpleindex.crawler;

import java.util.List;

public interface InfoSheetCrawler {
    List<InfoSheet> crawl(String dirPath);
}
