package ru.nsu.kolodina.expressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * testing Mul class.
 */
public class SubTest {

    public Expression expr;

    /**
     * creating expression.
     * 140.0 - xp
     */
    @BeforeEach
    void setUp() {
        expr = new Sub(new Number(140.0), new Variable("xp"));
    }

    /**
     * testing eval method with xp variable = 143.0.
     */
    @Test
    public void testEval() {
        Double result = expr.eval("xp=143.0");
        assertEquals(-3.0, result);
    }

    /**
     * testing derivative taken with variable xp passed.
     */
    @Test
    public void testDerivative() {
        Expression derivative = expr.derivative("xp");
        String exceptedDerivative = "(0.0-1.0)";
        assertEquals(exceptedDerivative, derivative.convertToString());
    }

    /**
     * testing convertation to string.
     */
    @Test
    public void testConvertToString() {
        String exceptedString = "(" + (new Number(140.0)).convertToString() + "-" + (new Variable("xp")).convertToString() + ")";
        assertEquals(exceptedString, expr.convertToString());
    }

}
