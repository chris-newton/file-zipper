public class Node implements Comparable<Node> {
    char character;
    int frequency;
    Node left;
    Node right;
    
    Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public boolean isLeaf() {
        if (this.character == '$') {
            return false;
        } else {
            return true;
        }
    }

    public int compareTo(Node other) {
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