package ru.nsu.kolodina.strings;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
=======
import java.io.*;
>>>>>>> efba6b9458319ba35da77860d01ce5ec806f392c
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

<<<<<<< HEAD
/**
 * implementation of Boyer-Moore algorithm using badCharacterHeuristic and goodSuffixHeuristic.
 */
public class BoyerMoore {

    /**
     * bad character Heuristic.
     * find the rightmost occurences of all characters in pattern
     *
     * @param pattern we want to match
     * @return hashMap that maps letters to their indexes
     */
=======
public class BoyerMoore {
>>>>>>> efba6b9458319ba35da77860d01ce5ec806f392c
    public static HashMap<Character, Integer> badCharacterHeuristic(String pattern) {
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (!charMap.containsKey(pattern.charAt(i))) {
                charMap.put(pattern.charAt(i), i);
            } else {
                charMap.replace(pattern.charAt(i), i);
            }
        }
        return charMap;
    }

<<<<<<< HEAD
    /**
     * good suffix heuristic.
     * matching shifts for prefixes and suffixes
     *
     * @param pattern we want to match
     * @return int array of shifts
     */
=======
>>>>>>> efba6b9458319ba35da77860d01ce5ec806f392c
    public int[] goodSuffixHeuristic(String pattern) {
        int[] shifts = new int[pattern.length() + 1];
        int[] borderPositions = new int[pattern.length() + 1];

        findShiftsAndBorders(shifts, borderPositions, pattern);
        setShiftsForPrefix(shifts, borderPositions, pattern);

        return shifts;
    }

<<<<<<< HEAD
    /**
     * find shifts and borderPositions of pattern for good character heuristic.
     *
     * @param shifts          int arr
     * @param borderPositions int arr
     * @param pattern         we want to match
     */
=======
>>>>>>> efba6b9458319ba35da77860d01ce5ec806f392c
    private void findShiftsAndBorders(int[] shifts, int[] borderPositions, String pattern) {
        int i = pattern.length();
        int j = pattern.length() + 1;

        borderPositions[i] = j;

        while (i > 0) {
            while (j <= pattern.length() && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (shifts[j] == 0) {
                    shifts[j] = j - i;
                }
                j = borderPositions[j];
            }
            i--;
            j--;
            borderPositions[i] = j;
        }
    }

<<<<<<< HEAD
    /**
     * setting shift for prefix.
     *
     * @param shifts          int arr
     * @param borderPositions int arr
     * @param pattern         string we want to match
     */
=======
>>>>>>> efba6b9458319ba35da77860d01ce5ec806f392c
    private void setShiftsForPrefix(int[] shifts, int[] borderPositions, String pattern) {
        int prefixBorder = borderPositions[0];

        for (int i = 0; i <= pattern.length(); i++) {
            if (shifts[i] == 0) {
                shifts[i] = prefixBorder;
            }
            if (i == prefixBorder) {
                prefixBorder = borderPositions[prefixBorder];
            }
        }
    }

<<<<<<< HEAD
    /**
     * find all entries of pattern in string.
     * each time we choose the max shift returned by badCharacter and good Suffix heuristic
     *
     * @param string  we find pattern in
     * @param pattern our pattern
     * @param index   the offset of file we are reading
     * @return list of indexes of all entries of pattern
     */
=======
>>>>>>> efba6b9458319ba35da77860d01ce5ec806f392c
    public List<Integer> search(String string, String pattern, int index) {
        List<Integer> result = new ArrayList<>();
        int[] shiftsGoodSuffix = goodSuffixHeuristic(pattern);
        HashMap<Character, Integer> badCharMap = badCharacterHeuristic(pattern);
        int stringLength = string.length();
        int patternLength = pattern.length();
        int i = 0; // pointer of text
        while (i <= stringLength - patternLength) {
            int j = patternLength - 1; // pointer of pattern. move from the right.
            while (j >= 0 && pattern.charAt(j) == string.charAt(i + j)) {
                j--;
            }
            if (j < 0) { // the whole pattern matched. move past it
                result.add(i + index);
                i += shiftsGoodSuffix[0];
            } else {
                int badCharShift = j - badCharMap.getOrDefault(string.charAt(i + j), -1);
                int goodSuffixShift = shiftsGoodSuffix[j + 1];
                i += Math.max(badCharShift, goodSuffixShift);
            }
        }
        return result;
    }

<<<<<<< HEAD
    /**
     * read file and find pattern in its text.
     *
     * @param filePath path to file
     * @param pat      pattern to find
     * @return list of indexes of all entries of pattern
     */
=======
>>>>>>> efba6b9458319ba35da77860d01ce5ec806f392c
    public List<Integer> findInFile(String filePath, String pat) {
        List<Integer> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int batchSize = 50000;
        int maxSize = 50000;
        String pattern = new String(pat.getBytes(), StandardCharsets.UTF_8);
        int patternLen = pattern.length();
        java.nio.charset.Charset charset = StandardCharsets.UTF_8;
        java.nio.charset.Charset charset2 = Charset.defaultCharset();
        try (FileInputStream stream = new FileInputStream(filePath)) {
            InputStreamReader reader0 = new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(reader0);
            char[] buffer = new char[batchSize];
            int allCharsRead = 0;
            int charsRead;
            int numBatch = 0;
            int index = 0;
            while ((charsRead = reader.read(buffer)) != -1) {
                allCharsRead += charsRead;
                sb.append(buffer, 0, charsRead);
                if (sb.length() > maxSize) {
                    String txt = sb.toString();
                    if (numBatch > 0) {
                        index = (numBatch + 1) * maxSize - patternLen;
                    } else {
                        index = 0;
                    }
                    numBatch++;
                    res.addAll(search(txt, pattern, index));
                    int start = Math.max(0, sb.length() - (patternLen));
                    sb.delete(0, start);
                }
            }

            if (!sb.isEmpty()) {
                String txt = sb.toString();
                res.addAll(search(txt, pattern, index));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
