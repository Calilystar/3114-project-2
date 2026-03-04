
public class FlyweightNode implements DNANode{
    private static final FlyweightNode INSTANCE = new FlyweightNode();

    @Override
    public DNANode insert(String sequence, int level) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DNANode remove(String sequence, int level) {
        // TODO Auto-generated method stub
        return null;
    }

    

    @Override
    public DNANode search(String sequence, int level) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static DNANode getInstanceOf() {
        return INSTANCE;
    }

    

}
