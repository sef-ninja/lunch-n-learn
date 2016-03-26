package ninja.sef.simpleindex.query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ninja.sef.simpleindex.domain.Game;

public class ResultFormatter {
    public static String format(Game game, float score) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        
        return "*** " + game.getTitle() + ", " +
        game.getFileName() + ", " +
        dateFormat.format(game.getReleaseDate()) +
        " (" + score + ") ***" +
        System.getProperty("line.separator") + game.getDescription() +
        "----------------------------------------------------";
    }
}
