package ru.nsu.kolodina.expressions;

import java.util.Map;

public class Variable extends Expression{

    final String name;
    public Variable(String name){
        this.name = name;
    }

    @Override
    public double eval(Map<String, Double> variables){
        return (variables.get(name));
    }

    @Override
    public Expression derivative(String variable){
        if (variable.equals(name)){
            return new Number(1);
        } else{
            return new Number(0);
        }
    }

    @Override
    public String convertToString(){
        return name;
    }

    @Override
    public void printExpression(){
        System.out.println(this.convertToString());
    }
}
