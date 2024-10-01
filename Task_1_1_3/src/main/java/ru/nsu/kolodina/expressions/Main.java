package ru.nsu.kolodina.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static ru.nsu.kolodina.expressions.Parser.polishNotation;
import static ru.nsu.kolodina.expressions.Parser.returnExpression;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        Expression expr = returnExpression(inputString);
        expr.printExpression();
        Map<String, Double> map = new HashMap<>();
        map.put("x", 10.0);
        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x"))); // (3+(2*x))
        e.printExpression();
        Expression de = e.derivative("x");
        de.printExpression();
        double result = e.eval(map);
        System.out.println(result);
        de = expr.derivative("x");
        de.printExpression();
        result = expr.eval(map);
        System.out.println(result);
    }
}
