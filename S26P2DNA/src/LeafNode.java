
/**
 * represents a terminal node in the DNA tree that stores a complete DNA
 * sequence.
 * 
 * @author Callie Chiang (ccsea), Jocelyn Chu (jocelynchu)
 * @version 2026.03.04
 */
public class LeafNode implements DNANode {
    // stored DNA sequence
    private String stored;

    /**
     * creates a leaf node containing a DNA sequence.
     * 
     * @param sequence
     *            The DNA sequence stored
     */
    public LeafNode(String sequence) {
        stored = sequence;
    }


    /**
     * inserts a sequence into the leaf node.
     * 
     * @param sequence
     *            The sequence to insert
     * @param level
     *            The current level in the tree
     * @return The node unchanged if sequence already exists, the leaf split
     *         into new internal node with both sequences if not.
     */
    @Override
    public DNANode insert(String sequence, int level) {

        if (stored.equals(sequence)) {
            return this;
        }
        InternalNode newNode = new InternalNode(FlyweightNode.getInstanceOf());
        newNode.insert(this.stored, level);
        newNode.insert(sequence, level);
        return newNode;

    }


    /**
     * removes the stored sequence if it matches the target sequence.
     * 
     * @param sequence
     *            The seuqence to remove.
     * @param level
     *            The current level in the tree.
     * @return a flyweight node if removed, the same leaf node if not.
     */
    @Override
    public DNANode remove(String sequence, int level) {
        if (stored.equals(sequence)) {
            return FlyweightNode.getInstanceOf();
        }
        return this;
    }


    /**
     * search for a sequence in the leaf node.
     * 
     * @param sequence
     *            The sequence to be searched.
     * @param level
     *            The current level in the tree.
     * @return The sequence if found, null if not.
     */
    @Override
    public String search(String sequence, int level) {
        if (stored.equals(sequence)) {
            return stored;
        }
        return null;
    }


    /**
     * returns the stored sequence.
     * 
     * @return The DNA sequence contained in this leaf node.
     */
    public String getSequence() {
        return stored;
    }
}
