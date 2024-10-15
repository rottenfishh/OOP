package ru.nsu.kolodina.expressions;

import java.util.Objects;

/**
 * class which implements difference of two expressions.
 */

public class Sub extends Expression {

    private final Expression left;

    private final Expression right;

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
    @Override
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
    @Override
    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    /**
     * convert expression to string.
     *
     * @return String version of expression
     */
    @Override
    public String toString() {
        String newString = "(" + left.toString() + "-" + right.toString() + ")";
        return newString;
    }

    @Override
    public Expression simplify() {
        Expression simplerLeft = this.left.simplify();
        Expression simplerRight = this.right.simplify();
        Double result;
        if (simplerLeft instanceof Number leftNumber
                && simplerRight instanceof Number rightNumber) {
            result = leftNumber.value - rightNumber.value;
            return new Number(result);
        } else if (simplerRight.equals(simplerLeft)) {
            return new Number(0);
        }
        return new Sub(simplerLeft, simplerRight);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Sub sub = (Sub) object;
        return left.equals(sub.left) && right.equals(sub.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
