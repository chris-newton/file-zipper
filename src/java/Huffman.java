import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    private ByteNode root;
    private HashMap<Byte, String> encodings;

    Huffman() {
        this.root = null;
        this.encodings = new HashMap<Byte, String>();
    }

    // builds the Huffman tree on the input text, returns the encoding 
    public byte[] encode(byte[] inputBytes) {
        buildTree(inputBytes);

        byte[] encodedBytes = zipBytesWithCodes(inputBytes, encodings);
        return encodedBytes;
    }

    private static byte[] zipBytesWithCodes(byte[] bytes, Map<Byte, String> huffCodes) {
        StringBuilder strBuilder = new StringBuilder();
        for (byte b : bytes)
            strBuilder.append(huffCodes.get(b));

        int length=(strBuilder.length()+7)/8;
        byte[] huffCodeBytes = new byte[length];
        int idx = 0;
        for (int i = 0; i < strBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > strBuilder.length())
                strByte = strBuilder.substring(i);
            else strByte = strBuilder.substring(i, i + 8);
            huffCodeBytes[idx] = (byte) Integer.parseInt(strByte, 2);
            idx++;
        }
        return huffCodeBytes;
    }
    
    // currently this method only works immediately after calling encode 
    // with the same text
    public String decode(byte[] encodedBytes) {
        System.out.println("# of Encodings: " + encodings.size());
        ByteNode node = this.root;
        StringBuilder decodedText = new StringBuilder();

        for (int i = 0; i < encodedBytes.length; i++) {
            // System.out.println(encodedText[i]);

            if (encodedBytes[i] == '0') {
                node = node.left;
            } else {
                node = node.right;
            }
                
            if (node.isLeaf()) {
                decodedText.append(node.character);
                node = this.root;
            }
        }
        return decodedText.toString();
    }

    public HashMap<Byte, String> getEncodings() {
        return this.encodings;
    }

    public String toString() {
        return this.root.toString();
    }

    private void buildTree(byte[] textBytes) {
        // STEP 1: Count frequencies of characters in source text (runtime = O(n))
        HashMap<Byte, Integer> freqs = Util.frequencies(textBytes);
        
        // STEP 2: Build min priority queue of nodes for each char (O(n))
        PriorityQueue<ByteNode> pq = new PriorityQueue<ByteNode>();
        for (byte key : freqs.keySet()) {
            ByteNode node = new ByteNode(key, freqs.get(key));
            pq.add(node);
        }
        
        // STEP 3: Build the tree (O(n))
        while (pq.size() > 1) {
            ByteNode left = pq.poll();
            ByteNode right = pq.poll();

            // add new internal node that sums its children's frequencies
            ByteNode node = new ByteNode(left.frequency + right.frequency);
            node.left = left;
            node.right = right;
            pq.add(node);
        }

        this.root = pq.poll();
        setEncodings();
    }

    private void setEncodings() {
        setEncodingsAux(this.root, "");
    }

    private void setEncodingsAux(ByteNode node, String code) {
        if (node.isLeaf()) {
            this.encodings.put(node.character, code);
        } else {
            setEncodingsAux(node.left, code + "0");
            setEncodingsAux(node.right, code + "1");
        }
    }
}