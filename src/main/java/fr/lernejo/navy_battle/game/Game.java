package fr.lernejo.navy_battle.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import fr.lernejo.navy_battle.server.SendRequest;

import java.util.ArrayList;

public class Game {

    private final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public final ArrayList<String> opponentURL = new ArrayList<>();

    private final Grid opponentGrid = new Grid(new ArrayList<>());
    public final Grid myGrid;

    private final ArrayList<Boat> boatList = new ArrayList<>();
    private final int[] boatLife = new int[5];
    public final int[] attack = {0};

    public Game(int[] boatSize, String[][] boatCoordinates) {
        // Setup boatList
        for (int i = 0; i < boatSize.length; ++i) {
            boatList.add(new Boat(boatSize[i], boatCoordinates[i]));
            boatLife[i] = boatSize[i];
        }
        // Set up the positions
        ArrayList<int[]> positions = new ArrayList<>();
        for (Boat boat : boatList) {
            for (int i = 0; i < boat.size; ++i)
                positions.add(new int[]{boat.positions[i][0], boat.positions[i][1]});
        }
        // Initialize the grid
        myGrid = new Grid(positions);
    }

    public String getNextMove() {
        // Get the next coordinates
        int nextLetter = attack[0] % 10;
        int nextLine = attack[0] / 10 + 1;
        // Get to the next attack
        attack[0]++;
        return letters[nextLetter] + nextLine;
    }

    public String checkTarget(String coo) {

        int boatIndex = 0;
        for (Boat boat : boatList)
        {
            if (boat.checkCoordinate(coo)) {
                boatLife[boatIndex]--;
                if (boatLife[boatIndex] == 0)
                    return "sunk";
                else
                    return "hit";
            }
            boatIndex++;
        }
        return "miss";
    }

    public boolean checkWin() {
        for (int i = 0; i < boatLife.length; ++i)
        {
            // We have at least one ship left alive
            if (boatLife[i] != 0)
                return false;
        }
        // All ships are sunk, end of the game
        return true;
    }

    public void play() throws JsonProcessingException {
        String cell = getNextMove();
        SendRequest shoot = new SendRequest(opponentURL.get(0), cell);
        JsonNode data = shoot.shoot();
        String consequence = data.path("consequence").asText();
        opponentGrid.update(cell, consequence);
        opponentGrid.printFeedback(consequence);
        if (checkWin())
            System.out.println("Nice job! You won.");
    }
}
