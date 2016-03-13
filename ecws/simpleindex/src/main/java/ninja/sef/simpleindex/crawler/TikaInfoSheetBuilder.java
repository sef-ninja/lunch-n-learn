package ninja.sef.simpleindex.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.tika.Tika;

public class TikaInfoSheetBuilder implements InfoSheetBuilder {

    public InfoSheet build(File infoSheetFile) {

        InfoSheet infoSheet = null;
        
        Tika tika = new Tika();
        try {
            
            
            // Use Tika facade one-liner
            Reader reader = tika.parse(infoSheetFile);
            
            
            
            infoSheet = new InfoSheet();
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
                        infoSheet.setTitle(line);
                    } else if("Description".equals(line)) {
                        descSection = true;
                    } else if("System".equals(prevLine)) {
                        infoSheet.setSystem(line);
                    } else if("Release Date".equals(prevLine)) {
                        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                        infoSheet.setReleaseDate(format.parse(line));
                    }
                }
                
                prevLine = line;
                line = br.readLine();
                lineNum++;
            }
            
            br.close();
            
            infoSheet.setDescription(description);
            infoSheet.setFilePath(infoSheetFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return infoSheet;
    }

}
