package app.ChatApp.Server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private Socket socket;
    private PrintWriter writer;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            String text;

            do {
                text = reader.readLine();
                System.out.println("Client: " + text);
                sendServerMessage("Server: Received your message ");

            } while (text != null && !text.equals("bye"));

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Server exception when closing socket: " + ex.getMessage());
            }
        }
    }

    public void sendServerMessage(String message) {
        writer.println(message);
    }
}