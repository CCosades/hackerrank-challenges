package com.hubelias.hackerrank.contests.weekofcode26;

import java.util.Scanner;

/**
 * Solution for BestDivisor problem - 2nd challenge of Week od Code 26 contest
 * Difficulty lvl: Easy
 * Solves all 25 test cases.
 * https://www.hackerrank.com/contests/w26/challenges/best-divisor
 */
public class BestDivisor {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.close();

        int bestDivisor = 1;
        int bestSum = 1;

        for(int i = 2; i<=n; i++) {
            if(n%i == 0){
                int sum = sumOfDigits(i);
                if(sum > bestSum) {
                    bestSum = sum;
                    bestDivisor = i;
                }
            }
        }

        System.out.println(bestDivisor);
    }

    private static int sumOfDigits(int number) {
        String numberString = String.valueOf(number);
        int sum = 0;
        for(int i = 0; i<numberString.length(); i++) {
            sum += Integer.parseInt(numberString.substring(i, i+1));
        }
        return sum;
    }
}
