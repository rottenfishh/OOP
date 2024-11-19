package ru.nsu.kolodina.strings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static Map<Character, Integer> badCharacterHeuristic(String pattern) {
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

    /**
     * good suffix heuristic.
     * matching shifts for prefixes and suffixes
     *
     * @param pattern we want to match
     * @return int array of shifts
     */
    public int[] goodSuffixHeuristic(String pattern) {
        int[] shifts = new int[pattern.length() + 1];
        int[] borderPositions = new int[pattern.length() + 1];

        findShiftsAndBorders(shifts, borderPositions, pattern);
        setShiftsForPrefix(shifts, borderPositions, pattern);

        return shifts;
    }

    /**
     * find shifts and borderPositions of pattern for good character heuristic.
     *
     * @param shifts          int arr
     * @param borderPositions int arr
     * @param pattern         we want to match
     */
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

    /**
     * setting shift for prefix.
     *
     * @param shifts          int arr
     * @param borderPositions int arr
     * @param pattern         string we want to match
     */
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

    /**
     * find all entries of pattern in string.
     * each time we choose the max shift returned by badCharacter and good Suffix heuristic
     *
     * @param string  we find pattern in
     * @param pattern our pattern
     * @param index   the offset of file we are reading
     * @return list of indexes of all entries of pattern
     */
    public List<Integer> search(String string, String pattern, int index) {
        List<Integer> result = new ArrayList<>();
        int[] shiftsGoodSuffix = goodSuffixHeuristic(pattern);
        Map<Character, Integer> badCharMap = badCharacterHeuristic(pattern);
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

    /**
     * read file and find pattern in its text.
     *
     * @param filePath path to file
     * @param pat      pattern to find
     * @return list of indexes of all entries of pattern
     */
    public List<Integer> findInFile(String filePath, String pat, boolean isResource) {
        List<Integer> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int batchSize = 50000;
        int maxSize = 50000;
        String pattern = new String(pat.getBytes(), StandardCharsets.UTF_8);
        int patternLen = pattern.length();
        try (InputStream inputStream = isResource
                ? getClass().getClassLoader().getResourceAsStream(filePath)
                : new FileInputStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + filePath);
            }
            InputStreamReader reader0 = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(reader0);
            char[] buffer = new char[batchSize];
            int charsRead;
            int numBatch = 0;
            int index = 0;
            while ((charsRead = reader.read(buffer)) != -1) {
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

