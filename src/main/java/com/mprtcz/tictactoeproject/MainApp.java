package com.mprtcz.tictactoeproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by azotan on 26.06.17.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage window) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        System.out.println(loader.getLocation());
        Parent root = loader.load();

        Scene scene = new Scene(root, 1024, 768);

        window.setTitle("Tic Tac Toe Game");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
