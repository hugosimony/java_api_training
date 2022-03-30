package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.game.Game;
import fr.lernejo.navy_battle.server.SendConnection;
import fr.lernejo.navy_battle.server.Server;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Arguments needed : [port] ([url])");
            return;
        }
        int[] boatSizes = {5, 4, 3, 3, 2};
        String[][] boats = {
            {"A1", "A2", "A3", "B1", "B2"},
            {"C3", "C4", "C5", "C6"},
            {"D7", "E7", "F7"},
            {"E2", "E3", "E4"},
            {"A6", "A7"}
        };
        Game game = new Game(boatSizes, boats);
        if (args.length == 2) {
            new SendConnection(Integer.parseInt(args[0]), args[1]);
            game.opponentURL.add(args[1]);
        }
        Server server = new Server("localhost", Integer.parseInt(args[0]));
        server.createServer(game);
    }
}
