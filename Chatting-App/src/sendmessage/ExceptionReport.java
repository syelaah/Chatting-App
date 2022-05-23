package sendmessage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sye
 */
public class ExceptionReport {
            
    public void report() {
        final String PATH = "src/sendmessage/";
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(PATH + "/error.log", true));
        } catch (IOException ex) {
            Logger.getLogger(ExceptionReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
