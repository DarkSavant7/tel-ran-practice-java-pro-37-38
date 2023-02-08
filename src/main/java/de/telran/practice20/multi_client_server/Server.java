package de.telran.practice20.multi_client_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 8089;
    private List<Handler> handlers;

    public Server() {
        this.handlers = new ArrayList<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server start!");
            while (true) {
                System.out.println("Waiting for connection......");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                Handler handler = new Handler(socket, this);
                handlers.add(handler);
                handler.handle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) {
        for (Handler handler : handlers) {
            handler.send(message);
        }
    }
}
