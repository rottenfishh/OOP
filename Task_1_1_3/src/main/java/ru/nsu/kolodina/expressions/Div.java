package ru.nsu.kolodina.expressions;

import java.util.Map;

import static ru.nsu.kolodina.expressions.Parser.parseVar;

/**
 * class which implements division of two expressions.
 */
public class Div extends Expression {

    final Expression left;

    final Expression right;

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
    public double eval(String variables) {
        return left.eval(variables) / right.eval(variables);
    }

    /**
     * derivative implementation for division of two expressions.
     *find derivative using formula of derivative from division
     *
     * @param variable given variable for which we find derivative
     * @return new expression which is derivative of source expression
     */
    public Expression derivative(String variable) {
        return new Div(new Sub(new Mul(left.derivative(variable), this.right),
                new Mul(right.derivative(variable), this.left)), new Mul(this.right, this.right));
    }

    /**
     * convert expression to string.
     *
     * @return String version of expression
     */
    public String convertToString() {
        String newString = "(" + left.convertToString() + "/" + right.convertToString() + ")";
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
