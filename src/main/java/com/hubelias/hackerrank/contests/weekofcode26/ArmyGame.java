package com.hubelias.hackerrank.contests.weekofcode26;

import java.util.Scanner;

/**
 * Solution for ArmyGame problem - 1st challenge of Week od Code 26 contest
 * Difficulty lvl: Easy
 * Solves all 23 test cases.
 * https://www.hackerrank.com/contests/w26/challenges/game-with-cells/submissions/code/7952377
 */
public class ArmyGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        System.out.println((n/2 + n%2) * (m/2 + m%2));
    }
}
