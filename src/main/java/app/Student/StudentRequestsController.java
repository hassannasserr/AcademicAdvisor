package app.Student;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.Socket;
import java.sql.*;

public class StudentRequestsController {
    @FXML
    private ImageView send;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField message;

    private Client client;
    @FXML
    void initialize() {
        try {
            client = new Client(new Socket("localhost", 8000));
            System.out.println("Client connected to server");
        } catch (Exception e) {
            e.printStackTrace();

        }
        vBox.heightProperty().addListener((observableValue, number, t1) -> scrollPane.setVvalue((Double) t1));
        client.ReciveMessage(vBox);
        send.setOnMouseClicked((event) -> {
            String messageToSend = message.getText();
            if (!messageToSend.isEmpty()) {
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);

                hBox.setPadding(new Insets(5, 5, 5, 10));
                Text text = new Text(messageToSend);
                TextFlow textFlow = new TextFlow(text);
                textFlow.setStyle("-fx-color:rgb(239,242,255)"+";"+"-fx-background-color:rgb(15, 25, 242)"+";"+"-fx-background-radius: 20px");
                textFlow.setPadding(new Insets(5,10,5,10));
                text.setFill(Color.color(0.934,0.945,0.996));
                hBox.getChildren().add(textFlow);
                vBox.getChildren().add(hBox);

                client.sendMessage(messageToSend);
                message.clear();
            }
        });

    }
    public static void addlabel(String messageFromServer, VBox vbox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));
        Text text= new Text(messageFromServer);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color:rgb(233,233,235)"+";"+"-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vbox.getChildren().add(hBox);
            }
        });
    }

}