package ru.nsu.kolodina.expressions;

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
    public double eval(String variables) {
        return left.eval(variables) - right.eval(variables);
    }

    /**
     * derivative implementation for difference of expressions.
     * find derivative for left part of expression and for the right part recursively
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

    @Override
    public Expression simplify() {
        Expression simplerLeft = this.left.simplify();
        Expression simplerRight = this.right.simplify();
        Double result;
        if (simplerLeft instanceof Number && simplerRight instanceof Number) {
            result = ((Number) simplerLeft).value - ((Number) simplerRight).value;
            return new Number(result);
        }
        else if (simplerRight.convertToString().equals(simplerLeft.convertToString())) {
            return new Number(0);
        }
        return new Sub(simplerLeft, simplerRight);
    }
}
