package ru.nsu.kolodina.graph;

public class Parser {
    public static Object parse(String input) {
        try {
            return Integer.valueOf(input); // Try Integer
        } catch (NumberFormatException e1) {
            try {
                return Double.valueOf(input); // Try Double
            } catch (NumberFormatException e2) {
                return input; // Fallback to String
            }
        }
    }
}
