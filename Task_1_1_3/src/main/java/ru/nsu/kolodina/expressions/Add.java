package ru.nsu.kolodina.expressions;

import java.util.Map;

/**
 * class which implements sum of two expressions.
 */
public class Add extends Expression {

    final Expression left;

    final Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * eval implementation for sum of expressions.
     *
     * @param variables map structure containing variables of the expression
     * @return result of evaluation
     */
    @Override
    public double eval(Map<String, Double> variables) {
        return left.eval(variables) + right.eval(variables);
    }

    /**
     * derivative implementation for sum of expressions.
     * find derivative for left part of expression and for the right part recursively
     * create new sum expression from derivatives
     *
     * @param variable given variable for which we find derivative
     * @return new expression which is derivative of source expression
     */
    @Override
    public Expression derivative(String variable) {
        return new Add(left.derivative(variable), right.derivative(variable));
    }

    /**
     * convert expression to string.
     *
     * @return String version of expression
     */
    @Override
    public String convertToString() {
        String newString = "(" + left.convertToString() + "+" + right.convertToString() + ")";
        return newString;
    }

    /**
     * print expression.
     */
    @Override
    public void printExpression() {
        System.out.println(this.convertToString());
    }
}
