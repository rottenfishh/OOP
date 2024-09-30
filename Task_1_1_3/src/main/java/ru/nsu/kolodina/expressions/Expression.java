package ru.nsu.kolodina.expressions;

import java.util.Map;

abstract class Expression {

    public abstract double eval(Map<String, Double> variables);

    public abstract Expression derivative(String variable);

    public abstract String convertToString();

    public abstract void printExpression();
}
