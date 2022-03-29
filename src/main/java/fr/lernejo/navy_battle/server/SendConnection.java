package fr.lernejo.navy_battle.server;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SendConnection {
    public SendConnection(int port, String adversaryUrl) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestPost = HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{" +
                "\"id\":\"1\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello\"" +
                "}"))
            .build();
        client.sendAsync(requestPost, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println)
            .join();
    }
}
