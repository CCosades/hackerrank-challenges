package com.hubelias.hackerrank.algorithms.bitmanipulation;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/maximizing-xor
 */
public class MaximizingXOR {
    private static int maxXor(int l, int r) {
        int max = 0;
        for(int a = l; a <= r; a++) {
            for(int b = a; b<=r; b++) {
                int xor = a^b;
                if(xor > max) {
                    max = xor;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        int r = in.nextInt();
        System.out.println(maxXor(l,r));
    }
}
