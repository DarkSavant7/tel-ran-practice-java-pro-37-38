package de.telran.practice20.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimpleSingleConsoleClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8089;
    private DataOutputStream out;
    private DataInputStream in;
    private Thread clientConsoleThread;

    public static void main(String[] args) {
        new SimpleSingleConsoleClient().start();
    }

    private void start() {
        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Client started!");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            startClientConsoleThread();

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
        if (clientConsoleThread.isAlive()) clientConsoleThread.interrupt();
        System.out.println("Client stopped");
    }

    private void startClientConsoleThread() {
        clientConsoleThread = new Thread(() -> {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.print("Enter message for server >>>> \n");

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
        clientConsoleThread.start();
    }

}
