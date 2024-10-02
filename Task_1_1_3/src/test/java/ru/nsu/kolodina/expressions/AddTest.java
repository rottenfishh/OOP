package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing Add class.
 */
public class AddTest {

    public Expression expr;

    /**
     * creating expression.
     * 5 + x
     */
    @BeforeEach
    void setUp() {
        expr = new Add(new Number(5), new Variable("x"));
    }

    /**
     * testing eval method with x variable = 5.
     */
    @Test
    public void testEval() {
        Double result = expr.eval("x=5");
        assertEquals(10.0, result);
    }

    /**
     * testing derivative taken with variable x passed.
     */
    @Test
    public void testDerivative() {
        Expression derivative = expr.derivative("x");
        String exceptedDerivative = "(0.0+1.0)";
        assertEquals(exceptedDerivative, derivative.convertToString());
    }

    /**
     * testing convertation to string.
     */
    @Test
    public void testConvertToString() {
        String exceptedString = "(" + (new Number(5)).convertToString()
                + "+" + (new Variable("x")).convertToString() + ")";
        assertEquals(exceptedString, expr.convertToString());
    }

}
