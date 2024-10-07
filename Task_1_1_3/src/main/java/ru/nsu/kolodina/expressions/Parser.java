package ru.nsu.kolodina.expressions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * Parser class for parsing expressions.
 */
public class Parser {

    /**
     * method to get value of operation order from passed operator.
     *
     * @param operation passed operator
     * @return value of operation order
     */
    public static int operationOrder(char operation) {
        if (operation == '+' || operation == '-') {
            return 1;
        }
        if (operation == '*' || operation == '/') {
            return 2;
        }
        return 0;
    }

    /**
     * implementation of converting expression to
     * reverse polish notation using shunting yard algorithm.
     *
     * @param string we want to parse
     * @return list of parsed values, variables and operators
     */
    public static List<String> polishNotation(String string) {
        int i;
        Deque<Character> stackOperators = new ArrayDeque<>();
        List<String> outputpn = new ArrayList<>();
        string = string.replaceAll(" ", "");
        for (i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(') {
                stackOperators.push(c);
            } else if (Character.isDigit(c)) {
                StringBuilder temp = new StringBuilder();
                while (i < string.length() && Character.isDigit(string.charAt(i))) {
                    temp.append(string.charAt(i));
                    i++;
                }
                i--;
                outputpn.add(temp.toString());
            } else if (c == ')') {
                while (!stackOperators.isEmpty() && stackOperators.peek() != '(') {
                    char top = stackOperators.pop();
                    String outputTop = Character.toString(top);
                    outputpn.add(outputTop);
                }
                stackOperators.pop();
            } else if (c == '+' || c == '*' || c == '-' || c == '/') {
                while (!stackOperators.isEmpty()
                        && operationOrder(stackOperators.peek()) >= operationOrder(c)) {
                    String operator = Character.toString(stackOperators.pop());
                    outputpn.add(operator);
                }
                stackOperators.push(c);
            } else if (Character.isLetter(c)) {
                StringBuilder var = new StringBuilder();
                while (i < string.length() && Character.isLetter(string.charAt(i))) {
                    var.append(string.charAt(i));
                    i++;
                }
                outputpn.add(var.toString());
                i--;
            }
        }
        while (!stackOperators.isEmpty()) {
            String operator = Character.toString(stackOperators.pop());
            outputpn.add(operator);
        }
        return outputpn;
    }

    /**
     * convert passed string to reverse polish notation and then to our expression implementation.
     *
     * @param string we want to parse and convert
     * @return Expression made from the string
     */
    public static Expression returnExpression(String string) {
        List<String> outputpn = polishNotation(string);
        String curr;
        Deque<Expression> expression = new ArrayDeque<>();
        Expression left;
        Expression right;
        Expression newExpr;
        for (int i = 0; i < outputpn.size(); i++) {
            curr = outputpn.get(i);
            if (Character.isDigit(curr.charAt(0))) {
                newExpr = new Number(Double.parseDouble(curr));
                expression.push(newExpr);
            } else if (curr.equals("+")) {
                right = expression.pop();
                left = expression.pop();
                newExpr = new Add(left, right);
                expression.push(newExpr);
            } else if (curr.equals("-")) {
                right = expression.pop();
                left = expression.pop();
                newExpr = new Sub(left, right);
                expression.push(newExpr);
            } else if (curr.equals("*")) {
                right = expression.pop();
                left = expression.pop();
                newExpr = new Mul(left, right);
                expression.push(newExpr);
            } else if (curr.equals("/")) {
                right = expression.pop();
                left = expression.pop();
                newExpr = new Div(left, right);
                expression.push(newExpr);
            } else {
                newExpr = new Variable(curr);
                expression.push(newExpr);
            }
        }
        return expression.pop();
    }

    /**
     * parsing variables we set values for.
     *
     * @param string with following structure: "x = 5; y = 4"
     * @return map structure where name of variable is key, and number is mapped value
     */
    @Nullable
    public static Map<String, Double> parseVar(String string) {
        Map<String, Double> variablesMap = new HashMap<>();
        string = string.replaceAll(" ", "");
        try {
            String[] variablesList = string.split(";");
            for (int i = 0; i < variablesList.length; i++) {
                String[] values = variablesList[i].split("=");
                String var = values[0];
                Double number = Double.parseDouble(values[1]);
                variablesMap.put(var, number);
            }
        } catch (Exception s) {
            System.out.println("wrong format passed");
            return null;
        }
        return variablesMap;
    }
}
