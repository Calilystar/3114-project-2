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


//    /**
//     * Test output formatting
//     */
//    public void testSampleInput() {
//        assertFuzzyEquals("Sequence |ACGT| inserted", it.insert("ACGT"));
//        assertFuzzyEquals("Sequence |ACGT| already exists", it.insert("ACGT"));
//        assertFuzzyEquals("Sequence |ACGT| removed", it.remove("ACGT"));
//        assertFuzzyEquals("Sequence |AAAA| inserted", it.insert("AAAA"));
//        assertFuzzyEquals("Sequence |AA| inserted", it.insert("AA"));
//        assertFuzzyEquals("Sequence |ACG| does not exist", it.remove("ACG"));
//        assertFuzzyEquals("tree dump:\r\n" + "I\r\n" + " I\r\n" + " I\r\n"
//            + " AAAA\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " AA\r\n"
//            + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n"
//            + " E\r\n" + " E", it.print());
//        assertFuzzyEquals("tree dump with lengths:\r\n" + "I\r\n" + " I\r\n"
//            + " I\r\n" + " AAAA 4\r\n" + " E\r\n" + " E\r\n" + " E\r\n"
//            + " AA 2\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n"
//            + " E\r\n" + " E\r\n" + " E", it.printLengths());
//        assertFuzzyEquals("tree dump with stats:\r\n" + "I\r\n" + " I\r\n"
//            + " I\r\n" + " AAAA A:100.00 C:0.00 G:0.00 T:0.00\r\n" + " E\r\n"
//            + " E\r\n" + " E\r\n" + " AA A:100.00 C:0.00 G:0.00 T:0.00\r\n"
//            + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n" + " E\r\n"
//            + " E\r\n" + " E", it.printStats());
//        assertFuzzyEquals("AAAA\r\n" + "# of nodes visited: 4", it.search(
//            "AAAA$"));
//        assertFuzzyEquals("AAAA\r\n" + "AA\r\n" + "# of nodes visited: 8", it
//            .search("AA"));
//        assertFuzzyEquals("No sequence found\r\n" + "# of nodes visited: 3", it
//            .search("ACGT$"));
//    }


    /**
     * Example tests for bad input error formatting
     */
    public void testBadInput() {
        assertFuzzyEquals("testBadInput", "No sequence found\r\n" + "# of nodes visited: 1", it.search(""));
        
        
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
        
        

        assertFuzzyEquals("testBadInput", "Bad input sequence |AB|\r\n", it
            .search("AB"));
        assertFuzzyEquals("testBadInput", "Bad input sequence |TB|\r\n", it
            .search("TB"));
        assertFuzzyEquals("testBadInput", "Bad input sequence |CB|\r\n", it
            .search("CB"));
        assertFuzzyEquals("testBadInput", "Bad input sequence |GB|\r\n", it
            .search("GB"));
        assertFuzzyEquals("testBadInput",
            "Bad input: Sequence may not be null\r\n", it.search(null));

        assertFuzzyEquals("testBadInput", "Bad input sequence |A$A|\r\n", it
            .search("A$A"));
        
        assertFuzzyEquals("testBadInput", "Bad input sequence |$AGC|\r\n", it
            .search("$AGC"));
        assertFuzzyEquals("testBadInput", "Bad input sequence |$AGT|\r\n", it
            .search("$AGT"));
        assertFuzzyEquals("testBadInput", "Bad input sequence |$AGA|\r\n", it
            .search("$AGA"));
        assertFuzzyEquals("testBadInput", "Bad input sequence |$AGG|\r\n", it
            .search("$AGG"));
        assertFuzzyEquals("testBadInput", "Bad input sequence |$AGB|\r\n", it
            .search("$AGB"));
        assertFuzzyEquals("testBadInput", "Bad input sequence |AGB|\r\n", it
            .search("AGB"));

        assertFuzzyEquals("testBadInput", "Bad Input Sequence |AB|\r\n", it
            .remove("AB"));
        assertFuzzyEquals("testBadInput", "Bad Input Sequence |CB|\r\n", it
            .remove("CB"));
        assertFuzzyEquals("testBadInput", "Bad Input Sequence |GB|\r\n", it
            .remove("GB"));
        assertFuzzyEquals("testBadInput", "Bad Input Sequence |TB|\r\n", it
            .remove("TB"));
        assertFuzzyEquals("testBadInput",
            "Bad input: Sequence may not be null\r\n", it.remove(null));
        assertFuzzyEquals("testBadInput",
            "Bad input: Sequence may not be empty\r\n", it.remove(""));
    }


    /**
     * Test output formatting
     */
    public void testGoodInput() {
        assertFuzzyEquals("Sequence |ACGT| inserted", it.insert("ACGT"));
        assertFuzzyEquals("Sequence |AACCGTC| does not exist", it.remove("AACCGTC"));
        assertFuzzyEquals(null, it.search("AGC"));
        assertFuzzyEquals(null, it.search("AGT"));
        assertFuzzyEquals(null, it.search("AGG"));
        assertFuzzyEquals(null, it.search("AGA"));
        assertFuzzyEquals(null, it.search("AGC$"));
        

// assertFuzzyEquals(
// "Sequence |ACGT| already exists",
// it.insert("ACGT"));
    }
}
