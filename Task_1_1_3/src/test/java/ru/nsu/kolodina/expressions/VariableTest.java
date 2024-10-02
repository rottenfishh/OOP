package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * testing Variable class.
 */
public class VariableTest {

    public Expression expr;

    /**
     * creating new Variable expression.
     */
    @BeforeEach
    void setUp() {
        expr = new Variable("godzilla");
    }

    /**
     * testing eval method.
     * if the variables passed have variable's name, we should assign value to its name
     */
    @Test
    public void testEval() {
        Double result = expr.eval("godzilla = 5");
        assertEquals(5, result);
    }

    /**
     * testing derivative method.
     * if we find derivative from our variable, it becomes 1
     * if we find derivative from another variable, it becomes 0
     */
    @Test
    public void testDerivative() {
        Expression derivative = expr.derivative("godzilla");
        Expression exceptedDerivative = new Number(1);
        assertEquals(exceptedDerivative.convertToString(), derivative.convertToString());

        Expression derivative2 = expr.derivative("m");
        Expression exceptedDerivative2 = new Number(1);
        assertNotEquals(exceptedDerivative2.convertToString(), derivative2.convertToString());
    }

    /**
     * testing convertation to String.
     */
    @Test
    public void testConvertToString() {
        String exceptedString = "godzilla";
        assertEquals(exceptedString, expr.convertToString());
    }

}

