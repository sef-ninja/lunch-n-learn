package ninja.sef.simpleindex.index;

import java.util.List;

import ninja.sef.simpleindex.domain.Game;

public interface TextIndexer {
    void initializeIndex(List<Game> games);
}
