package ru.nsu.kolodina.graph;

/**
 * class to parse string into concrete types.
 */
public class Parser {
    /**
     * method for parsing.
     *
     * @param input input string for parse
     * @return converted to type or String
     */
    public static Object parse(String input) {
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException e1) {
            try {
                return Double.valueOf(input);
            } catch (NumberFormatException e2) {
                return input;
            }
        }
    }
}
