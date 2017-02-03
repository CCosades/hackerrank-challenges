package com.hubelias.hackerrank.contests.weekofcode26;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Solution for Twins problem - 3rd challenge of Week od Code 26 contest
 * Difficulty lvl: Medium
 * Solves 21 of 29 test cases. All fails occurred due to timeout.
 * https://www.hackerrank.com/contests/w26/challenges/twins
 */
public class Twins {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        System.out.println(solve(n, m));
    }

    static int solve(int n, int m) {
        int oddNumbersFrom3ToM = (m % 2 == 0) ? m / 2 : m / 2 + 1;
        boolean[] isOddPrime = new boolean[oddNumbersFrom3ToM];
        Arrays.fill(isOddPrime, true);
        isOddPrime[0] = false;
        int twins = 0;
        boolean previousIsPrime = false;
        for (int i = 1; i < oddNumbersFrom3ToM; i++) {
            if (isOddPrime[i]) {
                if(i >= n/2) {
                    if (previousIsPrime) {
                        twins++;
                    }
                    previousIsPrime = true;
                }
                int number = i * 2 + 1;
                for (int j = i + number; j < oddNumbersFrom3ToM; j += number) {
                    isOddPrime[j] = false;
                }
            } else {
                previousIsPrime = false;
            }
        }

        return twins;
    }
}
