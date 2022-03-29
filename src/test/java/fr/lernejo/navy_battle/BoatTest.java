package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.game.Boat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoatTest {

    @Test
    public void checkBoatCoordinate() {
        int boatSize = 4;
        String[] boatCoo = {"A1", "A2", "A3", "A4"};
        Boat boat = new Boat(boatSize, boatCoo);
        // Check the boat
        Assertions.assertTrue(boat.checkCoordinate("A1"));
        Assertions.assertTrue(boat.checkCoordinate("A2"));
        Assertions.assertTrue(boat.checkCoordinate("A3"));
        Assertions.assertTrue(boat.checkCoordinate("A4"));
    }

    @Test
    public void checkWaterCoordinate() {
        int boatSize = 4;
        String[] boatCoo = {"A1", "A2", "A3", "A4"};
        Boat boat = new Boat(boatSize, boatCoo);
        // Check the water
        Assertions.assertFalse(boat.checkCoordinate("A5"));
        Assertions.assertFalse(boat.checkCoordinate("B1"));
        Assertions.assertFalse(boat.checkCoordinate("C2"));
    }
}
