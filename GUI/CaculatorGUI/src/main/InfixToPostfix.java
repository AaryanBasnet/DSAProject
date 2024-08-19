package org.example.GUI.CaculatorGUI.src.main;

import java.util.Stack;
import java.util.StringTokenizer;

public class InfixToPostfix {

    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(infix, "+-*/^() ", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();

            if (token.isEmpty()) {
                continue;
            }

            char ch = token.charAt(0);

            if (Character.isDigit(ch)) {
                postfix.append(token).append(" ");
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); // Remove '('
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    // Method to evaluate postfix expression
    public static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(postfix);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (Character.isDigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
            } else if (isOperator(token.charAt(0))) {
                int b = stack.pop();
                int a = stack.pop();
                char op = token.charAt(0);
                int result = applyOperator(a, b, op);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    private static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private static int applyOperator(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '^':
                return (int) Math.pow(a, b);
            default:
                throw new UnsupportedOperationException("Unsupported operator: " + op);
        }
    }
}
