package app.AnswerRequests;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Server(ServerSocket serverSocket) throws IOException {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Error in Server constructor");
            e.printStackTrace();
            CloseEveryThing(socket, bufferedReader, bufferedWriter);
        }
    }
    public void ReciveMessage(VBox vBox){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        String messageFromClient = bufferedReader.readLine();
                        AnswerRequestsController.addlabel( messageFromClient, vBox);


                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error in reciving message");
                        CloseEveryThing(socket, bufferedReader, bufferedWriter);
                        break;
                    }
                }
            }
        }).start();

    }


    public void CloseEveryThing(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if(socket != null)
                socket.close();
            if(bufferedReader != null)
                bufferedReader.close();
            if(bufferedWriter != null)
                bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error in closing the socket");
            e.printStackTrace();
        }
    }
    public void sendMessage(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Error in sending message");
            e.printStackTrace();
            CloseEveryThing(socket, bufferedReader, bufferedWriter);
        }
    }
}