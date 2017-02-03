package com.hubelias.hackerrank.ai.botbuilding;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Solution for Bot saves princess problem
 * Difficulty lvl: Easy
 * https://www.hackerrank.com/challenges/saveprincess
 */
public class BotSavesPrincess {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int gridSize = in.nextInt();
        int princessRow = -1;
        int princessCol = -1;
        int botRow = -1;
        int botCol = -1;

        for (int row = 0; row < gridSize; row++) {
            String wholeRow = in.next();
            if (wholeRow.indexOf('p') != -1) {
                princessRow = row;
                princessCol = wholeRow.indexOf('p');
            }
            if (wholeRow.indexOf('m') != -1) {
                botRow = row;
                botCol = wholeRow.indexOf('m');
            }
        }

        botCommands(princessRow, princessCol, botRow, botCol).forEach(System.out::println);
    }


    static List<String> botCommands(int princessRow, int princessCol, int botRow, int botCol) {
        if (princessRow < 0 || princessCol < 0 || botRow < 0 || botCol < 0) {
            throw new IllegalArgumentException();
        }

        int horizontalDist = Math.abs(botCol - princessCol);
        int verticalDist = Math.abs(botRow - princessRow);
        int shortestPathLength = horizontalDist + verticalDist;

        String horizontalDir;
        if (princessCol > botCol) {
            horizontalDir = "RIGHT";
        } else {
            horizontalDir = "LEFT";
        }
        String verticalDir;
        if (princessRow > botRow) {
            verticalDir = "DOWN";
        } else {
            verticalDir = "UP";
        }

        List<String> commands = new ArrayList<>(shortestPathLength);

        for (int i = 0; i < verticalDist; i++) {
            commands.add(verticalDir);
        }
        for (int i = verticalDist; i < shortestPathLength; i++) {
            commands.add(horizontalDir);
        }

        return commands;
    }
}
