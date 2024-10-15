package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.kolodina.expressions.Parser.returnExpression;

import org.junit.jupiter.api.Test;

/**
 * class for testing evaluation of Expressions.
 */
public class EvalTest {
    public Expression expr;
    String exprString;

    /**
     * example from classroom.
     * 3 + (2*x)
     * excepted result : 23
     */
    @Test
    public void exampleTest() {
        exprString = "3+(2*x)";
        expr = returnExpression(exprString);
        Double result = expr.eval("x=10");
        assertEquals(23.0, result);
    }

    /**
     * testing evaluation with variables assignment.
     * including variables with multiple letters
     * (godzilla + is) * cool - 100/m
     */
    @Test
    public void exprVariablesTest() {
        exprString = "(godzilla + is) * cool - 100 / m";
        expr = returnExpression(exprString);
        Double result = expr.eval("godzilla = 32; is =5; cool=2; m=1");
        assertEquals(-26.0, result);
    }

    /**
     * big expression test.
     * ((4 * (1 + 4)) *3
     */
    @Test
    public void exprBigTest() {
        exprString = "((4* (1+4)) * 3) / ((43-33) * 2)";
        expr = returnExpression(exprString);
        Double result = expr.eval("");
        assertEquals(3.0, result);
    }

    /**
     * single variable test.
     */
    @Test
    public void exprLongTest() {
        exprString = "me";
        expr = returnExpression(exprString);
        Double result = expr.eval("me = 34");
        assertEquals(34.0, result);
    }
}
