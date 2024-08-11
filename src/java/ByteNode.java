public class ByteNode implements Comparable<ByteNode> {
    Byte character = null; // null character means node is non-leaf (internal)
    int frequency;
    ByteNode left;
    ByteNode right;
    
    ByteNode(int frequency) {
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    ByteNode(byte character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public boolean isLeaf() {
        if (this.character == null) {
            return false;
        } else {
            return true;
        }
    }

    public int compareTo(ByteNode other) {
        int frequencyCompare = Integer.compare(this.frequency, other.frequency);

        if (frequencyCompare == 0) {
            return 1;
        }

        return frequencyCompare;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append(this.character);
        
        if (this.left != null) {
            str.append(this.left.toString());
        }

        if (this.right != null) {
            str.append(this.right.toString());
        }

        return str.toString();
    }
}