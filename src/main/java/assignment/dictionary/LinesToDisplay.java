package assignment.dictionary;

import java.util.Iterator;


/**
 * A class that will be used to display the lines of text that are corrected.
 *
 */

public class LinesToDisplay {

    public static final int LINES = 20;     // Display 20 lines
    private AList<Wordlet>[] lines;
    private int currentLine;

    /**
     * Constructor for objects of class LinesToDisplay
     */
    public LinesToDisplay() {
        @SuppressWarnings("unchecked")
        AList<Wordlet>[] tempLines = (AList<Wordlet>[]) new AList[LINES];
        for (int i = 0; i < LINES; i++) {
            tempLines[i] = new AList<>();
        }
        this.lines = tempLines;
        this.currentLine = 0;
    }

    /**
     * Add a new wordlet to the current line.
     *
     */
    public void addWordlet(Wordlet w) {
        lines[currentLine].add(w);
    }

    /**
     * Go to the next line, if the number of lines has exceeded LINES, shift
     * them all up by one
     *
     */
    public void nextLine() {
        currentLine++;
    }

      
    public int getCurrentLine(){
        return currentLine;
    }
    
    public AList<Wordlet>[] getLines(){
        return lines;
    }
}
