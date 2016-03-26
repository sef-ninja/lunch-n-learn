package ninja.sef.simpleindex.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.tika.Tika;

import ninja.sef.simpleindex.domain.Game;

public class TikaGameBuilder implements GameBuilder {

    public Game build(File gameFile) {

        Game game = null;
        
        Tika tika = new Tika();
        try {
            
            
            // Use Tika facade one-liner
            Reader reader = tika.parse(gameFile);
            
            
            
            game = new Game();
            String description = "";

            String prevLine = "";
            boolean descSection = false;
            int lineNum = 1;
            
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while(line != null) {
                if(descSection){
                    if("Box".equals(line)){
                        descSection = false;
                    } else {
                        description += line + System.getProperty("line.separator");
                    }
                } else {
                    if(lineNum == 2) {
                        game.setTitle(line);
                    } else if("Description".equals(line)) {
                        descSection = true;
                    } else if("System".equals(prevLine)) {
                        game.setSystem(line);
                    } else if("Release Date".equals(prevLine)) {
                        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                        game.setReleaseDate(format.parse(line));
                    }
                }
                
                prevLine = line;
                line = br.readLine();
                lineNum++;
            }
            
            br.close();
            
            game.setDescription(description);
            game.setFilePath(gameFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return game;
    }

}
