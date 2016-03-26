package ninja.sef.simpleindex.index;

import org.apache.lucene.document.Document;

import ninja.sef.simpleindex.domain.Game;

public interface DocumentBuilder {
    Document build(Game game);
}
