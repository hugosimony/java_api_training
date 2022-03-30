package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.game.Game;

import java.io.IOException;
import java.io.OutputStream;

public record GetResponse(Game game) implements HttpHandler {

    public void sendResponse(HttpExchange exchange, String body, int rCode) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(rCode, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }

    @Override
    public void handle(HttpExchange e) throws IOException {
        if (e.getRequestMethod().equals("GET")) {
            String query = e.getRequestURI().getQuery();
            String target = query.split("=")[1];
            String consequence = this.game.checkTarget(target);
            boolean stillPlaying = !game.checkWin();
            String msg = "{\n\"consequence\": \"" + consequence + "\",\n\"shipLeft\": " + stillPlaying + "\n}";
            sendResponse(e, msg, 202);
            if (stillPlaying)
                game.play();
            else
                System.out.println("You lost the game !");
        } else
            sendResponse(e, "Not Found", 404);
    }
}
