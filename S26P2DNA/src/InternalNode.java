
public class InternalNode implements DNANode{
    public DNANode[] children;
    
    
    /** 
     * children[i] = A G C T $ in that order, but currently should be pointing to flyweight (placeholder)
     * until insert is called.
     * @param placeholder
     */
    public InternalNode(DNANode placeholder) {
        children = new DNANode[5];
        
        for(int i = 0 ; i < 5 ; i++) {
            children[i] = FlyweightNode.getInstanceOf();
        }
    }

    @Override
    public DNANode insert(String sequence, int level) {
        int index;
        if(level >= sequence.length()) {
            index = 4;
        }
        index = this.getCharIndex(sequence.charAt(level));
        children[index] = children[index].insert(sequence, level + 1);
        return this;
    }

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
        
        for(int i = 0; i < children.length - 1 ; i ++) {
            if(children[i] instanceof FlyweightNode) {
                count += 1;
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
    
    public int getCharIndex(char x) {
        switch (x) {
            case 'A':
                return 0;
            case 'G':
                return 1;
            case 'C':
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
