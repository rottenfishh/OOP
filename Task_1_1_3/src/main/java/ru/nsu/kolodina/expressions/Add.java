package ru.nsu.kolodina.expressions;

import java.util.Map;

public class Add extends Expression{

    final Expression left;

    final Expression right;

    public Add(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public double eval(Map<String, Double> variables){
        return left.eval(variables) + right.eval(variables);
    }

    @Override
    public Expression derivative(String variable){
        return new Add(left.derivative(variable), right.derivative(variable));
    }

    @Override
    public String convertToString(){
        String newString = "(" + left.convertToString() + "+" + right.convertToString() + ")";
        return newString;
    }

    @Override
    public void printExpression(){
        System.out.println(this.convertToString());
    }
}
