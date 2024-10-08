package ru.nsu.kolodina.expressions;

import java.util.Objects;

/**
 * class which implements sum of two expressions.
 */
public class Add extends Expression {

    private final Expression left;

    private final Expression right;

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
    public double eval(String variables) {
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
    public String toString() {
        String newString = "(" + left.toString() + "+" + right.toString() + ")";
        return newString;
    }

    @Override
    public Expression simplify() {
        Expression simplerLeft = this.left.simplify();
        Expression simplerRight = this.right.simplify();
        Double result;
        if (simplerLeft instanceof Number leftNumber
                && simplerRight instanceof Number rightNumber) {
            result = leftNumber.value + rightNumber.value;
            return new Number(result);
        }
        return new Add(simplerLeft, simplerRight);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Add sum = (Add) object;
        return left.equals(sum.left) && right.equals(sum.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
