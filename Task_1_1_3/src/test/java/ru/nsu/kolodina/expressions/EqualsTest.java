package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * class for testing equals method.
 */
public class EqualsTest {
    Expression expr1;
    Expression expr2;
    Expression expr3;

    @Test
    public void equalsMulTest() {
        expr1 = new Mul(new Number(34), new Variable("x"));
        expr2 = new Mul(new Number(34), new Variable("x"));
        expr3 = new Sub(new Number(21), new Number(0));
        assertTrue(expr1.equals(expr2));
        assertFalse(expr1.equals(expr3));
    }

    @Test
    public void equalsDivTest() {
        expr1 = new Div(new Number(100), new Number(5));
        expr2 = new Div(new Number(100), new Number(5));
        expr3 = new Sub(new Number(21), new Number(0));
        assertTrue(expr1.equals(expr2));
        assertFalse(expr1.equals(expr3));
    }

    @Test
    public void equalsAddTest() {
        expr1 = new Add(new Number(34), new Variable("x"));
        expr2 = new Add(new Number(34), new Variable("x"));
        expr3 = new Sub(new Number(21), new Number(0));
        assertTrue(expr1.equals(expr2));
        assertFalse(expr1.equals(expr3));
    }

    @Test
    public void equalsSubTest() {
        expr1 = new Sub(new Number(34), new Variable("x"));
        expr2 = new Sub(new Number(34), new Variable("x"));
        expr3 = new Div(new Number(21), new Number(0));
        assertTrue(expr1.equals(expr2));
        assertFalse(expr1.equals(expr3));
    }

    @Test
    public void equalsNumberTest() {
        expr1 = new Number(234);
        expr2 = new Number(234);
        expr3 = new Sub(new Number(21), new Number(0));
        assertTrue(expr1.equals(expr2));
        assertFalse(expr1.equals(expr3));
    }

    @Test
    public void equalsVariableTest() {
        expr1 = new Variable("godzilla");
        expr2 = new Variable("godzilla");
        expr3 = new Sub(new Number(21), new Number(0));
        assertTrue(expr1.equals(expr2));
        assertFalse(expr1.equals(expr3));
    }
}
