package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NumberTest {

    public Expression expr;

    @BeforeEach
    void setUp() {
        expr = new Number(342235);
    }

    @Test
    public void testEval() {
        Double result = expr.eval("peepoo = 143");
        assertEquals(342235, result);
    }

    @Test
    public void testDerivative() {
        Expression derivative = expr.derivative("xp");
        Expression exceptedDerivative = new Number(0);
        assertEquals(exceptedDerivative.convertToString(), derivative.convertToString());
    }

    @Test
    public void testConvertToString() {
        String exceptedString = String.valueOf(342235.0);
        assertEquals(exceptedString, expr.convertToString());
    }

}
