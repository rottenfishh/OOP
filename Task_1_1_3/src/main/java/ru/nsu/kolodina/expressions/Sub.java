package ru.nsu.kolodina.expressions;

import java.util.Map;

/**
 * class which implements difference of two expressions.
 */

public class Sub extends Expression {

    final Expression left;

    final Expression right;

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * eval implementation for difference of expressions.
     *
     * @param variables map structure containing variables of the expression
     * @return result of evaluation
     */
    public double eval(Map<String, Double> variables) {
        return left.eval(variables) - right.eval(variables);
    }

    /**
     * derivative implementation for difference of expressions.
     *find derivative for left part of expression and for the right part recursively
     * create new sub expression from derivatives
     *
     * @param variable given variable for which we find derivative
     * @return new expression which is derivative of source expression
     */

    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    /**
     * convert expression to string.
     *
     * @return String version of expression
     */
    public String convertToString() {
        String newString = "(" + left.convertToString() + "-" + right.convertToString() + ")";
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
