package ru.nsu.kolodina.expressions;

/**
 * class which implements multiplication of two expressions.
 */
public class Mul extends Expression {

    final Expression left;

    final Expression right;

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
    public Expression derivative(String variable) {
        return new Add(new Mul(left.derivative(variable), this.right), new Mul(right.derivative(variable), this.left));
    }

    /**
     * convert expression to string.
     *
     * @return String version of expression
     */
    public String convertToString() {
        String newString = "(" + left.convertToString() + "*" + right.convertToString() + ")";
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
