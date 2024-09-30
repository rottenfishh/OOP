package ru.nsu.kolodina.expressions;

import java.util.Map;

public class Number extends Expression{

    final double value;

    public Number(double value){
        this.value = value;
    }

    @Override
    public double eval(Map<String, Double> variables){
        return this.value;
    }

    @Override
    public Number derivative(String variable){
        return new Number(0);
    }

    @Override
    public String convertToString(){
        return String.valueOf(value);
    }

    @Override
    public void printExpression(){
        System.out.println(this.convertToString());
    }
}
