package com.hubelias.hackerrank.algorithms.implementation;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/utopian-tree
 */
public class UtopianTree {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i = 0; i<t; i++) {
            int n = in.nextInt();
            int h = 1;
            for(int j = 0; j<n; j++) {
                if(j%2 == 0) {
                    h = 2*h;
                } else {
                    h += 1;
                }
            }
            System.out.println(h);
        }
        in.close();
    }
}
