package ru.goncharova.mycalculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

class ExpressionParser {
    private static String operators;

    private static String delimiters;

    static {
        operators = "+-*/";
        delimiters = "() " + operators;
    }

    public static boolean flag = true;
    private static boolean isDelimiter(String token) {
        if (token.length() != 1) return false;
        for (int i = 0; i < delimiters.length(); i++) {
            if (token.charAt(0) == delimiters.charAt(i)) return true;
        }
        return false;
    }

    private static boolean isOperator(String token) {
        if (token.equals("u-")) return true;
        for (int i = 0; i < operators.length(); i++) {
            if (token.charAt(0) == operators.charAt(i)) return true;
        }
        return false;
    }

    private static boolean isFunction(String token) {
        if (token.equals("sqrt") || token.equals("cube") || token.equals("pow10")) return true;
        return false;
    }

    private static int priority(String token) {
        if (token.equals("(")) return 1;
        if (token.equals("+") || token.equals("-")) return 2;
        if (token.equals("*") || token.equals("/")) return 3;
        return 4;
    }

    public static List<String> parse(String infix) {
        List<String> postfix = new ArrayList<String>();
        Deque<String> stack = new ArrayDeque<String>();
        StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
        String prev = "";
        String curr = "";
        while (tokenizer.hasMoreTokens()) {
            curr = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
                System.out.println("Некорректное выражение.");
                flag = false;
                return postfix;
            }
            if (curr.equals(" ")) continue;
            if (isFunction(curr)) stack.push(curr);
            else if (isDelimiter(curr)) {
                if (curr.equals("(")) stack.push(curr);
                else if (curr.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {
                            System.out.println("Скобки не согласованы.");
                            flag = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty() && isFunction(stack.peek())) {
                        postfix.add(stack.pop());
                    }
                }
                else {
                    if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev)  && !prev.equals(")")))) {
                        // унарный минус
                        curr = "u-";
                    }
                    else {
                        while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
                            postfix.add(stack.pop());
                        }

                    }
                    stack.push(curr);
                }

            }

            else {
                postfix.add(curr);
            }
            prev = curr;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) postfix.add(stack.pop());
            else {
                System.out.println("Скобки не согласованы.");
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }

    public double calculate(List<String> expression) {

        ArrayList<String> operators = new ArrayList();

        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");

        while(expression.size() > 1) {

            int index = 0;

            while (operators.contains(expression.get(index)) == false) {
                index++;
            }

            String operator = expression.get(index);
            double leftOperand = Double.parseDouble(expression.get(index - 2));
            double rightOperand = Double.parseDouble(expression.get(index - 1));

            double result = 0;

            if (operator.equals("*")) {
                result = leftOperand * rightOperand;
            }

            if (operator.equals("/")) {
                result = leftOperand / rightOperand;
            }

            if (operator.equals("-")) {
                result = leftOperand - rightOperand;
            }

            if (operator.equals("+")) {
                result = leftOperand + rightOperand;
            }

            expression.remove(index - 2);
            expression.remove(index - 2);
            expression.remove(index - 2);

            expression.add(index - 2, Double.toString(result));
        }

        return Double.parseDouble(expression.get(0));
    }

    public static void main (String[] args) {
        ExpressionParser parser = new ExpressionParser();

        String expression = "2+4*2/(2-1)";

        List<String> dd = parser.parse(expression);

        double res = parser.calculate(dd);

        int a = 10;
    }
}
