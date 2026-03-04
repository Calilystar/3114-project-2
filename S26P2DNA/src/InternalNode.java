
/**
 * represents a branching node in the DNA tree that contains references to
 * children corresponding to A, G, C, T, and $.
 * 
 * @author Callie Chiang (ccsea), Jocelyn Chu (jocelynchu)
 * @version 2026.03.03
 */
public class InternalNode implements DNANode {
    public DNANode[] children;

    /**
     * children[i] = A C G T $ in that order, but currently should be pointing
     * to flyweight (placeholder)
     * until insert is called.
     * 
     * @param placeholder
     *            The flyweight node used to represent empty children
     */
    public InternalNode(DNANode placeholder) {
        children = new DNANode[5];

        for (int i = 0; i < 5; i++) {
            children[i] = FlyweightNode.getInstanceOf();
        }
    }


    /**
     * inserts the sequence recursively
     * 
     * @param sequence
     *            The sequence to insert.
     * @param level
     *            The current level of the tree.
     * @return The updated node after inserting.
     */
    @Override
    public DNANode insert(String sequence, int level) {
        int index;
        if (level >= sequence.length()) {
            index = 4;
        }
        else {
            index = getCharIndex(sequence.charAt(level));
        }

        children[index] = children[index].insert(sequence, level + 1);
        return this;
    }


    /**
     * removes a sequence recursively
     * 
     * @param sequence
     *            The sequence to remove.
     * @param level
     *            The current level of the tree.
     * @return The updated node after removing.
     */
    @Override
    public DNANode remove(String sequence, int level) {
        int index;
        if (level < sequence.length()) {
            index = this.getCharIndex(sequence.charAt(level));
        }
        else {
            index = 4;
        }
        children[index] = children[index].remove(sequence, level + 1);

        int count = 0;
        DNANode remainder = null;

        for (int i = 0; i < children.length; i++) {
            if (children[i] instanceof FlyweightNode) {
                count++;
            }
            else {
                remainder = children[i];
            }
        }

        if (count == 5) {
            return FlyweightNode.getInstanceOf();
        }
        if (count == 4) {
            if (remainder instanceof LeafNode) {
                return remainder;
            }
        }
        return this;
    }


    /**
     * searches the appropriate branch recursively.
     * 
     * @param sequence
     *            The sequence to search
     * @param level
     *            The current level in the tree.
     * @return The result of the search.
     */
    @Override
    public String search(String sequence, int level) {
        int index;
        if (level < sequence.length()) {
            index = this.getCharIndex(sequence.charAt(level));
        }
        else {
            index = 4;
        }
        return children[index].search(sequence, level + 1);
    }


    /**
     * converts DNA character into corresponding child index.
     * 
     * @param x
     *            The DNA character
     * @return The index representation
     */
    public int getCharIndex(char x) {
        switch (x) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            case '$':
                return 4;

            default:
                return -1;
        }
    }

}
