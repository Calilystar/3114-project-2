import student.TestCase;

/**
 * Testing for LeafNode
 * 
 * @author Jocelyn Chu (jocelynchu), Callie Chiang (ccsea)
 * @version 2026.03.16
 */
public class LeafNodeTest extends TestCase {
    // ~ Fields ................................................................
    private LeafNode leaf;

    // ~ Constructors ..........................................................
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        leaf = new LeafNode("ACG");
    }


    // ~Public Methods ........................................................
    /**
     * Test constructor and getSequence
     */
    public void testGetSequence() {
        assertEquals("ACG", leaf.getSequence());
    }


    /**
     * Test insert for same sequence
     */
    public void testInsertSameSequence() {
        DNANode res = leaf.insert("ACG", 0);

        assertTrue(res instanceof LeafNode);
        assertTrue(res == leaf);
        assertEquals("ACG", ((LeafNode)res).getSequence());
    }


    /**
     * Test insert for different sequnece
     */
    public void testInsertDiffSequence() {
        DNANode res = leaf.insert("ACT", 0);

        assertTrue(res instanceof InternalNode);

    }


    /**
     * Test insert with different sequence, deeper level
     */
    public void testInsertDiffSequence2() {
        DNANode res = leaf.insert("ACT", 2);

        assertTrue(res instanceof InternalNode);

    }


    /**
     * Test remove when sequence matches stored value
     */
    public void testRemoveMatch() {
        DNANode res = leaf.remove("ACG", 0);

        assertTrue(res == FlyweightNode.getInstanceOf());

    }


    /**
     * Test remove when sequence doesn't match
     */
    public void testRemoveNoMatch() {
        DNANode res = leaf.remove("ATT", 0);

        assertTrue(res == leaf);
        assertEquals("ACG", ((LeafNode)res).getSequence());

    }


    /**
     * Test search when sequence matches
     */
    public void testSearchMatch() {
        String res = leaf.search("ACG", 0);

        assertEquals("ACG", res);
    }


    /**
     * Test serach when sequence doesn't match
     */
    public void testSearchNoMatch() {
        String res = leaf.search("AAA", 0);

        assertNull(res);
    }
}
