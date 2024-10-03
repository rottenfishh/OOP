package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.kolodina.expressions.Parser.returnExpression;

import org.junit.jupiter.api.Test;

/**
 * testing simplify method.
 */
public class SimplifyTest {
    Expression expr;
    String exprString;
    String excepted;
    Expression simpleExpr;

    /**
     * simple test.
     * simplify should evaluate expression
     * because it has no variables
     */
    @Test
    public void testSimple() {
        exprString = "3 + (2*5)";
        expr = returnExpression(exprString);
        simpleExpr = expr.simplify();
        excepted = "13.0";
        assertEquals(excepted, simpleExpr.convertToString());
    }

    /**
     * testing multiplication with zero.
     * expression should be evaluated to zero constant
     */
    @Test
    public void testMulZero() {
        exprString = "(3+243-4/x) * 0";
        expr = returnExpression(exprString);
        simpleExpr = expr.simplify();
        excepted = "0.0";
        assertEquals(excepted, simpleExpr.convertToString());
    }

    /**
     * testing multiplication with one.
     * expression should be changed to its other multiplier
     */
    @Test
    public void testMulOne() {
        exprString = "(34 - 31 * x) * 1";
        expr = returnExpression(exprString);
        simpleExpr = expr.simplify();
        excepted = "(34.0-(31.0*x))";
        assertEquals(excepted, simpleExpr.convertToString());
    }

    /**
     * testing sub with equal expressions.
     * expression should change to zero constant
     */
    @Test
    public void subEqualExpr() {
        exprString = "(243 * 21 + 2) - (243 * 21 +2)";
        expr = returnExpression(exprString);
        simpleExpr = expr.simplify();
        excepted = "0.0";
        assertEquals(excepted, simpleExpr.convertToString());
    }
}
