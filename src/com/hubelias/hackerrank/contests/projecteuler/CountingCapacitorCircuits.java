package com.hubelias.hackerrank.contests.projecteuler;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CountingCapacitorCircuits {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        Set<CapacitorNode> capacitances = new HashSet<>();
        CapacitorNode capacitorUnit = new CapacitorNode(1, new Fraction(60), "C");
        capacitances.add(capacitorUnit);

        for (int capCount = 2; capCount <= n; capCount++) {
            Set<CapacitorNode> capacitancesInStep = new HashSet<>();
            for (int j = 1; j <= capCount / 2; j++) {
                int baseSize = j;
                int complementSize = capCount - j;
                Set<CapacitorNode> baseNodes = capacitances.parallelStream().filter(node -> node.numberOfCapacitors == baseSize).collect(Collectors.toSet());
                Set<CapacitorNode> complementNodes = capacitances.parallelStream().filter(node -> node.numberOfCapacitors == complementSize).collect(Collectors.toSet());

                baseNodes.forEach(baseNode -> complementNodes.forEach(complementNode -> {
                        capacitancesInStep.add(baseNode.attachInSeries(complementNode));
                        capacitancesInStep.add(baseNode.attachParallel(complementNode));
                    })
                );
            }
            capacitances.addAll(capacitancesInStep);
        }

        System.out.println(capacitances.size());
    }

    private static int howManyCombinations(int n) {
        if (n == 0) {
            return 1;
        }
        return 2 ^ n + howManyCombinations(n - 1);
    }
}

class CapacitorNode implements Comparable<CapacitorNode> {
    final Integer numberOfCapacitors;
    final Fraction capacitance;
    final String structure;

    CapacitorNode(int numberOfCapacitors, Fraction capacitance, String structure) {
        this.numberOfCapacitors = numberOfCapacitors;
        this.capacitance = capacitance;
        this.structure = structure;
    }

    @Override
    public int compareTo(CapacitorNode o) {
        return this.capacitance.compareTo(o.capacitance);
    }

    CapacitorNode attachParallel(CapacitorNode c) {
        String newStructure = "(" + structure + "-" + c.structure + ")";
        return new CapacitorNode(numberOfCapacitors + c.numberOfCapacitors, capacitance.add(c.capacitance), newStructure);
    }

    CapacitorNode attachInSeries(CapacitorNode c) {
        Fraction newCapa = capacitance.multiply(c.capacitance).divide(capacitance.add(c.capacitance));

        return new CapacitorNode(numberOfCapacitors + c.numberOfCapacitors, newCapa, structure + c.structure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CapacitorNode)) return false;

        CapacitorNode that = (CapacitorNode) o;

        return capacitance.equals(that.capacitance);
    }

    @Override
    public int hashCode() {
        return capacitance.hashCode();
    }

    @Override
    public String toString() {
        return "Node: " + numberOfCapacitors + " capacitor(s), " + capacitance + " uF, struct: " + structure;
    }
}

class Fraction implements Comparable<Fraction> {
    private int numerator, denominator;

    Fraction(int num, int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Denominator must not be zero!");
        }
        this.numerator = num;
        this.denominator = den;
        adjustSign();
        reduce();
    }

    Fraction(int num) {
        this.numerator = num;
        this.denominator = 1;
    }

    private void reduce() {
        int maxReducer = Math.min(numerator, denominator);
        for (int reducer = 2; reducer <= maxReducer; reducer++) {
            if (numerator % reducer == 0 && denominator % reducer == 0) {
                numerator = numerator / reducer;
                denominator = denominator / reducer;
                reduce();
            }
        }
    }

    private void adjustSign() {
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    @Override
    public String toString() {
        return numerator + " / " + denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;

        Fraction fraction = (Fraction) o;

        if (numerator != fraction.numerator) return false;
        return denominator == fraction.denominator;

    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    public Fraction add(Fraction f) {
        int commonDen = denominator * f.denominator;
        return new Fraction(numerator * f.denominator + f.numerator * denominator, commonDen);
    }

    public Fraction subtract(Fraction f) {
        int commonDen = denominator * f.denominator;
        return new Fraction(numerator * f.denominator - f.numerator * denominator, commonDen);
    }

    public Fraction multiply(Fraction f) {
        return new Fraction(numerator * f.numerator, denominator * f.denominator);
    }

    public Fraction divide(Fraction f) {
        return new Fraction(numerator * f.denominator, denominator * f.numerator);
    }

    public Fraction invert() {
        return new Fraction(denominator, numerator);
    }

    @Override
    public int compareTo(Fraction o) {
        Fraction difference = this.subtract(o);
        if (difference.numerator > 0) {
            return 1;
        } else if (difference.numerator == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    public double toDouble() {
        return numerator / denominator;
    }
}
