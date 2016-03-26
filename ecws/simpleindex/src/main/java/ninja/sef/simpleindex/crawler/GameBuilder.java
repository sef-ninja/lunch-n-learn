package ninja.sef.simpleindex.crawler;

import java.io.File;

import ninja.sef.simpleindex.domain.Game;

public interface GameBuilder {
    Game build(File gameFile);
}
