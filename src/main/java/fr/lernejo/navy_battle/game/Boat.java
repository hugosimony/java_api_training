package fr.lernejo.navy_battle.game;

import java.util.Arrays;
import java.util.List;

public class Boat {

    private final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public final int size;
    public final int[][] positions;

    public Boat(int size, String[] coos) {
        this.size = size;
        this.positions = new int[size][];
        for (int i = 0; i < coos.length; ++i) {
            this.positions[i] = cooToIntTab(coos[i]);
        }
    }

    public int[] cooToIntTab(String coo) {
        // Translate String coordinates to tab coordinates (A1 = { 0, 0 })
        int x = Integer.parseInt(coo.substring(1, 2)) - 1;
        int y = List.of(letters).indexOf(String.valueOf(coo.charAt(0)));
        return new int[]{x, y};
    }

    public boolean checkCoordinate(String coo) {
        int[] checkPosition = cooToIntTab(coo);
        for (int[] position : positions) {
            if (position[0] == checkPosition[0] && position[1] == checkPosition[1])
                return true;
        }
        return false;
    }
}
