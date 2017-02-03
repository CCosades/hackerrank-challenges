package com.hubelias.hackerrank.contests.projecteuler;


import java.util.Scanner;

public class InvestigatingUlamSequences {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long k = in.nextLong();
        in.close();

        AstonishingUlamSequence seq = new AstonishingUlamSequence(n);
        System.out.println(seq.getMember(k));
    }
}

class AstonishingUlamSequence {
    private int n;
    private long nextSmallMultiplier;
    private long nextBigMultiplier;
    private long nextMember;

    AstonishingUlamSequence(int n) {
        initialize(n);
    }

    private void initialize(int n) {
        this.n = n;
        this.nextSmallMultiplier = 0;
        this.nextBigMultiplier = 2;
        this.nextMember = 2;
    }

    long getMember(long index) {
        initialize(n);
        for (long i = 2; i <= index; i++) {
            long nextSimple = nextSimpleMember();
            long nextCompound = nextCompoundMember();
            if (nextSimple > nextCompound) {
                nextMember = nextCompound;
                nextBigMultiplier++;
            } else {
                nextMember = nextSimple;
                nextSmallMultiplier++;
            }
        }
        return nextMember;

    }

    private long nextSimpleMember() {
        return 2 * n + 1 + 2 * nextSmallMultiplier;
    }

    private long nextCompoundMember() {
        return (long)(Math.pow(2, nextBigMultiplier) * n + Math.pow(2,nextBigMultiplier - 1) + 2);
    }
}
