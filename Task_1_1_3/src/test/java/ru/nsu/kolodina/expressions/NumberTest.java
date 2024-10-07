package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing Number class.
 */
public class NumberTest {

    public Expression expr;

    /**
     * creating new Number expression.
     */
    @BeforeEach
    void setUp() {
        expr = new Number(342235);
    }

    /**
     * testing eval method.
     * it should return just the value of number, no matter what is passed
     */
    @Test
    public void testEval() {
        Double result = expr.eval("peepoo = 143");
        assertEquals(342235, result);
    }

    /**
     * testing derivative method.
     * derivative of constant is 0
     */
    @Test
    public void testDerivative() {
        Expression derivative = expr.derivative("xp");
        Expression exceptedDerivative = new Number(0);
        assertEquals(exceptedDerivative.toString(), derivative.toString());
    }

    /**
     * testing convertation to string.
     */
    @Test
    public void testConvertToString() {
        String exceptedString = String.valueOf(342235.0);
        assertEquals(exceptedString, expr.toString());
    }

}
