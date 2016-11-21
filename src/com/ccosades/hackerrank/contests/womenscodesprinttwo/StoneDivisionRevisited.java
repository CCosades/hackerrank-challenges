package com.ccosades.hackerrank.contests.womenscodesprinttwo;

import java.util.Scanner;

public class StoneDivisionRevisited {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int i = 0; i<q; i++) {
            long n = in.nextLong();
            int m = in.nextInt();
            long[] s = new long[m];
            for(int j = 0; j<m; j++) {
                s[j] = in.nextLong();
            }
            System.out.println(analyze(n, s));
        }
        in.close();
    }

    static int analyze(long initialPileSize, long[] moves) {
        return -1;
    }
}
