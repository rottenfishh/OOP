package ru.nsu.kolodina.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.max;

public class BoyerMoore {
    public static HashMap<Character, Integer> badCharacterHeuristic(String pattern) {
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < pattern.codePointCount(0, pattern.length()); i++) {
            if (!charMap.containsKey(pattern.charAt(i))) {
                charMap.put(pattern.charAt(i), i);
            } else {
                charMap.replace(pattern.charAt(i), i);
            }
        }
        return charMap;
    }

    public static List<Integer> findSubstringBad(String string, String pattern) {
        List<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> charMap = badCharacterHeuristic(pattern);
        int stringLen = string.length();
        int patternLen = pattern.length();
        int shift = 0; // shift of string
        while (shift <= (stringLen - patternLen)) {
            int j = patternLen - 1;
            while (j >= 0 && pattern.charAt(j) == string.charAt(shift + j)) {
                j--;
            }
            if (j < 0) { // if we found pattern j will be -1
                result.add(shift);
                shift += ((shift + patternLen) < stringLen) ? patternLen - charMap.getOrDefault(string.charAt(shift + patternLen), -1) : 1;
            } else { // else skip over pattern
                int idx = charMap.getOrDefault(string.charAt(shift + j), -1);
                shift += max(1, j - idx);
            }
        }
        return result;
    }
    public static int[] goodSuffixHeuristic(String pattern) {
        int[] shifts = new int[pattern.length() + 1];
        int[] borderPositions = new int[pattern.length() + 1];

        findShiftsAndBorders(shifts, borderPositions, pattern);
        setShiftsForPrefix(shifts, borderPositions, pattern);

        return shifts;
    }

    private static void findShiftsAndBorders(int[] shifts, int[] borderPositions, String pattern) {
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

    private static void setShiftsForPrefix(int[] shifts, int[] borderPositions, String pattern) {
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
    public static List<Integer> search(String string, String pattern) {
        List<Integer> result = new ArrayList<>();
        int[] shiftsGoodSuffix = goodSuffixHeuristic(pattern);
        HashMap<Character, Integer> badCharMap = badCharacterHeuristic(pattern);
        int stringLength = string.length();
        int patternLength = pattern.length();
        int i = 0; // pointer of text
        while (i <= stringLength - patternLength) {
            int j = patternLength - 1; // pointer of pattern. move from the right.
            while  (j >= 0 && pattern.charAt(j) == string.charAt(i + j)) {
                j--;
            }
            if (j < 0) { // the whole pattern matched. move past it
                result.add(i);
                i += shiftsGoodSuffix[0];
            } else {
                int badCharShift = j - badCharMap.getOrDefault(string.charAt(i + j), -1);
                int goodSuffixShift = shiftsGoodSuffix[j + 1];
                i += Math.max(badCharShift, goodSuffixShift);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        String txt = "abrakadabra";
        String pattern = "bra";
        List<Integer> res = search(txt, pattern);
        System.out.println(res);
    }
}
