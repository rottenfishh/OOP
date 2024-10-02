package ru.nsu.kolodina.expressions;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static ru.nsu.kolodina.expressions.Parser.returnExpression;

/**
 * main class.
 */
public class Main {
    /**
     * main method currently showing output of implemented methods.
     *
     * @param args - default args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        String inputVariable = scanner.nextLine();
        Expression expr = returnExpression(inputString);
        expr.printExpression();
        Map<String, Double> map = new HashMap<>();
        map.put("x", 10.0);
        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x"))); // (3+(2*x))
        e.printExpression();
        Expression de = e.derivative("x");
        de.printExpression();
        double result = e.eval("x=10");
        System.out.println(result);
        de = expr.derivative("x");
        de.printExpression();
        result = expr.eval("pe=5");
        System.out.println(result);
        Expression var = new Variable("godzilla");
        Expression varDe = var.derivative("godzilla");
        varDe.printExpression();
        Expression expr2 = new Sub(new Number(140), new Variable("xp"));
        Expression derivative = expr2.derivative("xp");
        Expression exceptedDerivative = new Sub(new Number(0.0), new Number(1.0));
        derivative.printExpression();
        exceptedDerivative.printExpression();
        String exprString = "5+x";
        expr = returnExpression(exprString);
        Expression result2 = expr.derivative("x");
        String resul = result2.convertToString();
        System.out.println(resul);
    }
}
