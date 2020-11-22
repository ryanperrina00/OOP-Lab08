package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE = "output.txt";
    
    private File dest = new File( HOME + SEPARATOR + DEFAULT_FILE);
    
    /**
     * 
     * @param file
     * @return a new current file if exists
     */
    public File setCurrentFile(final File file) {
        final File parent = file.getParentFile();
        if(parent.exists()) {        
            this.dest = file;
        }
        throw new IllegalArgumentException("File non trovato");    
     }
    /**
     * 
     * @return the current file
     */
    
    public File getCurrentFile(){
        return dest;
    }
    
    /**
     * 
     * @return the path of the current file
     */
    public String getFilePath() {
        return dest.getPath();
    }
    
    public void SaveOnFile(final String s)throws IOException {
        try(PrintStream out = new PrintStream(getCurrentFile())){
            out.println(s);
        }
    
    }
    
    

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

}
