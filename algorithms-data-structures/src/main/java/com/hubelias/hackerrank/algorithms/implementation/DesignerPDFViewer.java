package com.hubelias.hackerrank.algorithms.implementation;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/designer-pdf-viewer
 */
public class DesignerPDFViewer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 26;
        int h[] = new int[n];
        for(int h_i=0; h_i < n; h_i++){
            h[h_i] = in.nextInt();
        }
        String word = in.next();
        System.out.println(word.length() * word.chars().map(code -> h[code-97]).max().getAsInt());
    }
}
