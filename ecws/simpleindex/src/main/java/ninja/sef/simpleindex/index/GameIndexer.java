package ninja.sef.simpleindex.index;

import java.util.List;

import ninja.sef.simpleindex.domain.Game;

public interface GameIndexer {
    void initializeIndex(String indexDirPath, List<Game> games);
}
