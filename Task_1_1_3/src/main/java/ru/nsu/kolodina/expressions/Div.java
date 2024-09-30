package ru.nsu.kolodina.expressions;

import java.util.Map;

public class Div extends Expression{

    final Expression left;

    final Expression right;
    public Div(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    public double eval(Map<String, Double> variables){
        return left.eval(variables) / right.eval(variables);
    }

    public Expression derivative(String variable){
        return new Div (new Sub(new Mul(left.derivative(variable), this.right),
                new Mul(right.derivative(variable), this.left)), new Mul(this.right, this.right));
    }

    public String convertToString(){
        String newString = "(" + left.convertToString() + "/" + right.convertToString() + ")";
        return newString;
    }

    @Override
    public void printExpression(){
        System.out.println(this.convertToString());
    }
}
