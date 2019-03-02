package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramFinder {

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();

        if(args.length < 2) {
            throw new RuntimeException("Not enough arguments: [path] [word]");
        }

        String path = args[0];
        String word = args[1];

        List<String> anagrams = findAnagramsFromFile(word, path);

        printResult(startTime, anagrams);
    }

    private static List<String> findAnagramsFromFile(String target, String path) throws IOException {
        List<String> words = new ArrayList<>();
        FastReader reader = new FastReader(path);
        Map<Byte, Integer> charMap = createCharMap(target);
        int length = target.getBytes(WordObject.charset).length;
        WordObject word;
        while ((word = reader.readLine()) != null) {
            if (word.getLength() == length && word.isAnagram(charMap)) {
                words.add(word.createString());
            }
        }
        reader.close();
        return words;
    }

    private static Map<Byte, Integer> createCharMap(String target) {
        Map<Byte, Integer> map = new HashMap<>();
        byte[] charArray = target.getBytes(WordObject.charset);
        for (byte curByte : charArray) {
            Integer count = map.get(curByte);
            map.put(curByte, count == null ? 1 : count + 1);
        }
        return map;
    }

    private static void printResult(long startTime, List<String> result) {
        double stop = (System.nanoTime() - startTime) / 1000d;
        result.add(0, Double.toString(stop));
        System.out.println(String.join(",", result));
    }

}
