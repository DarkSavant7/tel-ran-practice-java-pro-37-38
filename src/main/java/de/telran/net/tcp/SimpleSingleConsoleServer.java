package de.telran.net.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSingleConsoleServer {
    private static final int PORT = 8089;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Thread serverConsoleThread;

    public static void main(String[] args) {
        new SimpleSingleConsoleServer().start();
    }

    private void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server start!");
            waitForConnection(serverSocket);
            startServerConsoleThread();

            while (true) {
                String message = in.readUTF();
                if (message.startsWith("/end")) {
                    shutdown();
                    break;
                }
                System.out.println("Received: " + message);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void shutdown() throws IOException {
        if (serverConsoleThread.isAlive()) serverConsoleThread.interrupt();
        if (socket != null) socket.close();
        System.out.println("Server stopped");
    }

    private void startServerConsoleThread() {
        serverConsoleThread = new Thread(() -> {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.print("Enter message for client >>>> \n");

                while (!Thread.currentThread().isInterrupted()) {
                    if (bufferedReader.ready()) {
                        String serverMessage = bufferedReader.readLine();
                        out.writeUTF(serverMessage);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverConsoleThread.start();
    }

    private void waitForConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for connection......");
        socket = serverSocket.accept();
        System.out.println("Client connected!");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }
}
