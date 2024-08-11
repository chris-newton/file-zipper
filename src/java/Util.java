import java.util.HashMap;

public final class Util {
    // returns a table of characters in the text and the number of occurences of each
    public static HashMap<Byte, Integer> frequencies(byte[] textBytes) {
        HashMap<Byte, Integer> freqs = new HashMap<Byte, Integer>();

        for (int i = 0; i < textBytes.length; i++) {
            byte key = textBytes[i];
            if (freqs.containsKey(key)) {
                freqs.put(key, freqs.get(key) + 1);
            } else {
                freqs.put(key, 1);
            }
        }
        return freqs;
    }
}
