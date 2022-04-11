package com.progegy.kotprog;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
        if (foldmuvesImg != null) {
            foldmuvesImg.setDisable(Main.game.vasarol.getEgysegek()[0].getOsszelet() <= 0);
            if (foldmuvesImg.isDisabled()) foldmuvesImg.setOpacity(0.5);
            ijaszImg.setDisable(Main.game.vasarol.getEgysegek()[1].getOsszelet() <= 0);
            if (ijaszImg.isDisabled()) ijaszImg.setOpacity(0.5);
            lovagImg.setDisable(Main.game.vasarol.getEgysegek()[2].getOsszelet() <= 0);
            if (lovagImg.isDisabled()) lovagImg.setOpacity(0.5);
            magusImg.setDisable(Main.game.vasarol.getEgysegek()[3].getOsszelet() <= 0);
            if (magusImg.isDisabled()) magusImg.setOpacity(0.5);
            griffImg.setDisable(Main.game.vasarol.getEgysegek()[4].getOsszelet() <= 0);
            if (griffImg.isDisabled()) griffImg.setOpacity(0.5);
        }
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
        boltBetolto(event, 1300, false);
    }

    @FXML
    protected void kozepesNehezseg(ActionEvent event) throws IOException {
        System.out.println("Kozepes nehezseg");
        boltBetolto(event, 1000, false);
    }

    @FXML
    protected void nehezNehezseg(ActionEvent event) throws IOException {
        System.out.println("Nehez nehezseg");
        boltBetolto(event, 700, false);
    }
    //endregion

    @FXML
    Label arany;

    private void boltBetolto(ActionEvent event, int arany, boolean multi) throws IOException {
        Main.game = new GameController(new Hos(arany, true), new Hos(multi ? arany : 1000 , multi));
        System.out.println("Bolt betoltese " + arany + " arannyal");
        fxmlLoader = new FXMLLoader(Main.class.getResource("Shop.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    //region bolt
    //region liras
    @FXML
    Label leiras;

    @FXML
    protected void leirasMegj(MouseEvent event) {
        leiras.setTextFill(Color.BLACK);
        switch (((Label)event.getSource()).getText()) {
            case "Támadás":
                leiras.setText("Leírás: az egységek sebzését növeli meg (támadás * 10%)");
                return;
            case "Védekezés":
                leiras.setText("Leírás: az egységeket ért sebzést csökkenti (védekezés * 5%)");
                return;
            case "Varázserő":
                leiras.setText("Leírás: a hős által idézett varázslatok erősségét növeli");
                return;
            case "Tudás":
                leiras.setText("Leírás: a hős maximális mannapontjait növeli (tudás * 10%)");
                return;
            case "Morál":
                leiras.setText("Leírás: az egységek kezdeményezését növeli (morál * 1)");
                return;
            case "Szerencse":
                leiras.setText("Leírás: az egységek kritikus támadásának esélyét növeli (szerencse * 5%)");
                return;

            case "Villámcsapás":
                leiras.setText(Main.game.vasarol.getVarazslatok()[0].toString());
                return;
            case "Tűzlabda":
                leiras.setText(Main.game.vasarol.getVarazslatok()[1].toString());
                return;
            case "Pajzs":
                leiras.setText(Main.game.vasarol.getVarazslatok()[2].toString());
                return;
            case "Erősítés":
                leiras.setText(Main.game.vasarol.getVarazslatok()[3].toString());
                return;
            case "Feltámasztás":
                leiras.setText(Main.game.vasarol.getVarazslatok()[4].toString());
                return;

            case "Földműves":
                leiras.setText(Main.game.vasarol.getEgysegek()[0].toString());
                return;
            case "Íjász":
                leiras.setText(Main.game.vasarol.getEgysegek()[1].toString());
                return;
            case "Lovag":
                leiras.setText(Main.game.vasarol.getEgysegek()[2].toString());
                return;
            case "Mágus":
                leiras.setText(Main.game.vasarol.getEgysegek()[3].toString());
                return;
            case "Griff":
                leiras.setText(Main.game.vasarol.getEgysegek()[4].toString());
        }
    }

    @FXML
    protected void leirasElrejt(MouseEvent event) {
        leiras.setText("");
    }
    //endregion

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
        int arany = Main.game.vasarol.getArany(), ar;
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
                return Main.game.vasarol.getVarazslatok()[0].getAr();
            case "tuzlabda":
                return Main.game.vasarol.getVarazslatok()[1].getAr();
            case "pajzs":
                return Main.game.vasarol.getVarazslatok()[2].getAr();
            case "erosites":
                return Main.game.vasarol.getVarazslatok()[3].getAr();
            case "feltamasztas":
                return Main.game.vasarol.getVarazslatok()[4].getAr();

            case "foldmuves":
                return Main.game.vasarol.getEgysegek()[0].getAr();
            case "ijasz":
                return Main.game.vasarol.getEgysegek()[1].getAr();
            case "lovag":
                return Main.game.vasarol.getEgysegek()[2].getAr();
            case "magus":
                return Main.game.vasarol.getEgysegek()[3].getAr();
            case "griff":
                return Main.game.vasarol.getEgysegek()[4].getAr();
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
    protected void egysegElrendezo(ActionEvent event) throws IOException {
        int o = 0;
        TextField[] textFields = {villamcsapas, tuzbabda, pajzs, erosites, feltamasztas, foldmuves, ijasz, lovag, magus, griff};
        for (int i = 5; i < 10; i++) o += Integer.parseInt(textFields[i].getText());
        if (o < 1) {
            leiras.setTextFill(Color.RED);
            leiras.setText("Egyedül nem mehetsz csatába!");
            return;
        }
        Slider[] sliders = {tamadas, vedekezes, varazsero, tudas, moral, szerencse};
        Main.game.vasarol.setArany(penzvalto());
        Main.game.vasarol.setTulajdonsagok((int)sliders[0].getValue(), (int)sliders[1].getValue(), (int)sliders[2].getValue(),
                (int)sliders[3].getValue(), (int)sliders[4].getValue(), (int)sliders[5].getValue());
        Main.game.vasarol.setVarazslatok(Integer.parseInt(textFields[0].getText()), Integer.parseInt(textFields[1].getText()),
                Integer.parseInt(textFields[2].getText()), Integer.parseInt(textFields[3].getText()), Integer.parseInt(textFields[4].getText()));
        Main.game.vasarol.setEgysegek(Integer.parseInt(textFields[5].getText()), Integer.parseInt(textFields[6].getText()),
                Integer.parseInt(textFields[7].getText()), Integer.parseInt(textFields[8].getText()), Integer.parseInt(textFields[9].getText()));
        System.out.println("Egysegek elrendezese a tablan");
        fxmlLoader = new FXMLLoader(Main.class.getResource("Arrange.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    //endregion

    @FXML
    GridPane palya;

    @FXML
    ImageView foldmuvesImg;
    @FXML
    ImageView ijaszImg;
    @FXML
    ImageView lovagImg;
    @FXML
    ImageView magusImg;
    @FXML
    ImageView griffImg;

    @FXML
    protected void egysegKivalasztas(MouseEvent event) {
        int e = -1;
        switch (((ImageView)event.getSource()).getId()){
            case "foldmuvesImg":
                e = 0;
                break;
            case "ijaszImg":
                e = 1;
                break;
            case "lovagImg":
                e = 2;
                break;
            case "magusImg":
                e = 3;
                break;
            case "griffImg":
                e = 4;
                break;
        }
        System.out.println("" + e + ". egyseg kivalasztva");
        if (e != -1) Main.game.letesz = Main.game.vasarol.getEgysegek()[e];
        levesz();
        hovaTehet();
    }

    private void levesz() {
        for (Node node: palya.getChildren()) {
            if (GridPane.getRowIndex(node) != null && ((ImageView)node).getImage() != null && ((ImageView)node).getImage().getUrl().contains(Main.game.letesz.getName())) {
                ((ImageView)node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/ures.png"));
            }
        }
    }

    private void hovaTehet() {
        if (Main.game.vasarol == Main.game.player1) {
            for (Node node: palya.getChildren()) {
                if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) < 2 && ((ImageView)node).getImage() == null) {
                    ((ImageView)node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/mezo.png"));
                }
            }
        }
        if (Main.game.vasarol == Main.game.player2) {
            for (Node node: palya.getChildren()) {
                if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) > 9 && ((ImageView)node).getImage() == null) {
                    ((ImageView)node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/mezo.png"));
                }
            }
        }
    }

    @FXML
    protected void mezoKivalasztas(MouseEvent event) {
        if (Main.game.letesz == null) return;
        int row = -1, col = -1;
        if (((ImageView)event.getSource()).getImage() != null) {
            row = GridPane.getRowIndex((Node)event.getSource());
            col = GridPane.getColumnIndex((Node)event.getSource());
        }
        if (row == -1 || col == -1) return;
        boolean nT = false;
        for (Node node: palya.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) == col && ((ImageView)node).getImage() != null && ((ImageView)node).getImage().getUrl().contains("ures")) {
                ((ImageView)node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/" + Main.game.letesz.getName() + ".png"));
                nT = true;
            }
        }
        if (nT) nemTehet();
    }

    private void nemTehet() {
        for (Node node: palya.getChildren()) {
            if (GridPane.getRowIndex(node) != null && ((ImageView)node).getImage() != null && ((ImageView)node).getImage().getUrl().contains("mezo")) {
                ((ImageView)node).setImage(null);
            }
        }
    }

    @FXML
    protected void jatekIndito(ActionEvent event) throws IOException {
        for (Node node: palya.getChildren()) {
            if (GridPane.getRowIndex(node) != null && ((ImageView)node).getImage() != null && !((ImageView)node).getImage().getUrl().contains("ures")) {
                for (Egyseg e: Main.game.vasarol.getEgysegek()) {
                    if (((ImageView)node).getImage().getUrl().contains(e.getName())) {
                        Main.game.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = e;
                    }
                }
            }
        }
        Main.game.printMap();
        if (Main.game.player2.jatekos && Main.game.vasarol != Main.game.player2) {
            Main.game.vasarol = Main.game.player2;
            fxmlLoader = new FXMLLoader(Main.class.getResource("Arrange.fxml"));
        }
        else {
            //bot vásárol és elrendez
            fxmlLoader = new FXMLLoader(Main.class.getResource("Game.fxml"));
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}