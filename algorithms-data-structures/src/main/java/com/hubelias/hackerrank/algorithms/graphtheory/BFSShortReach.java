package com.hubelias.hackerrank.algorithms.graphtheory;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/bfsshortreach
 */
public class BFSShortReach {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt(); // number of queries
        for (int i = 0; i < q; i++) {
            int n = scanner.nextInt(); // number of nodes
            int m = scanner.nextInt(); // number of edges
            Graph graph = new Graph(n);
            for (int j = 0; j < m; j++) {
                graph.addNeighbour(scanner.nextInt(), scanner.nextInt());
            }
            int s = scanner.nextInt(); // starting node

            System.out.println(graph.distances(s));
        }
    }
}

class Graph {
    private Node[] nodes;
    private final static int EDGE_LENGTH = 6;
    private Queue<Node> queue = new LinkedList<>();

    Graph(int numberOfNodes) {
        this.nodes = new Node[numberOfNodes];
        for (int j = 0; j < numberOfNodes; j++) {
            nodes[j] = new Node();
        }
    }

    void addNeighbour(int n1, int n2) {
        nodes[n1 - 1].neighbours.add(nodes[n2 - 1]);
        nodes[n2 - 1].neighbours.add(nodes[n1 - 1]);
    }

    String distances(int startingNode) {
        int startingNodeIndex = startingNode - 1;
        enqueue(nodes[startingNodeIndex], 0);
        while(!queue.isEmpty()) {
            visit(queue.poll());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<nodes.length; i++) {
            if(i != startingNodeIndex) {
                sb.append(nodes[i].shortestDistance).append(" ");
            }
        }
        return sb.toString();
    }

    private void visit(Node node) {
        if(node.visited) {
            return;
        }

        node.neighbours.forEach( children -> {
            if(!children.visited && !children.enqueued) {
                enqueue(children, node.shortestDistance + EDGE_LENGTH);
            }
        });
        node.visited = true;
    }

    private void enqueue(Node node, int distance) {
        node.enqueued = true;
        node.shortestDistance = distance;
        queue.add(node);
    }

    private class Node {
        private Set<Node> neighbours = new HashSet<>();
        boolean visited;
        boolean enqueued;
        int shortestDistance;

        Node() {
            reset();
        }

        void reset() {
            this.visited = false;
            this.enqueued = false;
            this.shortestDistance = -1;
        }
    }
}