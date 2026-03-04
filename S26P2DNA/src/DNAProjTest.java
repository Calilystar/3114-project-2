import student.TestCase;
import student.testingsupport.annotations.ScoringWeight;

/**
 * @author CS3114/5040 staff
 * @version Spring 2026
 */
public class DNAProjTest extends TestCase {
    private DNA it;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        it = new DNADB();
    }


    /**
     * Test output formatting
     */
    public void testSampleInput() {
        assertFuzzyEquals("Sequence |ACGT| inserted", it.insert("ACGT"));
        assertFuzzyEquals("Sequence |ACGT| already exists", it.insert("ACGT"));
        assertFuzzyEquals("Sequence |ACGT| removed", it.remove("ACGT"));
        assertFuzzyEquals("Sequence |AAAA| inserted", it.insert("AAAA"));
        assertFuzzyEquals("Sequence |AA| inserted", it.insert("AA"));
        assertFuzzyEquals("Sequence |ACG| does not exist", it.remove("ACG"));
        assertFuzzyEquals("tree dump:\r\n" + "I\r\n" + " I\r\n" + " I\r\n"
            + " AAAA\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " AA\r\n"
            + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n"
            + " E\r\n" + " E", it.print());
        assertFuzzyEquals("tree dump with lengths:\r\n" + "I\r\n" + " I\r\n"
            + " I\r\n" + " AAAA 4\r\n" + " E\r\n" + " E\r\n" + " E\r\n"
            + " AA 2\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n"
            + " E\r\n" + " E\r\n" + " E", it.printLengths());
        assertFuzzyEquals("tree dump with stats:\r\n" + "I\r\n" + " I\r\n"
            + " I\r\n" + " AAAA A:100.00 C:0.00 G:0.00 T:0.00\r\n" + " E\r\n"
            + " E\r\n" + " E\r\n" + " AA A:100.00 C:0.00 G:0.00 T:0.00\r\n"
            + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n"
            + " E\r\n" + " E", it.printStats());
        assertFuzzyEquals("AAAA\r\n" + "# of nodes visited: 4", it.search(
            "AAAA$"));
        assertFuzzyEquals("AAAA\r\n" + "AA\r\n" + "# of nodes visited: 8", it
            .search("AA"));
        assertFuzzyEquals("No sequence found\r\n" + "# of nodes visited: 3", it
            .search("ACGT$"));
    }


    /**
     * Example tests for bad input error formatting
     */
    public void testBadInput() {
        assertFuzzyEquals("testBadInput",
            "Bad input: Sequence may not be null\r\n", it.insert(null));
        assertFuzzyEquals("testBadInput",
            "Bad input: Sequence may not be empty\r\n", it.insert(""));
        assertFuzzyEquals("testBadInput", "Bad Input Sequence |AXA|\r\n", it
            .insert("AXA"));
        assertFuzzyEquals("testBadInput", "Bad Input Sequence |A A|\r\n", it
            .insert("A A"));
        assertFuzzyEquals("testBadInput", "Bad Input Sequence |A |\r\n", it
            .insert("A "));
        assertFuzzyEquals("testBadInput", "Bad Input Sequence |A$|\r\n", it
            .insert("A$"));
        assertFuzzyEquals("testBadInput", "Bad input sequence |A$A|\r\n", it
            .search("A$A"));
    }

    public void testSearchNull() {
        assertFuzzyEquals("Bad input: Sequence may not be null\r\n", it.search(null));
    }
    
    public void testBadLetter() {
        assertFuzzyEquals("Bad input sequence |ABC|\r\n", it.search("ABC"));
    }
    
    public void testRemoveNull() {
        assertFuzzyEquals("Bad input: Sequence may not be null\r\n", it.remove(null));
    }
    
    public void testRemoveEmpty() {
        assertFuzzyEquals("Bad input: Sequence may not be empty\r\n", it.remove(""));
    }
    
    public void testRemoveBadLetter() {
        assertFuzzyEquals("Bad Input Sequence |ABC|\r\n", it.remove("ABC"));
    }

    /**
     * Tests inserting a single sequqence.
     */
    public void testSingleInsert() {
        assertFuzzyEquals("Sequence |AAAA| inserted", it.insert("AAAA"));

    }


    /**
     * Tests inserting a duplicate sequence.
     */
    public void testDuplicateInsert() {
        it.insert("AAAA");
        assertFuzzyEquals("Sequence |AAAA| already exists", it.insert("AAAA"));

    }


    /**
     * Tests print after inserting one sequence.
     */
    public void testPrintSingleNode() {
        it.insert("AAAA");
        assertFuzzyEquals("tree dump:\r\nAAAA", it.print());
    }


    /**
     * Only insert one sequence and print length
     */
    public void testPrintLengthsOfOne() {
        it.insert("AGT");
        assertFuzzyEquals("tree dump with lengths:\r\nAGT 3", it
            .printLengths());

    }


    /**
     * Insert two sequences of same internal node and print length
     */
    public void testPrintLengthsOfTwoSame() {
        it.insert("AGT");
        it.insert("A");

        assertFuzzyEquals("tree dump with lengths:\r\n" + "I\r\n" + " I\r\n"
            + "  E\r\n" + "  E\r\n" + "  AGT 3\r\n" + "  E\r\n" + "  A 1\r\n"
            + " E\r\n" + " E\r\n" + " E\r\n" + " E", it.printLengths());
    }


    /**
     * Insert two sequences of different internal nodes and print length
     */
    public void testPrintLengthsOfTwoDifferent() {
        it.insert("AGT");
        it.insert("GA");

        assertFuzzyEquals("tree dump with lengths:\r\n" + "I\r\n" + " AGT 3\r\n"
            + " E\r\n" + " GA 2\r\n" + " E\r\n" + " E", it.printLengths());
    }


    /**
     * 
     */
    public void testPrintStats25Equal() {
        it.insert("ACTG");
        assertFuzzyEquals("tree dump with stats:\r\n"
            + "ACTG A:25.00 C:25.00 G:25.00 T:25.00", it.printStats());
    }


    /**
     * 
     */
    public void testPrintStats50Equal() {
        it.insert("AC");
        assertFuzzyEquals("tree dump with stats:\r\n"
            + "AC A:50.00 C:50.00 G:0.00 T:0.00", it.printStats());

    }


    /**
     * 
     */
    public void testPrintStats100() {
        it.insert("AA");
        assertFuzzyEquals("tree dump with stats:\r\n"
            + "AA A:100.00 C:0.00 G:0.00 T:0.00", it.printStats());

    }


    /**
     * 
     */
    public void testPrintStatsDifferent() {
        it.insert("ACACTA");
        assertFuzzyEquals("tree dump with stats:\r\n"
            + "ACACTA A:50.00 C:33.33 G:0.00 T:16.67", it.printStats());
    }


    public void testPrintStatsMultiple() {
        it.insert("ACA");
        it.insert("ACC");
        it.insert("GAA");
        assertFuzzyEquals("tree dump with stats:\r\n" + "I\r\n" + " I\r\n"
            + "  E\r\n" + "  I\r\n" + "   ACA A:66.67 C:33.33 G:0.00 T:0.00\r\n"
            + "   ACC A:33.33 C:66.67 G:0.00 T:0.00\r\n" + "   E\r\n"
            + "   E\r\n" + "   E\r\n" + "  E\r\n" + "  E\r\n" + "  E\r\n"
            + " E\r\n" + " GAA A:66.67 C:0.00 G:33.33 T:0.00\r\n" + " E\r\n"
            + " E", it.printStats());
    }
    
    public void testPrintEmptyTree() {
        assertFuzzyEquals("tree dump:\r\nE", it.print());
        assertFuzzyEquals("tree dump with lengths:\r\nE", it.printLengths());
        assertFuzzyEquals("tree dump with stats:\r\nE", it.printStats());
    }
    
    public void testSearchNonExistentLong() {
        it.insert("AAAA");
        assertFuzzyEquals("No sequence found\r\n# of nodes visited: 1", it.search("AAAG$"));
    }

    public void testRemoveFromEmpty() {
        assertFuzzyEquals("Sequence |A| does not exist", it.remove("A"));
    }

    public void testStatsOnlyT() {
        it.insert("TTTT");
        assertFuzzyEquals("tree dump with stats:\r\nTTTT A:0.00 C:0.00 G:0.00 T:100.00", it.printStats());
    }
    
    public void testPrefixSearchFailsAtLeaf() {
        it.insert("ACGT");
        assertFuzzyEquals("No sequence found\r\n# of nodes visited: 1", it.search("T"));
    }

    /**
     * 
     */
}
