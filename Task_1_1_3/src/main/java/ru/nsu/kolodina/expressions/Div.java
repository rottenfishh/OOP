package ru.nsu.kolodina.expressions;

import java.util.Objects;

/**
 * class which implements division of two expressions.
 */
public class Div extends Expression {

    private final Expression left;

    private final Expression right;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * eval implementation for finding division of two expressions.
     *
     * @param variables map structure containing variables of the expression
     * @return result of evaluation
     */
    @Override
    public double eval(String variables) {
        double result = 0;
        try {
            result = left.eval(variables) / right.eval(variables);
        } catch (ArithmeticException e) {
            System.out.println("division by zero");
            result = Double.NaN;
        }
        return result;
    }

    /**
     * derivative implementation for division of two expressions.
     * find derivative using formula of derivative from division
     *
     * @param variable given variable for which we find derivative
     * @return new expression which is derivative of source expression
     */
    @Override
    public Expression derivative(String variable) {
        return new Div(new Sub(new Mul(left.derivative(variable), this.right),
                new Mul(right.derivative(variable), this.left)), new Mul(this.right, this.right));
    }

    /**
     * convert expression to string.
     *
     * @return String version of expression
     */
    @Override
    public String toString() {
        String newString = "(" + left.toString() + "/" + right.toString() + ")";
        return newString;
    }

    @Override
    public Expression simplify() {
        Expression simplerLeft = this.left.simplify();
        Expression simplerRight = this.right.simplify();
        Double result;
        if (simplerLeft instanceof Number leftNumber
                && simplerRight instanceof Number rightNumber) {
            result = leftNumber.value / rightNumber.value;
            return new Number(result);
        }
        return new Div(simplerLeft, simplerRight);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Div div = (Div) object;
        return left.equals(div.left) && right.equals(div.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
