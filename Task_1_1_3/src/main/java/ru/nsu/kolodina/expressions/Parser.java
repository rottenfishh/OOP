package ru.nsu.kolodina.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Parser class for parsing expressions.
 */
public class Parser {

    /**
     * method to get value of operation order from passed operator.
     *
     * @param operation passed operator
     * @return vlaue of operation order
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
     * implementation of converting expression to reverse polish notation using shunting yard algorithm.
     *
     * @param string we want to parse
     * @return list of parsed values, variables and operators
     */
    public static ArrayList<String> polishNotation(String string) {
        int i;
        int flag = 0;
        Stack<Character> stackOperators = new Stack<>();
        ArrayList<String> outputPN = new ArrayList<>();
        string = string.replaceAll(" ", "");
        for (i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(') {
                stackOperators.push(c);
                flag = 0;
            } else if (Character.isDigit(c)) {
                StringBuilder temp = new StringBuilder();
                while (i < string.length() && Character.isDigit(string.charAt(i))) {
                    temp.append(string.charAt(i));
                    i++;
                }
                i--;
                outputPN.add(temp.toString());
                flag = 1;
            } else if (c == ')') {
                while (!stackOperators.empty() && stackOperators.peek() != '(') {
                    char top = stackOperators.pop();
                    String outputTop = Character.toString(top);
                    outputPN.add(outputTop);
                }
                stackOperators.pop();
            } else if (c == '+' || c == '*' || c == '-' || c == '/') {
                while (!stackOperators.empty() && operationOrder(stackOperators.peek()) >= operationOrder(c)) {
                    String operator = Character.toString(stackOperators.pop());
                    outputPN.add(operator);
                }
                stackOperators.push(c);
                flag = 0;
            } else {
                outputPN.add(Character.toString(c));
            }
        }
        while (!stackOperators.empty()) {
            String operator = Character.toString(stackOperators.pop());
            outputPN.add(operator);
        }
        return outputPN;
    }

    /**
     * convert passed string to reverse polish notation and then to our expression implementation.
     *
     * @param string we want to parse and convert
     * @return Expression made from the string
     */
    public static Expression returnExpression(String string) {
        ArrayList<String> outputPN = polishNotation(string);
        String curr;
        Stack<Expression> expression = new Stack<>();
        Expression left;
        Expression right;
        Expression newExpr;
        for (int i = 0; i < outputPN.size(); i++) {
            curr = outputPN.get(i);
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
    public static Map<String, Double> parseVar(String string){
        Map<String, Double> variablesMap = new HashMap<>();
        string = string.replaceAll(" ", "");
        String[] variablesList = string.split(";");
        for (int i = 0; i < variablesList.length ; i++) {
            String[] values = variablesList[i].split("=");
            String var = values[0];
            Double number = Double.parseDouble(values[1]);
            variablesMap.put(var, number);
        }
        return variablesMap;
    }
}
