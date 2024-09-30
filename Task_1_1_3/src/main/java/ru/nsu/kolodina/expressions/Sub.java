package ru.nsu.kolodina.expressions;

import java.util.Map;

public class Sub extends Expression{

    final Expression left;

    final Expression right;
    public Sub(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    public double eval(Map<String, Double> variables){
        return left.eval(variables) - right.eval(variables);
    }

    public Expression derivative(String variable){
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    public String convertToString(){
        String newString = "(" + left.convertToString() + "-" + right.convertToString() + ")";
        return newString;
    }

    @Override
    public void printExpression(){
        System.out.println(this.convertToString());
    }
}
