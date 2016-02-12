package ninja.sef.simpleindex.index;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ninja.sef.simpleindex.Operation;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class IndexCreatorOperation implements Operation {

    public void execute() {
        
        // TODO: RL
        System.out.println("[IndexCreatorOperation] execute");
        
        
       
        try {
            // TODO: Extract content from example01.pdf
            File document = new File("/home/hourglass/LunchNLearn/TextSearch/demo/example/example01.pdf");
            OutputStream output =
                    new FileOutputStream(new File("/home/hourglass/LunchNLearn/TextSearch/demo/example/output.txt"));
            
            ContentHandler contentHandler = new BodyContentHandler(output);
            
            Parser parser = new AutoDetectParser();
            ParseContext context = new ParseContext();
            Metadata metadata = new Metadata();
            
            parser.parse(new FileInputStream(document), contentHandler, metadata, context);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
        
//      System.out.println("This will index multiple documents and store the index here: " + INDEX_DIR);
//      
//      TextIndexer indexer = new LuceneTextIndexer(INDEX_DIR);
//      indexer.initializeIndex();    

    }
}
