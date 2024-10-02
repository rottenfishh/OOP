package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.kolodina.expressions.Parser.returnExpression;

import org.junit.jupiter.api.Test;

/**
 * class for testing result of derivative method.
 */
public class DerivativesTest {
    public Expression expr;
    public Expression result;
    String exprString;
    String exceptedResult;

    /**
     * example from classroom.
     * 3 + (2*x)
     */
    @Test
    public void exampleTest() {
        exprString = "3+(2*x)";
        expr = returnExpression(exprString);
        result = expr.derivative("x");
        exceptedResult = "(0.0+((0.0*x)+(1.0*2.0)))";
        assertEquals(exceptedResult, result.convertToString());
    }

    /**
     * example with multiple variables.
     * y * 3 + (x-1)
     */
    @Test
    public void derVariablesTest() {
        exprString = "y * 3 + (x-1)";
        expr = returnExpression(exprString);
        exceptedResult = "(((0.0*3.0)+(0.0*y))+(1.0-0.0))";
        result = expr.derivative("x");
        assertEquals(exceptedResult, result.convertToString());
    }

    /**
     * complicated expression with multiplication and division.
     * (y * 3 / x) - 5
     */
    @Test
    public void complicatedExprTest() {
        exprString = "(y * 3 / x) - 5";
        expr = returnExpression(exprString);
        exceptedResult = "((((((0.0*3.0)+(0.0*y))*x)-(1.0*(y*3.0)))/(x*x))-0.0)";
        result = expr.derivative("x");
        assertEquals(exceptedResult, result.convertToString());
    }
}
