package com.hubelias.hackerrank.contests.weekofcode26;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Solution for Music on the Street problem - 4th challenge of Week od Code 26 contest
 * Difficulty lvl: Hard
 * https://www.hackerrank.com/contests/w26/challenges/street-parade-1
 */
public class MusicOnTheStreet {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int m = in.nextInt();
        int h1 = in.nextInt();
        int h2 = in.nextInt();
        in.close();

        System.out.println(solve(a, m, h1, h2));
    }

    static int solve(int[] a, int m, int h1, int h2) {
        List<Node> nodes = new MusicStreet(a, m, h1, h2).getNodes();
        for (int i = 0; i < nodes.size(); i++) {
            Node current = nodes.get(i);
            if (current.type == NodeType.BEGINNING && i < nodes.size() - 1) {
                Node next = nodes.get(i + 1);
                int distance = 0;
                if (next.type == NodeType.ENDING) {
                    distance = next.position - current.position;
                }
                if (next.type == NodeType.BEGINNING && next.getSkipOneEnding() && i < nodes.size() - 2) {
                    if (nodes.get(i + 2).type == NodeType.ENDING) {
                        distance = nodes.get(i + 2).position - current.position;
                    } else if (nodes.get(i + 2).getSkipOneEnding() && i < nodes.size() - 3 && nodes.get(i + 3).type == NodeType.ENDING) {
                        distance = nodes.get(i + 3).position - current.position;
                    }
                }
                if (distance >= m) {
                    return current.position;
                }
            }
        }
        return 9999;
    }
}

class MusicStreet {
    private List<Node> nodes = new ArrayList<>();

    MusicStreet(int[] a, int m, int h1, int h2) {
        int maxGenreDistance = Integer.min(m, h2);
        nodes.add(new Node(a[0] - maxGenreDistance, NodeType.BEGINNING));
        for (int i = 1; i < a.length; i++) {
            int distanceBetweenBorders = a[i] - a[i - 1];
            if (distanceBetweenBorders < h1) {
                nodes.add(new Node(a[i - 1], NodeType.ENDING));
                nodes.add(new Node(a[i], NodeType.BEGINNING));
            } else if (distanceBetweenBorders > maxGenreDistance) {
                Node beginning = new Node(a[i] - maxGenreDistance, NodeType.BEGINNING);
                Node ending = new Node(a[i - 1] + maxGenreDistance, NodeType.ENDING);
                if (beginning.position < ending.position) {
                    beginning.setSkipOneEnding(true);
                    nodes.add(beginning);
                    nodes.add(ending);
                } else {
                    nodes.add(ending);
                    nodes.add(beginning);
                }
            }
        }
        nodes.add(new Node(a[a.length - 1] + maxGenreDistance, NodeType.ENDING));
    }

    List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }
}

class Node {
    final int position;
    final NodeType type;
    private boolean skipOneEnding = false;

    Node(int position, NodeType type) {
        this.position = position;
        this.type = type;
    }

    void setSkipOneEnding(boolean skip) {
        this.skipOneEnding = skip;
    }

    boolean getSkipOneEnding() {
        return this.skipOneEnding;
    }
}

enum NodeType {BEGINNING, ENDING}