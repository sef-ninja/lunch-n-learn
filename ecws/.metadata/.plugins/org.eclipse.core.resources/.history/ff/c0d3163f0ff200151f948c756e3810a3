package ninja.sef.simpleindex.crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TikaInfoSheetCrawler implements InfoSheetCrawler {

    private InfoSheetBuilder infoSheetBuilder;
    
    public TikaInfoSheetCrawler(InfoSheetBuilder infoSheetBuilder) {
        this.infoSheetBuilder = infoSheetBuilder;
    }

    public List<InfoSheet> crawl(String dirPath) {

        List<InfoSheet> infoSheetList = new ArrayList<InfoSheet>();
		
        File dir = new File(dirPath);
		for(final File infoSheetFile : dir.listFiles()) {
		    infoSheetList.add(infoSheetBuilder.build(infoSheetFile));
		}    
		
        return infoSheetList;
    }

}
