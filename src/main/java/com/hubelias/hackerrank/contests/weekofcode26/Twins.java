package com.hubelias.hackerrank.contests.weekofcode26;

import java.util.Arrays;
import java.util.Scanner;

public class Twins {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        System.out.println(solve(n, m));
    }

    static int solve(int n, int m) {
        boolean[] isPrime = new boolean[m + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= Math.sqrt(m); i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j <= m; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int twins = 0;
        for (int i = n + 2; i <= m; i++) {
            if (isPrime[i - 2] && isPrime[i]) {
                twins++;
            }
        }
        return twins;
    }
}
