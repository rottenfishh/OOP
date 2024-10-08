package ru.nsu.kolodina.expressions;

import java.util.Objects;

/**
 * class which implements multiplication of two expressions.
 */
public class Mul extends Expression {

    private final Expression left;

    private final Expression right;

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * eval implementation for finding multiplication of two expressions.
     *
     * @param variables map structure containing variables of the expression
     * @return result of evaluation
     */
    @Override
    public double eval(String variables) {
        return left.eval(variables) * right.eval(variables);
    }

    /**
     * derivative implementation for multiplication of two expressions.
     * find derivative using formula of derivative from multiplication
     *
     * @param variable given variable for which we find derivative
     * @return new expression which is derivative of source expression
     */
    @Override
    public Expression derivative(String variable) {
        return new Add(new Mul(left.derivative(variable), this.right),
                new Mul(right.derivative(variable), this.left));
    }

    /**
     * convert expression to string.
     *
     * @return String version of expression
     */
    @Override
    public String toString() {
        String newString = "(" + left.toString() + "*" + right.toString() + ")";
        return newString;
    }

    /**
     * simplify mul expression.
     * if both left and right are numbers, evaluate expression and return result
     * if left or right is 0 return 0 as result
     * if left or right is 1 return the remaining not 1 expression
     *
     * @return new Mul expression with simplified left and right parts
     */
    @Override
    public Expression simplify() {
        Expression simplerLeft = this.left.simplify();
        Expression simplerRight = this.right.simplify();
        Double result;

        if (simplerLeft instanceof Number leftNumber
                && simplerRight instanceof Number rightNumber) {
            result = leftNumber.value * rightNumber.value;
            return new Number(result);
        }

        if (simplerLeft instanceof Number leftNumber) {
            if (leftNumber.value == 0) {
                return new Number(0);
            } else if (leftNumber.value == 1) {
                return this.right;
            }
        }

        if (simplerRight instanceof Number rightNumber) {
            if (rightNumber.value == 0) {
                return new Number(0);
            } else if (rightNumber.value == 1) {
                return this.left;
            }
        }
        return new Mul(simplerLeft, simplerRight);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Mul mul = (Mul) object;
        return left.equals(mul.left) && right.equals(mul.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
