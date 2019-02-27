package main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    private static void printResult(long startTime, List<String> result) {
        double stop = (System.nanoTime() - startTime) / 1000d;
        result.add(0, Double.toString(stop));
        System.out.println(String.join(",", result));
    }

    private static boolean isAnagram(String target, String element) {
        Map<Character, Integer> characterMap = createCharacterMap(target);
        for (int i = 0; i < element.length(); i++) {
            Character curCharacter = element.charAt(i);
            Integer charCount = characterMap.get(curCharacter);
            if (charCount == null || charCount == 0) {
                return false;
            } else {
                characterMap.put(curCharacter, charCount - 1);
            }
        }
        return true;
    }

    private static Map<Character, Integer> createCharacterMap(String target) {
        Map<Character, Integer> characterMap = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            Character character = target.charAt(i);
            Integer curValue = characterMap.get(character);
            characterMap.put(character, curValue == null ? 1 : curValue+1);
        }
        return characterMap;
    }

    public static List<String> findAnagramsFromFile(String target, String path) throws IOException {
        List<String> words = new ArrayList<>();
        File file = new File(path);
        InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1);
        BufferedReader reader = new BufferedReader(streamReader);
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.length() == target.length() && isAnagram(target, line))
                words.add(line);
        }
        return words;
    }
}
