package ru.nsu.kolodina.expressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * testing Div class.
 */
public class DivTest {

    public Expression expr;

    /**
     * creating expression.
     * 200.0 / pepo
     */
    @BeforeEach
    void setUp() {
        expr = new Div(new Number(200.0), new Variable("pepo"));
    }

    /**
     * testing eval method with pepo variable = 20.0.
     */
    @Test
    public void testEval() {
        Double result = expr.eval("pepo=20.0");
        assertEquals(10.0, result);
    }

    /**
     * testing derivative taken with variable pepo passed.
     */
    @Test
    public void testDerivative() {
        Expression derivative = expr.derivative("pepo");
        String exceptedDerivative = "(((0.0*pepo)-(1.0*200.0))/(pepo*pepo))";
        assertEquals(exceptedDerivative, derivative.convertToString());
    }

    /**
     * testing convertation to string.
     */
    @Test
    public void testConvertToString() {
        String exceptedString = "(200.0/pepo)";
        assertEquals(exceptedString, expr.convertToString());
    }

}
