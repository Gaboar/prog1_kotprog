package com.progegy.kotprog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class ApplicationController {

    private Stage stage;
    private Scene scene;
    FXMLLoader fxmlLoader;

    @FXML
    protected void nehezsegValaszto(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("Difficulty.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void konnyuNehezseg(ActionEvent event) throws IOException {
        boltBetolto(event, 1300);
    }

    @FXML
    protected void kozepesNehezseg(ActionEvent event) throws IOException {
        boltBetolto(event, 1000);
    }

    @FXML
    protected void nehezNehezseg(ActionEvent event) throws IOException {
        boltBetolto(event, 700);

    }

    private void boltBetolto(ActionEvent event, int arany) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("Shop.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}