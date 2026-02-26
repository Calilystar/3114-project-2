// -------------------------------------------------------------------------

/**
 * The database implementation for this project. This manages the commands for
 * the DNA tree.
 *
 * @author CS3114/5040 Staff
 * @version Spring 2026
 *
 */
public class DNADB implements DNA {

    // ----------------------------------------------------------
    /**
     * Create a new DNADB object.
     */
    public DNADB() {
    }


    // ----------------------------------------------------------
    /**
     * Insert a DNA string into the database
     * 
     * @param sequence
     *            The sequence to insert
     * @return The outcomes message string
     */
    public String insert(String sequence) {
        if (sequence == null) {
            return "Bad input: Sequence may not be null\r\n";
        }
        else if (sequence.equals("")) {
            return "Bad input: Sequence may not be empty\r\n";
        }
        else {
            for (int i = 0; i < sequence.length(); i++) {
                if (sequence.charAt(i) != 'A' && sequence.charAt(i) != 'G'
                    && sequence.charAt(i) != 'C' && sequence.charAt(i) != 'T') {
                    return "Bad Input Sequence |" + sequence + "|\r\n";
                }

            }
            return "Sequence |" + sequence + "| inserted";
        }

    }


    // ----------------------------------------------------------
    /**
     * Remove a DNA string into the database
     * 
     * @param sequence
     *            The sequence to remove
     * @return The outcomes message string
     */
    public String remove(String sequence) {
        if (sequence == null) {
            return "Bad input: Sequence may not be null\r\n";
        }
        else if (sequence.equals("")) {
            return "Bad input: Sequence may not be empty\r\n";
        }
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) != 'A' && sequence.charAt(i) != 'G'
                && sequence.charAt(i) != 'C' && sequence.charAt(i) != 'T') {
                return "Bad Input Sequence |" + sequence + "|\r\n";
            }

        }
        return "Sequence |" + sequence + "| does not exist\r\n";
    }


    // ----------------------------------------------------------
    /**
     * Print the tree
     * 
     * @return the print string
     */
    public String print() {
        return null;
    }


    // ----------------------------------------------------------
    /**
     * Print the lengths
     * 
     * @return the print string
     */
    public String printLengths() {
        return null;
    }


    // ----------------------------------------------------------
    /**
     * Print the stats
     * 
     * @return the print string
     */
    public String printStats() {
        return null;
    }


    // ----------------------------------------------------------
    /**
     * Search for a given string
     * 
     * @param sequence
     *            The sequence to search for
     * @return the print string
     */
    public String search(String sequence) {
        if (sequence == null) {
            return "Bad input: Sequence may not be null\r\n";

        }
        if (sequence.equals("")) {
            return "No sequence found\r\n # of nodes visited: 1"; 
        }

        for (int i = 0; i < sequence.length() - 1; i++) {
            if (sequence.charAt(i) != 'A' && sequence.charAt(i) != 'G'
                && sequence.charAt(i) != 'C' && sequence.charAt(i) != 'T') {
                return "Bad Input sequence |" + sequence + "|\r\n";
            }
        }
        if (sequence.charAt(sequence.length() - 1) != 'A' && sequence.charAt(
            sequence.length() - 1) != 'G' && sequence.charAt(sequence.length()
                - 1) != 'C' && sequence.charAt(sequence.length() - 1) != 'T'
            && sequence.charAt(sequence.length() - 1) != '$') {
            return "Bad Input sequence |" + sequence + "|\r\n";
        }
        return null;

    }
}
