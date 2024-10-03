package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.kolodina.expressions.Parser.returnExpression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class SimplifyTest {
    Expression expr;
    String exprString;
    String excepted;
    Expression simpleExpr;
    /**
     * simple test.
     */
    @Test
    public void testSimple() {
        exprString = "3 + (2*5)";
        expr = returnExpression(exprString);
        simpleExpr = expr.simplify();
        excepted = "13.0";
        assertEquals(excepted, simpleExpr.convertToString());
    }

    @Test
    public void testMulZero() {
        exprString = "(3+243-4/2) * 0";
        expr = returnExpression(exprString);
        simpleExpr = expr.simplify();
        excepted ="0.0";
        assertEquals(excepted, simpleExpr.convertToString());
    }

    @Test
    public void testMulOne() {
        exprString = "(34 - 31 * x) * 1";
        expr = returnExpression(exprString);
        simpleExpr = expr.simplify();
        excepted = "(34.0-(31.0*x))";
        assertEquals(excepted, simpleExpr.convertToString());
    }

    @Test
    public void subEqualExpr() {
        exprString = "(243 * 21 + 2) - (243 * 21 +2)";
        expr = returnExpression(exprString);
        simpleExpr = expr.simplify();
        excepted = "0.0";
        assertEquals(excepted, simpleExpr.convertToString());
    }
}
