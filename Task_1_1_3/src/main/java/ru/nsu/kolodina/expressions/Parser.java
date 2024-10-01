package ru.nsu.kolodina.expressions;

import java.lang.reflect.Array;
import java.util.Stack;
import java.util.ArrayList;
public class Parser {
    public static int operationOrder(char operation) {
        if (operation == '+' || operation == '-') {
            return 1;
        }
        if (operation == '*' || operation == '/') {
            return 2;
        }
        return 0;
    }

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
            }
            else if (Character.isDigit(c)) {
                StringBuilder temp = new StringBuilder();
                while (i < string.length() && Character.isDigit(string.charAt(i))) {
                    temp.append(string.charAt(i));
                    i++;
                }
                i--;
                outputPN.add(temp.toString());
                flag = 1;
            }
            else if (c == ')') {
                while (!stackOperators.empty() && stackOperators.peek()!= '(') {
                        char top = stackOperators.pop();
                        String outputTop = Character.toString(top);
                        outputPN.add(outputTop);
                    }
                    stackOperators.pop();
                }
            else if (c == '+' || c == '*' || c == '-' || c =='/') {
                while (!stackOperators.empty() && operationOrder(stackOperators.peek())>= operationOrder(c)) {
                    String operator = Character.toString(stackOperators.pop());
                    outputPN.add(operator);
                }
                stackOperators.push(c);
                flag = 0;
            }
        }
        while (!stackOperators.empty()) {
            String operator = Character.toString(stackOperators.pop());
            outputPN.add(operator);
        }
        return outputPN;
    }

    public static Expression returnExpression(String string) {
        ArrayList<String> outputPN = polishNotation(string);
        String curr;
        Stack <Expression> expression = new Stack<>();
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
}
