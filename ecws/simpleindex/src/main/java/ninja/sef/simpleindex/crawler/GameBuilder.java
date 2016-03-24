package ninja.sef.simpleindex.crawler;

import java.io.File;

public interface GameBuilder {
    Game build(File gameFile);
}
