package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class Ping implements HttpHandler {

    @Override
    public void handle(HttpExchange e) throws IOException {
        // Message to ping
        String msg = "OK";
        // Send ping response
        e.sendResponseHeaders(200, msg.length());
        try (OutputStream os = e.getResponseBody()) {
            // Write the ping message
            os.write(msg.getBytes());
        }
        // Close the exchange
        e.close();
    }
}
