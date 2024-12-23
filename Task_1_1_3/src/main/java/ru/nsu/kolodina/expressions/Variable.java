package ru.nsu.kolodina.expressions;

import static ru.nsu.kolodina.expressions.Parser.parseVar;

import java.util.Map;

/**
 * class implementing variables.
 */
public class Variable extends Expression {

    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    /**
     * evaluating variable with passed value.
     * get value of variable from variables map structure using key which is variable's name
     *
     * @param variables map structure containing variables of the expression
     * @return result of evaluating variable with passed value
     */
    @Override
    public double eval(String variables) {
        Map<String, Double> variablesMap = parseVar(variables);
        if (variablesMap == null) {
            System.out.println("error with parsing variables");
            return Double.NaN;
        }
        if (variables.contains(name)) {
            return (variablesMap.get(name));
        } else {
            return 0; // change for exception later
        }
    }

    /**
     * derivative implementation for variables.
     * if the variable we need to find derivative for is not the variable we call method from
     * return 0 - variable will be looked at like constant
     * else
     * return 1 - derivative of variable is 1
     *
     * @param variable given variable for which we find derivative
     * @return new expression which is derivative of source expression
     */
    @Override
    public Expression derivative(String variable) {
        if (variable.equals(name)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    /**
     * convert expression to string.
     *
     * @return String version of number
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * simplify implemetation for variable.
     * you cant really simplify a variable
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {
        return new Variable(this.name);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Variable var = (Variable) object;
        return (this.name.compareTo(var.name) == 0);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
