// -------------------------------------------------------------------------
/**
 * Represents a shared empty node in the DNA tree/
 * 
 * @author Callie Chiang (ccsea), Jocelyn Chu (jocelynchu)
 * @version 2026.03.04
 */
public class FlyweightNode implements DNANode {
    private static final FlyweightNode INSTANCE = new FlyweightNode();

    /**
     * Private consstructor.
     */
    private FlyweightNode() {

    }


    /**
     * insert a sequence into an empty node by creating a new LeafNode.
     * 
     * @param sequence
     *            The sequence being inserted.
     * @param level
     *            The current level in the tree.
     * @return the new LeafNode containing the inserted sequence.
     */
    @Override
    public DNANode insert(String sequence, int level) {
        return new LeafNode(sequence);
    }


    /**
     * remove empty node does nothing.
     * 
     * @param sequence
     *            The sequence being removed.
     * @param level
     *            the current level in the tree.
     * @return the same flyweight node.
     */
    @Override
    public DNANode remove(String sequence, int level) {
        return this;
    }


    /**
     * search empty node has no existing sequence.
     * 
     * @param sequence
     *            The sequence being removed.
     * @param level
     *            The current level in the tree.
     * @return null, because empty node stores no sequence.
     */
    @Override
    public String search(String sequence, int level) {
        return null;
    }


    /**
     * returns the single shared flyweight instance.
     * 
     * @return The shared FlyweightNode instance.
     */
    public static DNANode getInstanceOf() {
        return INSTANCE;
    }

}
