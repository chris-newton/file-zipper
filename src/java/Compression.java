import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Compression {
    public static void compress(File src, File dst) {
        try {
            // open and read src into bytes array
            FileInputStream inStream = new FileInputStream(src);
            byte[] inputBytes = new byte[inStream.available()];
            inStream.read(inputBytes);
            
            // print original message (in bytes)
            System.out.println("Original message: ");
            StringBuilder orig = new StringBuilder();
            for (byte b : inputBytes) {
                orig.append(b + " ");
            }

            // build Huffman tree and encode message
            Huffman h = new Huffman();
            byte[] encodedBytes = h.encode(inputBytes);

            // write encoded message and encoding table to specified directory
            OutputStream outStream = new FileOutputStream(dst.getPath() + "\\" + src.getName().substring(0, src.getName().length()-4) + "_compressed.txt");
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            objectOutStream.writeObject(encodedBytes);
            objectOutStream.writeObject(h.getEncodings());

            System.out.println("\ninput length:" + inputBytes.length);
            System.out.println("encoded length:" + encodedBytes.length);

            inStream.close();
            objectOutStream.close();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decompress(File src, File dst) {
        try {
            System.out.println(src);
            FileInputStream inStream = new FileInputStream(src);
            
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);
            byte[] huffmanBytes = (byte[]) objectInStream.readObject();
            Map<Byte, String> huffmanCodes =
                    (Map<Byte, String>) objectInStream.readObject();

            System.out.println("huffman bytes: " + huffmanBytes.length);
            System.out.println("huffman codes: " + huffmanCodes);

            int[] ascii = new int[huffmanBytes.length];
            
            for (int i=0; i<huffmanBytes.length; i++) {
            	
            	for (Byte b : huffmanCodes.keySet()) {
            		huffmanCodes.get(huffmanCodes.get(b));
            		if (huffmanCodes.get(b).equals(huffmanBytes[i])) {
            			System.out.print(Integer.valueOf(huffmanBytes[i]));
            		}
            	}
            }
            
            
            byte[] bytes = decomp(huffmanCodes, huffmanBytes);

            try {
                String fileName = src.getName();
                dst = new File(dst.getAbsolutePath() + "\\" + fileName.substring(0, fileName.length()-4) + "_decompressed.txt");
                System.out.println("DST: " + dst);

                if (dst.createNewFile()) {
                    System.out.println("File created: " + dst.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            OutputStream outStream = new FileOutputStream(dst);
            outStream.write(bytes);
            inStream.close();
            objectInStream.close();
            outStream.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static byte[] decomp(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder sb1 = new StringBuilder();
        for (int i=0; i<huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            sb1.append(convertbyteInBit(!flag, b));
        }
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        java.util.List<Byte> list = new java.util.ArrayList<>();
        for (int i = 0; i < sb1.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = sb1.substring(i, i + count);
                b = map.get(key);
                if (b == null) count++;
                else flag = false;
            }
            list.add(b);
            i += count;
        }
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++)
            b[i] = list.get(i);
        return b;
    }

    private static String convertbyteInBit(boolean flag, byte b) {
        int byte0 = b;
        if (flag) byte0 |= 256;
        String str0 = Integer.toBinaryString(byte0);
        if (flag || byte0 < 0)
            return str0.substring(str0.length() - 8);
        else return str0;
    }
}
