package ru.nsu.kolodina.expressions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.kolodina.expressions.Parser.polishNotation;
import static ru.nsu.kolodina.expressions.Parser.returnExpression;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testPolishNotation() {
        ArrayList<String> outputpn = polishNotation(testString);
        assertEquals("23423", outputpn.get(0));
        assertEquals("2413", outputpn.get(1));
        assertEquals("2", outputpn.get(2));
        assertEquals("-", outputpn.get(3));
        assertEquals("var", outputpn.get(4));
        assertEquals("*", outputpn.get(5));
        assertEquals("+", outputpn.get(6));
        assertEquals("24", outputpn.get(7));
        assertEquals("2", outputpn.get(8));
        assertEquals("/", outputpn.get(9));
        assertEquals("-", outputpn.get(10));
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
