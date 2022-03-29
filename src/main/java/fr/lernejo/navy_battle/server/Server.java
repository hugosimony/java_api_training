package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.game.Game;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    private final String url;
    private final int port;
    private final String portString;

    public Server(String url, int port) {
        this.url = url;
        this.port = port;
        this.portString = String.valueOf(port);
    }

    public void createServer(Game game) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(url, port), 0);
        server.setExecutor(Executors.newFixedThreadPool(1));
        server.start();
        server.createContext("/ping", new Ping());
        server.createContext("/api/game/start", new GetConnection(game, portString));
        server.createContext("/api/game/fire", new GetResponse(game));
        System.out.println("Server started! Selected port: " + port);
    }
}
