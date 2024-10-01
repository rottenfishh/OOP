package ru.nsu.kolodina.expressions;

import java.util.Map;

/**
 * class implementing variables.
 */
public class Variable extends Expression {

    final String name;

    public Variable(String name) {
        this.name = name;
    }

    /**
     * evaluating variable with passed value.
     *get value of variable from variables map structure using key which is variable's name
     *
     * @param variables map structure containing variables of the expression
     * @return result of evaluating variable with passed value
     */
    @Override
    public double eval(Map<String, Double> variables) {
        return (variables.get(name));
    }

    /**
     * derivative implementation for variables.
     * if the variable we need to find derivative for is not the variable we call method from
     * return 0 - variable will be looked at like constant
     * else
     * return 1 - derivative of variable is 1
     *
     * @param variable given variable for which we find derivative
     * @return new expression which is derivative of source expression
     */
    @Override
    public Expression derivative(String variable) {
        if (variable.equals(name)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    /**
     * convert expression to string.
     *
     * @return String version of number
     */
    @Override
    public String convertToString() {
        return name;
    }

    /**
     * print expression.
     */
    @Override
    public void printExpression() {
        System.out.println(this.convertToString());
    }
}
