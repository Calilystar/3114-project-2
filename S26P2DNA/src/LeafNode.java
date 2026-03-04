
public class LeafNode implements DNANode {

    private String stored;

    public LeafNode(String sequence) {
        stored = sequence;
    }


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


    @Override
    public DNANode remove(String sequence, int level) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public String search(String sequence, int level) {
        if (stored.equals(sequence)) {
            return sequence;
        }
        return null;
    }

}
