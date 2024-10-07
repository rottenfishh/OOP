package ru.nsu.kolodina.expressions;

/**
 * class which implements constant numbers realization.
 */
public class Number extends Expression {

    final double value;

    public Number(double value) {
        this.value = value;
    }

    /**
     * eval implementation for numbers.
     *
     * @param variables map structure containing variables of the expression
     * @return the value of number, because it is constant
     */
    @Override
    public double eval(String variables) {
        return this.value;
    }

    /**
     * derivative implementation for number constant.
     *
     * @param variable given variable for which we find derivative
     * @return 0 because derivative of constant is 0
     */
    @Override
    public Number derivative(String variable) {
        return new Number(0);
    }

    /**
     * convert expression to string.
     *
     * @return String version of number
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * simplify implemetation for number.
     * you cant simplify a number
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {
        return new Number(this.value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Number num = (Number) object;
        return (num.value == this.value);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.value);
    }
}
