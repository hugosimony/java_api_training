package fr.lernejo.navy_battle.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public record SendRequest(String adversaryUrl, String cell) {

    public JsonNode shoot() throws JsonProcessingException {
        HttpResponse<String> response = null;
        HttpRequest requestGet = HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl + "/api/game/fire?cell=" + cell))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .build();
        try {
            response = HttpClient.newHttpClient().send(requestGet, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.err.println("[Error] Shoot request: " + e);
        }
        return new ObjectMapper().readTree(response.body());
    }
}
