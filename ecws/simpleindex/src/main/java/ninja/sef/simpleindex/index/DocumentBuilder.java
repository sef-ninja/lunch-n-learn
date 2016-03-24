package ninja.sef.simpleindex.index;

import ninja.sef.simpleindex.crawler.Game;

import org.apache.lucene.document.Document;

public interface DocumentBuilder {
    Document build(Game game);
}
