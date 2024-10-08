package ru.nsu.kolodina.expressions;

/**
 * abstract class Expression.
 */
public abstract class Expression {

    /**
     * method to evaluate expression.
     *
     * @param variables map structure containing variables of the expression
     * @return double value - result of evaluation
     */
    public abstract double eval(String variables);

    /**
     * method to find the derivative of expression.
     *
     * @param variable given variable for which we find derivative
     * @return new expression which is derivative of the source expression
     */
    public abstract Expression derivative(String variable);

    /**
     * convert expression to string.
     *
     * @return String version of expression
     */
    public abstract String toString();

    /**
     * print expression.
     */
    public void printExpression() {
        System.out.println(this.toString());
    }

    public abstract Expression simplify();
}
