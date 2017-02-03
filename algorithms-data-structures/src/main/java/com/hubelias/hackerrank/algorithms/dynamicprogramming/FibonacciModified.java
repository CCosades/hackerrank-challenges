package com.hubelias.hackerrank.algorithms.dynamicprogramming;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/fibonacci-modified
 */
public class FibonacciModified {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger t1 = new BigInteger(scanner.next());
        BigInteger t2 = new BigInteger(scanner.next());
        BigInteger t = new BigInteger("0");
        int n = scanner.nextInt();
        scanner.close();

        for(int i = 3; i<=n; i++) {
            t = new BigInteger(t1.toString()).add(new BigInteger(t2.toString()).pow(2));
            t1 = t2;
            t2 = t;
        }

        System.out.println(t);
    }
}
