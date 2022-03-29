package fr.lernejo.navy_battle.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.game.Game;

import java.io.IOException;
import java.io.OutputStream;

public record GetConnection(Game game, String port) implements HttpHandler {

    public void sendResponse(HttpExchange exchange, String body, int rCode) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(rCode, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }

    private boolean isMissingNode(JsonNode id, JsonNode url, JsonNode message)
    {
        return !id.isMissingNode() && !url.isMissingNode() && !message.isMissingNode();
    }

    @Override
    public void handle(HttpExchange e) throws IOException {
        if (e.getRequestMethod().equals("POST")) {
            JsonNode data = new ObjectMapper().readTree(e.getRequestBody());
            JsonNode id = data.path("id");
            JsonNode url = data.path("url");
            JsonNode message = data.path("message");
            if (isMissingNode(id, url, message)) {
                game.opponentURL.add(data.path("url").asText());
                String msg = "{" +
                    "\n\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\",\n" +
                    "\"url\": \"http://localhost:" + this.port + "\",\n" +
                    "\"message\": \"May the best code win\"\n" +
                    "}";
                sendResponse(e, msg, 202);
                game.play();
            } else sendResponse(e, "Bad Request", 400);
        } else
            sendResponse(e, "Not Found", 404);
        e.close();
    }
}
