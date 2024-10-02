package ru.nsu.kolodina.expressions;

import static ru.nsu.kolodina.expressions.Parser.returnExpression;

/**
 * main class.
 */
public class Main {
    /**
     * main method currently showing output of implemented methods.
     *
     * @param args - default args
     */
    public static void main(String[] args) {
        String expr = "x * (3+5)";
        Expression expression = returnExpression(expr);
        expression.printExpression();
        Double result = expression.eval("x=4");
        System.out.println(result);
    }
}
