package fr.lernejo.navy_battle.game;

import java.util.ArrayList;
import java.util.List;


public class Grid {

    private final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public final ArrayList<int[]> positions;
    private final int size = 10;
    public final int[][] grid = new int[size][size];

    public Grid(ArrayList<int[]> positions) {
        this.positions = positions;
        // Water
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j)
                grid[i][j] = 1;
        }
        // Boats
        for (int[] pos : positions)
            grid[pos[0]][pos[1]] = 3;
    }

    public int[] cooToIntTab(String coo) {
        // Translate String coordinates to tab coordinates (A1 = { 0, 0 })
        int x = Integer.parseInt(coo.substring(1, 2)) - 1;
        int y = List.of(letters).indexOf(String.valueOf(coo.charAt(0)));
        return new int[]{x, y};
    }

    public void update(String cell, String mode) {
        int x = cooToIntTab(cell)[0];
        int y = cooToIntTab(cell)[1];
        if (this.grid[x][y] != 2) {
            if (mode.equals("hit") || mode.equals("sunk")) {
                if (this.grid[x][y] == 3)
                    this.grid[x][y]--;
                else
                    this.grid[x][y]++;
            } else
                this.grid[x][y]--;
        }
    }

    public void printFeedback(String mode) {
        if (mode.equals("hit"))
            System.out.println("HIT!");
        else if (mode.equals("sunk"))
            System.out.println("SUNK!");
        else
            System.out.println("WATER!");
    }
}
