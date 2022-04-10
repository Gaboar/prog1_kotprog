package com.progegy.kotprog;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (arany != null) arany.setText("Arany: " + Main.game.player1.getArany());
    }

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    //region nehezseg
    @FXML
    protected void nehezsegValaszto(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("Difficulty.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        System.out.println("Egyjatekosmod inditasa");
    }

    @FXML
    protected void konnyuNehezseg(ActionEvent event) throws IOException {
        System.out.println("Konnyu nehezseg");
        boltBetolto(event, 1300);
    }

    @FXML
    protected void kozepesNehezseg(ActionEvent event) throws IOException {
        System.out.println("Kozepes nehezseg");
        boltBetolto(event, 1000);
    }

    @FXML
    protected void nehezNehezseg(ActionEvent event) throws IOException {
        System.out.println("Nehez nehezseg");
        boltBetolto(event, 700);
    }
    //endregion

    @FXML
    Label arany;

    private void boltBetolto(ActionEvent event, int arany) throws IOException {
        Main.game = new GameController(new Hos(arany), new Hos(1000));
        System.out.println("Bolt betoltese " + arany + " arannyal");
        fxmlLoader = new FXMLLoader(Main.class.getResource("Shop.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    //region liras
    @FXML
    Label leiras;

    @FXML
    protected void leirasMegj(MouseEvent event) {
        leiras.setTextFill(Color.BLACK);
        switch (((Label)event.getSource()).getText()) {
            case "Támadás":
                leiras.setText("asdr");
                return;
            case "Védekezés":
                leiras.setText("asde");
                return;
            case "Varázserő":
                leiras.setText("asdw");
                return;
            case "Tudás":
                leiras.setText("asdq");
                return;
            case "Morál":
                leiras.setText("asdf");
                return;
            case "Szerencse":
                leiras.setText("asd");
                return;

            case "Villámcsapás":
                leiras.setText(Main.game.player1.getVarazslatok()[0].toString());
                return;
            case "Tűzlabda":
                leiras.setText(Main.game.player1.getVarazslatok()[1].toString());
                return;
            case "Pajzs":
                leiras.setText(Main.game.player1.getVarazslatok()[2].toString());
                return;
            case "Erősítés":
                leiras.setText(Main.game.player1.getVarazslatok()[3].toString());
                return;
            case "Feltámasztás":
                leiras.setText(Main.game.player1.getVarazslatok()[4].toString());
                return;

            case "Földműves":
                leiras.setText(Main.game.player1.getEgysegek()[0].toString());
                return;
            case "Íjász":
                leiras.setText(Main.game.player1.getEgysegek()[1].toString());
                return;
            case "Lovag":
                leiras.setText(Main.game.player1.getEgysegek()[2].toString());
                return;
            case "Mágus":
                leiras.setText(Main.game.player1.getEgysegek()[3].toString());
                return;
            case "Griff":
                leiras.setText(Main.game.player1.getEgysegek()[4].toString());
        }
    }

    @FXML
    protected void leirasElrejt(MouseEvent event) {
        leiras.setText("");
    }
    //endregionm

    //region inputfields
    @FXML
    TextField villamcsapas;
    @FXML
    TextField tuzbabda;
    @FXML
    TextField pajzs;
    @FXML
    TextField erosites;
    @FXML
    TextField feltamasztas;
    @FXML
    TextField foldmuves;
    @FXML
    TextField ijasz;
    @FXML
    TextField lovag;
    @FXML
    TextField magus;
    @FXML
    TextField griff;
    @FXML
    Slider tamadas;
    @FXML
    Slider vedekezes;
    @FXML
    Slider varazsero;
    @FXML
    Slider tudas;
    @FXML
    Slider moral;
    @FXML
    Slider szerencse;
    //endregion

    @FXML
    protected void textBoxSelect(MouseEvent event) {
        ((TextField)event.getSource()).selectAll();
    }

    @FXML
    protected void textBoxInputHandler(KeyEvent event) {
        int val;
        try {
            val = Integer.parseInt(((TextField)event.getSource()).getText());
            if (val < 0) throw new Exception("Negative number: \"" + val + "\"");
        } catch (Exception e) {
            System.out.println("[Error] " + e);
            val = 0;
            ((TextField)event.getSource()).setText("" + val);
            ((TextField)event.getSource()).selectAll();
        }
        if (penzvalto() < 0) {
            ((TextField)event.getSource()).setText("0");
            ((TextField)event.getSource()).selectAll();
            penzvalto();
        }
    }

    @FXML
    protected void sliderInputHandler(MouseEvent event) {
        if (penzvalto() < 0) {
            ((Slider)event.getSource()).setValue(0);
            penzvalto();
        }
    }

    private int penzvalto() {
        int arany = Main.game.player1.getArany(), ar;
        System.out.println("--- Artabla: ---");
        TextField[] textFields = {villamcsapas, tuzbabda, pajzs, erosites, feltamasztas, foldmuves, ijasz, lovag, magus, griff};
        for (TextField field: textFields) {
            ar = getPrice(field.getId()) * Integer.parseInt(field.getText());
            System.out.println("" + field.getId() + " = " + ar);
            arany -= ar;
        }
        Slider[] sliders = {tamadas, vedekezes, varazsero, tudas, moral, szerencse};
        ar = getPrice(sliders);
        System.out.println("kepessegek = " + ar);
        arany -= ar;
        this.arany.setText("Arany: " + arany);
        System.out.println("= Arany: " + arany);
        return arany;
    }

    private int getPrice(String id) {
        switch (id) {
            case "villamcsapas":
                return Main.game.player1.getVarazslatok()[0].getAr();
            case "tuzlabda":
                return Main.game.player1.getVarazslatok()[1].getAr();
            case "pajzs":
                return Main.game.player1.getVarazslatok()[2].getAr();
            case "erosites":
                return Main.game.player1.getVarazslatok()[3].getAr();
            case "feltamasztas":
                return Main.game.player1.getVarazslatok()[4].getAr();

            case "foldmuves":
                return Main.game.player1.getEgysegek()[0].getAr();
            case "ijasz":
                return Main.game.player1.getEgysegek()[1].getAr();
            case "lovag":
                return Main.game.player1.getEgysegek()[2].getAr();
            case "magus":
                return Main.game.player1.getEgysegek()[3].getAr();
            case "griff":
                return Main.game.player1.getEgysegek()[4].getAr();
        }
        return 0;
    }

    private int getPrice(Slider[] sliders) {
        int ossz = -6;
        for (Slider s: sliders) {
            ossz += (int)s.getValue();
        }
        if (ossz == 0) return 0;
        int ar = 5, osszar = 0;
        for (int i = 0; i < ossz; i++) {
            osszar += ar;
            ar = (int)Math.ceil(ar * 1.1);
        }
        return osszar;
    }

    @FXML
    protected void jatekElindito(ActionEvent event) throws IOException {
        int o = 0;
        TextField[] textFields = {villamcsapas, tuzbabda, pajzs, erosites, feltamasztas, foldmuves, ijasz, lovag, magus, griff};
        for (TextField field: textFields) o += Integer.parseInt(field.getText());
        if (o < 1) {
            leiras.setTextFill(Color.RED);
            leiras.setText("Egyedül nem mehetsz csatába!");
            return;
        }
        Slider[] sliders = {tamadas, vedekezes, varazsero, tudas, moral, szerencse};
        Main.game.player1.setArany(penzvalto());
        Main.game.player1.setTulajdonsagok((int)sliders[0].getValue(), (int)sliders[1].getValue(), (int)sliders[2].getValue(), (int)sliders[3].getValue(), (int)sliders[4].getValue(), (int)sliders[5].getValue());
        Main.game.player1.setVarazslatok(Integer.parseInt(textFields[0].getText()), Integer.parseInt(textFields[1].getText()), Integer.parseInt(textFields[2].getText()), Integer.parseInt(textFields[3].getText()), Integer.parseInt(textFields[4].getText()));
        Main.game.player1.setEgysegek(Integer.parseInt(textFields[5].getText()), Integer.parseInt(textFields[6].getText()), Integer.parseInt(textFields[7].getText()), Integer.parseInt(textFields[8].getText()), Integer.parseInt(textFields[9].getText()));
        // itt majd multiban megnyitja a boltot a player2nek is
        System.out.println("Jetek inditasa");
        fxmlLoader = new FXMLLoader(Main.class.getResource("Game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}