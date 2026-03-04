// -------------------------------------------------------------------------

/**
 * Interface class for the DNA String Database Project
 *
 * @author CS3114/5040 Staff
 * @version Spring 2026
 *
 */
public interface DNANode {

    // ----------------------------------------------------------
    /**
     * Insert a DNA string into the database
     * 
     * @param sequence
     *            The sequence to insert
     * @param level
     *            the level on the DNA tree
     * @return The outcomes message string
     */
    public DNANode insert(String sequence, int level);


    // ----------------------------------------------------------
    /**
     * Remove a DNA string into the database
     * 
     * @param sequence
     *            The sequence to remove
     * @param level
     *            the level on the DNA tree
     * @return The outcomes message string
     */
    public DNANode remove(String sequence, int level);


    

    // ----------------------------------------------------------
    /**
     * Search for a given string
     * 
     * @param sequence
     *            The sequence to search for
     * @param level
     *            the level on the DNA tree
     * @return the print string
     */
    public String search(String sequence, int level);
}
