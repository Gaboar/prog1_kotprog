package com.progegy.kotprog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Heroes of Might & Magic");
        stage.setScene(scene);
        stage.show();
    }

    public static GameController game;

    public static void main(String[] args) {
        launch();
    }
}