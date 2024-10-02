package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing Mul class.
 */
public class MulTest {

    public Expression expr;

    /**
     * creating expression.
     * 23 * x
     */
    @BeforeEach
    void setUp() {
        expr = new Mul(new Number(23), new Variable("x"));
    }

    /**
     * testing eval method with x variable = 5.
     */
    @Test
    public void testEval() {
        Double result = expr.eval("x=5");
        assertEquals(115, result);
    }

    /**
     * testing derivative taken with variable x passed.
     */
    @Test
    public void testDerivative() {
        Expression derivative = expr.derivative("x");
        String exceptedDerivative = "((0.0*x)+(1.0*23.0))";
        assertEquals(exceptedDerivative, derivative.convertToString());
    }

    /**
     * testing convertation to string.
     */
    @Test
    public void testConvertToString() {
        String exceptedString = "(" + (new Number(23)).convertToString()
                + "*" + (new Variable("x")).convertToString() + ")";
        assertEquals(exceptedString, expr.convertToString());
    }

}
