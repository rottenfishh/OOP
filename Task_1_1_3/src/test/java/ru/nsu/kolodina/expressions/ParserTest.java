package ru.nsu.kolodina.expressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.kolodina.expressions.Parser.polishNotation;
import static ru.nsu.kolodina.expressions.Parser.returnExpression;

/**
 * testing work of parser.
 */
public class ParserTest {
    private String testString;
    private String testStringExpr;

    /**
     * creating strings to test.
     */
    @BeforeEach
    void setUp() {
        testString = "23423 + (2413-2) *var - 24/2";
        testStringExpr = "324 + 4 * 6 - 9/xe";
    }

    /**
     * test if arrayList of expression in reverse polish notation was created correctly.
     * input string = "23423 + (2413-2) *var - 24/2"
     * correct list:
     * 23423 2413 2 - 34 * + 24 2 / -
     */
    @Test
    public void testPN() {
        ArrayList<String> outputPN = polishNotation(testString);
        assertEquals("23423", outputPN.get(0));
        assertEquals("2413", outputPN.get(1));
        assertEquals("2", outputPN.get(2));
        assertEquals("-", outputPN.get(3));
        assertEquals("var", outputPN.get(4));
        assertEquals("*", outputPN.get(5));
        assertEquals("+", outputPN.get(6));
        assertEquals("24", outputPN.get(7));
        assertEquals("2", outputPN.get(8));
        assertEquals("/", outputPN.get(9));
        assertEquals("-", outputPN.get(10));
    }

    /**
     * test if expression is correctly created from list in polish notation.
     * input string = "324 + 4 * 6 - 9/xe"
     */
    @Test
    public void testReturnExpression() {
        Expression outputExpression = returnExpression(testStringExpr);
        Expression exceptedExpression = new Sub(new Add(new Number(324),
                new Mul(new Number(4), new Number(6))),
                new Div(new Number(9), new Variable("xe")));
        assertEquals(exceptedExpression.convertToString(), outputExpression.convertToString());
    }
}
