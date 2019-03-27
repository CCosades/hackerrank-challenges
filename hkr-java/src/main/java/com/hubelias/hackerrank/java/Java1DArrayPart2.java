package com.hubelias.hackerrank.java;

public class Java1DArrayPart2 {
    static boolean canWin(final int currentCellIdx, final int leap, final int[] game) {
        if (currentCellIdx >= game.length) {
            return true;
        } else if (currentCellIdx < 0 || game[currentCellIdx] == 1 /*is visited or unavailable*/) {
            return false;
        }

        game[currentCellIdx] = 1; // mark visited

        return canWin(currentCellIdx + leap, leap, game)
                || canWin(currentCellIdx + 1, leap, game)
                || canWin(currentCellIdx - 1, leap, game);
    }
}
