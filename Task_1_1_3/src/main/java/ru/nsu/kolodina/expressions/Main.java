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
        String expr = "3 + (2*5)";
        Expression expression = returnExpression(expr);
        System.out.print("Expression:");
        expression.printExpression();
        Expression simple = expression.simplify();
        System.out.print("Simplified:");
        simple.printExpression();
        Double result = expression.eval("x=4");
        System.out.println(result);
    }
}
