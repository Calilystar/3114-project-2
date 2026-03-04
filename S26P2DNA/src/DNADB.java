// -------------------------------------------------------------------------

/**
 * The database implementation for this project. This manages the commands for
 * the DNA tree.
 *
 * @author CS3114/5040 Staff
 * @version Spring 2026
 *
 */
public class DNADB implements DNA {
    // root of DNA tree
    private DNANode root;

    // ----------------------------------------------------------
    /**
     * Create a new DNADB object.
     */
    public DNADB() {
        root = FlyweightNode.getInstanceOf();
    }


    // ----------------------------------------------------------
    /**
     * Insert a DNA string into the database
     * 
     * @param sequence
     *            The sequence to insert
     * @return The outcomes message string
     */
    public String insert(String sequence) {
        if (sequence == null) {
            return "Bad input: Sequence may not be null\r\n";
        }

        else if (sequence.equals("")) {
            return "Bad input: Sequence may not be empty\r\n";
        }

        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);

            if (c != 'A' && c != 'G' && c != 'C' && c != 'T') {
                return "Bad Input Sequence |" + sequence + "|\r\n";
            }

        }

        DNANode oldRoot = root;

        root = root.insert(sequence, 0);

        if (oldRoot == root) {
            return "Sequence |" + sequence + "| already exists";
        }

        return "Sequence |" + sequence + "| inserted";
    }


    // ----------------------------------------------------------
    /**
     * Remove a DNA string into the database
     * 
     * @param sequence
     *            The sequence to remove
     * @return The outcomes message string
     */
    public String remove(String sequence) {
        if (sequence == null) {
            return "Bad input: Sequence may not be null\r\n";
        }

        else if (sequence.equals("")) {
            return "Bad input: Sequence may not be empty\r\n";
        }

        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);

            if (c != 'A' && c != 'G' && c != 'C' && c != 'T') {
                return "Bad Input Sequence |" + sequence + "|\r\n";
            }

        }

        DNANode newRoot = root.remove(sequence, 0);

        if (newRoot == root) {
            return "Sequence |" + sequence + "| does not exist";
        }

        root = newRoot;

        return "Sequence |" + sequence + "| removed";
    }


    // ----------------------------------------------------------
    /**
     * Print the tree
     * 
     * @return the print string
     */
    public String print() {
        StringBuilder sb = new StringBuilder();

        sb.append("tree dump:\r\n");

        printNode(root, 0, sb);

        return sb.toString().trim();

    }


    // ----------------------------------------------------------
    /**
     * Print the lengths
     * 
     * @return the print string
     */
    public String printLengths() {
        StringBuilder sb = new StringBuilder();

        sb.append("tree dump with lengths:\r\n");

        printNodeLength(root, 0, sb);

        return sb.toString().trim();
    }


    // ----------------------------------------------------------
    /**
     * Print the stats
     * 
     * @return the print string
     */
    public String printStats() {
        StringBuilder sb = new StringBuilder();

        sb.append("tree dump with stats:\r\n");

        printNodeStats(root, 0, sb);

        return sb.toString().trim();
    }


    // ----------------------------------------------------------
    /**
     * Search for a given string
     * 
     * @param sequence
     *            The sequence to search for
     * @return the print string
     */
    public String search(String sequence) {
        if (sequence == null) {
            return "Bad input: Sequence may not be null\r\n";

        }

        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);

            if (c != 'A' && c != 'G' && c != 'C' && c != 'T' && c != '$') {
                return "Bad input sequence |" + sequence + "|\r\n";
            }

            if (c == '$' && i != sequence.length() - 1) {
                return "Bad input sequence |" + sequence + "|\r\n";
            }
        }

        String str = sequence.replace("$", "");
        boolean exactMatch = sequence.endsWith("$");

        int[] visited = { 0 };
        StringBuilder sb = new StringBuilder();

        this.searchHelper(root, str, 0, exactMatch, visited, sb);

        String res = sb.toString().trim();

        if (res.isEmpty()) {
            return "No sequence found\r\n# of nodes visited: " + visited[0];
        }

        return res + "\r\n# of nodes visited: " + visited[0];
    }


    private void searchHelper(
        DNANode node,
        String str,
        int depth,
        boolean match,
        int[] visited,
        StringBuilder sb) {
        visited[0]++;

        if (node == FlyweightNode.getInstanceOf()) {
            return;
        }
        else if (node instanceof LeafNode) {
            LeafNode leaf = (LeafNode)node;
            String leafSeq = leaf.getSequence();

            if (match) {
                if (leafSeq.equals(str)) {
                    sb.append(leafSeq).append("\r\n");
                }
            }
            else {
                if (leafSeq.startsWith(str)) {
                    sb.append(leafSeq).append("\r\n");
                }
            }
        }
        else {
            InternalNode internal = (InternalNode)node;

            if (depth < str.length()) {
                char c = str.charAt(depth);
                int childIndex = internal.getCharIndex(c);
                searchHelper(internal.children[childIndex], str, depth + 1,
                    match, visited, sb);
            }
            else {
                for (int i = 0; i < internal.children.length; i++) {
                    searchHelper(internal.children[i], str, depth + 1, match,
                        visited, sb);
                }
            }
        }

    }

    // ----------------------------------------------------------
    /*
     * Helper methods
     */


    /**
     * help to print the tree structure
     * 
     * @param node
     *            The node being printed
     * @param depth
     *            The level of the tree
     * @param sb
     *            The stringbuilder storing output
     */
    private void printNode(DNANode node, int depth, StringBuilder sb) {
        for (int i = 0; i < depth; i++) {
            sb.append(" ");
        }

        // flyweight/empty node
        if (node == FlyweightNode.getInstanceOf()) {
            sb.append("E\r\n");
        }

        // leaf node
        else if (node instanceof LeafNode) {
            LeafNode leaf = (LeafNode)node;

            sb.append(leaf.getSequence());
            sb.append("\r\n");
        }

        else {
            InternalNode internal = (InternalNode)node;

            sb.append("I\r\n");

            for (int i = 0; i < internal.children.length; i++) {
                printNode(internal.children[i], depth + 1, sb);
            }
        }
    }


    /**
     * help to print lengths
     * 
     * @param node
     *            The current node
     * @param depth
     *            The currnet level in the tree
     * @param sb
     *            The stringbuilder storing output
     */
    private void printNodeLength(DNANode node, int depth, StringBuilder sb) {
        for (int i = 0; i < depth; i++) {
            sb.append(" ");
        }

        // flyweight/empty node
        if (node == FlyweightNode.getInstanceOf()) {
            sb.append("E\r\n");
        }

        else if (node instanceof LeafNode) {
            LeafNode leaf = (LeafNode)node;

            String seq = leaf.getSequence();

            sb.append(seq).append(" ").append(seq.length()).append("\r\n");

        }

        else {
            InternalNode internal = (InternalNode)node;

            sb.append("I\r\n");

            for (int i = 0; i < internal.children.length; i++) {
                printNodeLength(internal.children[i], depth + 1, sb);
            }
        }
    }


    /**
     * help compute stats for DNA
     * 
     * @param node
     *            The current node
     * @param depth
     *            The current level in tree
     * @param sb
     *            The stringbuilder storing output
     */
    private void printNodeStats(DNANode node, int depth, StringBuilder sb) {
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }

        // flyweight/empty node
        if (node == FlyweightNode.getInstanceOf()) {
            sb.append("E\r\n");
        }

        else if (node instanceof LeafNode) {
            LeafNode leaf = (LeafNode)node;
            String seq = leaf.getSequence();

            int a = 0;
            int c = 0;
            int g = 0;
            int t = 0;

            for (int i = 0; i < seq.length(); i++) {
                char chara = seq.charAt(i);
                if (chara == 'A') {
                    a++;
                }
                else if (chara == 'C') {
                    c++;
                }
                else if (chara == 'G') {
                    g++;
                }
                else if (chara == 'T') {
                    t++;
                }
            }

            int len = seq.length();

            sb.append(seq).append(" A:").append(String.format("%.2f", 100.0 * a
                / len)).append(" C:").append(String.format("%.2f", 100.0 * c
                    / len)).append(" G:").append(String.format("%.2f", 100.0 * g
                        / len)).append(" T:").append(String.format("%.2f", 100.0
                            * t / len)).append("\r\n");
        }

        else {
            InternalNode intern = (InternalNode)node;
            sb.append("I\r\n");

            for (int i = 0; i < intern.children.length; i++) {
                printNodeStats(intern.children[i], depth + 1, sb);
            }
        }

    }
}
