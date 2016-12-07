package com.hubelias.hackerrank.contests.weekofcode26;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Solution for Hard Homework problem - 6th challenge of Week od Code 26 contest
 * Difficulty lvl: Expert
 * Solves 18 of 30 test cases. All fails occurred due to timeout.
 * https://www.hackerrank.com/contests/w26/challenges/hard-homework
 */
public class HardHomework {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.printf("%.9f", solve(in.nextInt()));
    }

    static double solve(int n) {
        double[] sin = new double[n];
        for (int i = 0; i < n; i++) {
            sin[i] = Math.sin(i);
        }
        return IntStream.range(1, n/2 + 1).parallel().filter(x -> sin[x] < 0.52).mapToDouble(x -> {
            double yzMax = IntStream.range(1, (n - x) / 2 + 1).parallel().mapToDouble(y -> {
                int z = n - x - y;
                return sin[y] + sin[z];
            }).max().getAsDouble();
            return sin[x] + yzMax;
        }).max().getAsDouble();
    }
}
