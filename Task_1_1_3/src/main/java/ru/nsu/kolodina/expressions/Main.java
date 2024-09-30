package ru.nsu.kolodina.expressions;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, Double> map = new HashMap<>();
        map.put("x", 10.0);
        Expression e = new Add(new Number(3), new Mul(new Number(2),
                new Variable("x"))); // (3+(2*x))
        e.printExpression();
        Expression de = e.derivative("x");
        de.printExpression();
        double result = e.eval(map);
        System.out.println(result);
    }
}
