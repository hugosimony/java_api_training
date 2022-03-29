package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.game.Game;
import fr.lernejo.navy_battle.game.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {

    private int[] boatSizes = {5, 4, 3, 3, 2};
    private String[][] boats = {
        {"A1", "A2", "A3", "B1", "B2"},
        {"C3", "C4", "C5", "C6"},
        {"D7", "E7", "F7"},
        {"E2", "E3", "E4"},
        {"A6", "A7"}
    };
    private Game game = new Game(boatSizes, boats);
    private Grid grid = game.myGrid;

    @Test
    public void checkBoats() {
        // Check the boats
        Assertions.assertEquals(grid.grid[0][0], 3);
        Assertions.assertEquals(grid.grid[1][1], 3);
        Assertions.assertEquals(grid.grid[2][2], 3);

        // Check the water
        Assertions.assertEquals(grid.grid[7][7], 1);
        Assertions.assertEquals(grid.grid[8][8], 1);
        Assertions.assertEquals(grid.grid[9][9], 1);
    }

    @Test
    public void checkHits() {

        // Check the hits
        grid.update("A1", "hit");
        Assertions.assertEquals(grid.grid[0][0], 2);
        grid.update("B2", "hit");
        Assertions.assertEquals(grid.grid[1][1], 2);
        grid.update("C3", "hit");
        Assertions.assertEquals(grid.grid[2][2], 2);
    }

}
