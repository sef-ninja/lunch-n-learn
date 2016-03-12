package ninja.sef.tikademo;

import java.io.File;
import java.io.FileInputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class TikaDemoApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = args[0];
		
        try {
    		File document = new File(path);
            
            ContentHandler contentHandler = new BodyContentHandler(System.out);
            
            Parser parser = new AutoDetectParser();
            ParseContext context = new ParseContext();
            Metadata metadata = new Metadata();

        	parser.parse(new FileInputStream(document), contentHandler, metadata, context);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
