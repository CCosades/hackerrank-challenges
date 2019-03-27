package com.hubelias.hackerrank.java;

import java.util.Stack;

public class JavaStack {
    public static boolean isBalanced(String string) {
        Stack<Integer> stack = new Stack<>();

        string.chars().forEach(character -> {
            if (stack.isEmpty() || getBalancer(stack.peek()) != character) {
                stack.push(character);
            } else {
                stack.pop();
            }
        });

        return stack.isEmpty();
    }

    private static int getBalancer(int character) {
        switch (character) {
            case 123:
                return 125;
            case 91:
                return 93;
            case 40:
                return 41;
            default:
                return -1;
        }
    }
}
