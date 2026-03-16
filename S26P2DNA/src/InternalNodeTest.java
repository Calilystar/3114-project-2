import student.TestCase;

/**
 * Testing for InternalNode
 * 
 * @author Jocelyn Chu (jocelynchu), Callie Chiang (ccsea)
 * @version 2026.03.16
 * 
 */
public class InternalNodeTest extends TestCase {
    // ~ Fields ................................................................
    private InternalNode node;
    private DNANode fly;

    // ~ Constructors ..........................................................
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        fly = FlyweightNode.getInstanceOf();
        node = new InternalNode(fly);
    }


    // ~Public Methods ........................................................
    /**
     * Test constructor
     */
    public void testConstructor() {
        for (int i = 0; i < 5; i++) {
            assertTrue(node.getChildren()[i] == fly);
        }
    }


    /**
     * Test index mappings
     */
    public void testIndexMappings() {
        assertEquals(0, node.getCharIndex('A'));
        assertEquals(1, node.getCharIndex('C'));
        assertEquals(2, node.getCharIndex('G'));
        assertEquals(3, node.getCharIndex('T'));
        assertEquals(4, node.getCharIndex('$'));
    }


    /**
     * Test invalid indexes
     */
    public void testInvalidIndex() {
        assertEquals(-1, node.getCharIndex('X'));
    }


    /**
     * Test inserting at normal levels
     */
    public void testInsertNormalLevel() {
        node.insert("A", 0);

        DNANode[] children = node.getChildren();

        assertTrue(children[0] instanceof LeafNode);
        assertEquals("A", ((LeafNode)children[0]).getSequence());
    }


    /**
     * Test inserting at $ level
     */
    public void testInsertDollarLevel() {
        node.insert("A", 1);

        DNANode[] children = node.getChildren();

        assertTrue(children[4] instanceof LeafNode);
        assertEquals("A", ((LeafNode)children[4]).getSequence());
    }


    /**
     * Test search for normal branch
     */
    public void testSearchNormal() {
        node.insert("A", 0);

        String res = node.search("A", 0);

        assertEquals("A", res);
    }


    /**
     * Test search through $ branch
     */
    public void testSearchDollar() {
        node.insert("A", 1);

        String res = node.search("A", 1);

        assertEquals("A", res);
    }


    /**
     * Test remove when node becomes flyweight
     */
    public void testRemoveToFlyweight() {
        node.insert("A", 0);

        DNANode res = node.remove("A", 0);

        assertTrue(res == fly);
    }


    /**
     * Test remove when node becomes leaf
     */
    public void testRemoveToLeaf() {
        node.insert("A", 0);

        DNANode res = node.remove("A", 0);

        assertTrue(res == fly);
    }


    /**
     * Test remove when multiple children remain
     */
    public void testRemoveMultiple() {
        node.insert("A", 0);
        node.insert("C", 0);

        DNANode res = node.remove("C", 0);

        assertTrue(res instanceof LeafNode);
        assertEquals("A", ((LeafNode)res).getSequence());
    }


    /**
     * Test remove using $ branch
     */
    public void testRemoveDollar() {
        node.insert("A", 1);

        DNANode res = node.remove("A", 1);

        assertTrue(res == fly);
    }
}
