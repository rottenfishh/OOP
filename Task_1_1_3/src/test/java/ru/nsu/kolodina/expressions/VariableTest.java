package ru.nsu.kolodina.expressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VariableTest {

    public Expression expr;

    @BeforeEach
    void setUp() {
        expr = new Variable("godzilla");
    }

    @Test
    public void testEval() {
        Double result = expr.eval("godzilla = 5");
        assertEquals(5, result);
    }

    @Test
    public void testDerivative() {
        Expression derivative = expr.derivative("godzilla");
        Expression exceptedDerivative = new Number(1);
        assertEquals(exceptedDerivative.convertToString(), derivative.convertToString());

        Expression derivative2 = expr.derivative("m");
        Expression exceptedDerivative2 = new Number(1);
        assertNotEquals(exceptedDerivative2.convertToString(), derivative2.convertToString());
    }

    @Test
    public void testConvertToString() {
        String exceptedString = "godzilla";
        assertEquals(exceptedString, expr.convertToString());
    }

}

