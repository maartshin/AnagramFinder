package main;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class WordObject {

    public static Charset charset = StandardCharsets.ISO_8859_1;
    byte[] charBuffer;
    int length;

    public WordObject(byte[] buf, int length) {
        this.charBuffer = buf;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String createString() {
        return new String(charBuffer, 0, length, charset);
    }

    public boolean isAnagram(Map<Byte, Integer> target) {
        Map<Byte, Integer> mapCopy = new HashMap<>();
        for (int i = 0; i < length; i++) {
            Integer curCount = target.get(charBuffer[i]);
            if (curCount == null) {
                return false;
            }
            curCount = mapCopy.get(charBuffer[i]) == null ? curCount : mapCopy.get(charBuffer[i]);
            if (curCount == 0) {
                return false;
            }
            mapCopy.put(charBuffer[i], curCount - 1);
        }
        return true;
    }

}
