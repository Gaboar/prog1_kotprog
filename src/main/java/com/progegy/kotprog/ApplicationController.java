package com.progegy.kotprog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class ApplicationController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (arany != null) arany.setText("Arany: " + Main.game.vasarol.getArany());
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
        if (csatater != null) {
            updateMap();
            try {
                selectNext();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (gyoztes != null) {
            if (Main.game.player2.jatekos) {
                if (Main.game.player1.elMeg()) {
                    System.out.println("a bal olbali jatekos nyert");
                    gyoztes.setText("A bal oldali játékos nyert");
                }
                else {
                    System.out.println("a jobb olbali jatekos nyert");
                    gyoztes.setText("A jobb oldali játékos nyert");
                }
            }
            else {
                if (Main.game.player1.elMeg()) {
                    System.out.println("nyertel");
                    gyoztes.setText("Megnyerted a játékot");
                }
                else {
                    System.out.println("vesztettel");
                    gyoztes.setText("Sajnos vesztettél");
                }
            }
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
    protected void multiValaszto(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("MultiMode.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        System.out.println("Tobbjatekosmod inditasa");
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

    @FXML
    protected void normalMulti(ActionEvent event) throws IOException {
        System.out.println("Normal aranymennyiseg");
        boltBetolto(event, 1000, true);
    }

    @FXML
    protected void vilaghaboruMulti(ActionEvent event) throws IOException {
        System.out.println("Giga aranymennyiseg");
        boltBetolto(event, 30000, true);
    }

    private void boltBetolto(ActionEvent event, int arany, boolean multi) throws IOException {
        Main.game = new GameController(new Hos(arany, true), new Hos(multi ? arany : 1000 , multi));
        System.out.println("Bolt betoltese " + arany + " arannyal");
        fxmlLoader = new FXMLLoader(Main.class.getResource("Shop.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    //endregion

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
    CheckBox villamcsapas;
    @FXML
    CheckBox tuzlabda;
    @FXML
    CheckBox pajzs;
    @FXML
    CheckBox erosites;
    @FXML
    CheckBox feltamasztas;
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
    Label arany;

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
    protected void checkBoxInputHandler(ActionEvent event) {
        if (penzvalto() < 0) {
            ((CheckBox)event.getSource()).setSelected(false);
            penzvalto();
        }
    }

    @FXML
    protected void sliderInputHandler(MouseEvent event) {
        if (penzvalto() < 0) {
            ((Slider)event.getSource()).setValue(1);
            penzvalto();
        }
    }

    private int penzvalto() {
        int arany = Main.game.vasarol.getArany(), ar;
        System.out.println("--- Artabla: ---");
        TextField[] textFields = {foldmuves, ijasz, lovag, magus, griff};
        for (TextField field: textFields) {
            ar = getPrice(field.getId()) * Integer.parseInt(field.getText());
            System.out.println("" + field.getId() + " = " + ar);
            arany -= ar;
        }
        CheckBox[] checkBoxes = {villamcsapas, tuzlabda, pajzs, erosites, feltamasztas};
        for (CheckBox check: checkBoxes) {
            ar = getPrice(check.getId()) * (check.isSelected() ? 1 : 0);
            System.out.println("" + check.getId() + " = " + ar);
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
        TextField[] textFields = {foldmuves, ijasz, lovag, magus, griff};
        for (TextField field: textFields) o += Integer.parseInt(field.getText());
        if (o < 1) {
            leiras.setTextFill(Color.RED);
            leiras.setText("Egyedül nem mehetsz csatába!");
            return;
        }
        CheckBox[] checkBoxes = {villamcsapas, tuzlabda, pajzs, erosites, feltamasztas};
        Slider[] sliders = {tamadas, vedekezes, varazsero, tudas, moral, szerencse};
        Main.game.vasarol.setArany(penzvalto());
        Main.game.vasarol.setTulajdonsagok((int)sliders[0].getValue(), (int)sliders[1].getValue(), (int)sliders[2].getValue(),
                (int)sliders[3].getValue(), (int)sliders[4].getValue(), (int)sliders[5].getValue());
        Main.game.vasarol.setVarazslatok(checkBoxes[0].isSelected(), checkBoxes[1].isSelected(),
                checkBoxes[2].isSelected(), checkBoxes[3].isSelected(), checkBoxes[4].isSelected());
        Main.game.vasarol.setEgysegek(Integer.parseInt(textFields[0].getText()), Integer.parseInt(textFields[1].getText()),
                Integer.parseInt(textFields[2].getText()), Integer.parseInt(textFields[3].getText()), Integer.parseInt(textFields[4].getText()));
        System.out.println("Egysegek elrendezese a tablan");
        fxmlLoader = new FXMLLoader(Main.class.getResource("Arrange.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    //endregion

    //region elrendezo
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
        System.out.println("" + (e + 1) + ". egyseg kivalasztva");
        if (e != -1) Main.game.letesz = Main.game.vasarol.getEgysegek()[e];
        for (Node node: palya.getChildren()) {
            if (GridPane.getRowIndex(node) != null && ((ImageView)node).getImage() != null && ((ImageView)node).getImage().getUrl().contains(Main.game.letesz.getNev())) {
                ((ImageView)node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/ures.png"));
            }
        }
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
        System.out.println("lerakas {" + (row + 1) + ";" + (col + 1) + "} mezore");
        boolean nemTehet = false;
        for (Node node: palya.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) == col && ((ImageView)node).getImage() != null && ((ImageView)node).getImage().getUrl().contains("ures")) {
                kepForgato((ImageView)node, Main.game.vasarol, new Image("file:src/main/resources/com/progegy/kotprog/" + Main.game.letesz.getNev() + ".png"));
                nemTehet = true;
            }
        }
        if (nemTehet) {
            for (Node node: palya.getChildren()) {
                if (GridPane.getRowIndex(node) != null && ((ImageView)node).getImage() != null && ((ImageView)node).getImage().getUrl().contains("mezo")) {
                    ((ImageView)node).setImage(null);
                }
            }
        }
    }

    @FXML
    protected void jatekIndito(ActionEvent event) throws IOException {
        for (Node node: palya.getChildren()) {
            if (GridPane.getRowIndex(node) != null && ((ImageView)node).getImage() != null && !((ImageView)node).getImage().getUrl().contains("ures")) {
                for (Egyseg e: Main.game.vasarol.getEgysegek()) {
                    if (((ImageView)node).getImage().getUrl().contains(e.getNev())) {
                        Main.game.map[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = e;
                    }
                }
            }
        }
        // TODO: nem tette le az osszes egyseget
        Main.game.printMap();
        Main.game.letesz = null;
        if (Main.game.player2.jatekos && Main.game.vasarol != Main.game.player2) {
            Main.game.vasarol = Main.game.player2;
            fxmlLoader = new FXMLLoader(Main.class.getResource("Shop.fxml"));
        }
        else {
            if (!Main.game.player2.jatekos) Main.game.automataPenzkolto();
            Main.game.vasarol = null;
            fxmlLoader = new FXMLLoader(Main.class.getResource("Game.fxml"));
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    //endregion

    @FXML
    GridPane csatater;

    //region jatekinput
    @FXML
    Button p1Varakozas;
    @FXML
    Button p1Villam;
    @FXML
    Button p1Tuz;
    @FXML
    Button p1Pajzs;
    @FXML
    Button p1Ero;
    @FXML
    Button p1Felt;
    @FXML
    ImageView p1Hos;

    @FXML
    Button p2Varakozas;
    @FXML
    Button p2Villam;
    @FXML
    Button p2Tuz;
    @FXML
    Button p2Pajzs;
    @FXML
    Button p2Ero;
    @FXML
    Button p2Felt;
    @FXML
    ImageView p2Hos;
    //endregion

    //region disableInputs 0: hos1    1: hos2    2: e1    3: e2
    private void disableInputs(int option) {
        if (option == 4) {
            disableAll(new Node[]{p1Varakozas, p1Villam, p1Tuz, p1Pajzs, p1Ero, p1Felt, p2Varakozas, p2Villam, p2Tuz, p2Pajzs, p2Ero, p2Felt});
        }
        else if (option % 2 == 0) {
            disableAll(new Node[]{p2Varakozas, p2Villam, p2Tuz, p2Pajzs, p2Ero, p2Felt});
            if (option == 0) {
                disableAll(new Node[]{p1Varakozas});
                enableSpells(Main.game.player1, new Node[]{p1Villam, p1Tuz, p1Pajzs, p1Ero, p1Felt});
            }
            else {
                disableAll(new Node[]{p1Villam, p1Tuz, p1Pajzs, p1Ero, p1Felt});
                enableAll(new Node[]{p1Varakozas});
            }
        }
        else {
            disableAll(new Node[]{p1Varakozas, p1Villam, p1Tuz, p1Pajzs, p1Ero, p1Felt});
            if (option == 1) {
                disableAll(new Node[]{p2Varakozas});
                enableSpells(Main.game.player2, new Node[]{p2Villam, p2Tuz, p2Pajzs, p2Ero, p2Felt});
            }
            else {
                disableAll(new Node[]{p2Villam, p2Tuz, p2Pajzs, p2Ero, p2Felt});
                enableAll(new Node[]{p2Varakozas});
            }
        }
    }

    private void disableAll(Node[] nodes) {
        for (Node n: nodes) {
            n.setDisable(true);
            n.setOpacity(0);
        }
    }

    private void enableAll(Node[] nodes) {
        for (Node n: nodes) {
            n.setDisable(false);
            n.setOpacity(1);
        }
    }

    private void enableSpells(Hos hos, Node[] nodes) {
        for (int i = 0; i < 5; i++) {
            if (hos.getVarazslatok()[i].isVan() && hos.getVarazslatok()[i].getMana() <= hos.getMana()) {
                nodes[i].setDisable(false);
                nodes[i].setOpacity(1);
            }
            else {
                nodes[i].setDisable(true);
                nodes[i].setOpacity(0.5);
            }
        }
    }
    //endregion

    private void kepForgato(ImageView imgW, Hos hos, Image img) {
         if (hos == Main.game.player2) imgW.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
         else imgW.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
         imgW.setImage(img);
    }

    @FXML
    protected void clickSelection(MouseEvent event) throws IOException {
        if (((ImageView)event.getSource()).getImage() == null) return;
        String url = ((ImageView)event.getSource()).getImage().getUrl();
        if (url.contains("sajat")) {
            if (event.getSource() == p1Hos) {
                disableInputs(0);
                select(new Pont(-1, -1), 0);
            }
            else if (event.getSource() == p2Hos && Main.game.player2.jatekos) {
                disableInputs(1);
                select(new Pont(-2, -2), 0);
            }
            else {
                Hos hos = Main.game.getEgyseg(new Pont(GridPane.getRowIndex((Node)event.getSource()), GridPane.getColumnIndex((Node)event.getSource()))).getVezer();
                if (hos == Main.game.player1) {
                    if (sVarazs == -1) disableInputs(2);
                    select(new Pont(GridPane.getRowIndex((Node)event.getSource()), GridPane.getColumnIndex((Node)event.getSource())), 0);
                }
                else if (Main.game.player2.jatekos) {
                    if (sVarazs == -1) disableInputs(3);
                    select(new Pont(GridPane.getRowIndex((Node)event.getSource()), GridPane.getColumnIndex((Node)event.getSource())), 0);
                }
            }
        }
        else if (url.contains("mezo")) {
            select(new Pont(GridPane.getRowIndex((Node)event.getSource()), GridPane.getColumnIndex((Node)event.getSource())), 1);
        }
        else if (url.contains("ellenseg")) {
            select(new Pont(GridPane.getRowIndex((Node)event.getSource()), GridPane.getColumnIndex((Node)event.getSource())), 2);
        }
    }

    private Pont sEgyseg;
    private int sVarazs = -1;

    private void select(Pont p, int option) throws IOException {
        if (sVarazs == -1) {
            if (option == 0) {
                deselect(false);
                sEgyseg = p;
                if (p.row == p.col && (p.row == -1 || p.row == -2)) {
                    System.out.println("hos kivalasztva");
                    for (Node node : csatater.getChildren()) {
                        if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && ((ImageView) node).getImage() == null) {
                            Egyseg masik = Main.game.getEgyseg(new Pont(GridPane.getRowIndex(node), GridPane.getColumnIndex(node)));
                            if (masik != null && masik.getVezer() != (p.row == -1 ? Main.game.player1 : Main.game.player2)) {
                                ((ImageView) node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/ellenseg.png"));
                            }
                        }
                    }
                }
                else {
                    System.out.println(p + " egyseg kivalasztva");
                    Egyseg e = Main.game.getEgyseg(p);
                    Set<Pont> lephet = Main.game.hovaLephet(p, e.getSebesseg());
                    for (Node node : csatater.getChildren()) {
                        if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && ((ImageView) node).getImage() == null) {
                            Egyseg masik = Main.game.getEgyseg(new Pont(GridPane.getRowIndex(node), GridPane.getColumnIndex(node)));
                            if (masik != null && masik.getVezer() != e.getVezer() && (e.isTavolsagi() || Math.abs(p.row - GridPane.getRowIndex(node)) + Math.abs(p.col - GridPane.getColumnIndex(node)) == 1)) {
                                ((ImageView)node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/ellenseg.png"));
                            }
                            else {
                                for (Pont l: lephet) {
                                    if (GridPane.getRowIndex(node) == l.row && GridPane.getColumnIndex(node) == l.col) {
                                        ((ImageView)node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/mezo.png"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (option == 1) {
                System.out.println(p + " mezo kivalasztva");
                    Egyseg e = Main.game.getEgyseg(sEgyseg);
                    System.out.println(e.getNev() + " lepett");
                    for (Node node : csatater.getChildren()) {
                        if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && ((ImageView) node).getImage() != null) {
                            if (GridPane.getRowIndex(node) == p.row && GridPane.getColumnIndex(node) == p.col && ((ImageView) node).getImage().getUrl().contains("ures")) {
                                kepForgato((ImageView) node, e.getVezer(), new Image("file:src/main/resources/com/progegy/kotprog/" + e.getNev() + ".png"));
                                Main.game.map[p.row][p.col] = e;
                                Main.game.getEgyseg(p).lepett();
                            }
                            else if (GridPane.getRowIndex(node) == sEgyseg.row && GridPane.getColumnIndex(node) == sEgyseg.col && ((ImageView) node).getImage().getUrl().contains(e.getNev())) {
                                ((ImageView) node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/ures.png"));
                                Main.game.map[sEgyseg.row][sEgyseg.col] = null;
                            }
                        }
                    }
                selectNext();
            }
            else {
                System.out.println(p + " ellenseg kivalasztva");
                if (sEgyseg.row == sEgyseg.col && (sEgyseg.row == -1 || sEgyseg.row == -2)) {
                    (sEgyseg.row == -1 ? Main.game.player1 : Main.game.player2).tamad(Main.game.getEgyseg(p));
                }
                else Main.game.getEgyseg(sEgyseg).tamad(Main.game.getEgyseg(p));
                halott(p);
                if (sEgyseg.row != -1 && sEgyseg.col != -2) halott(sEgyseg);
                selectNext();
            }
        }
        else {  // varazslatok
            System.out.println("varazslas");
            Hos hos = (sEgyseg.row == -1 ? Main.game.player1 : Main.game.player2);
            List<Pont> sebzett = hos.getVarazslatok()[sVarazs].varazs(p);
            if (sebzett.size() != 0) {
                for (Pont egyseg: sebzett) {
                    halott(egyseg);
                }
            }
            sVarazs = -1;
            selectNext();
        }
    }

    private void halott(Pont p) {
        if (!Main.game.getEgyseg(p).elMeg()) {
            for (Node node : csatater.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) == p.row &&
                        GridPane.getColumnIndex(node) == p.col && ((ImageView) node).getImage() != null && ((ImageView) node).getImage().getUrl().contains(Main.game.getEgyseg(p).getNev())) {
                    ((ImageView) node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/ures.png"));
                }
            }
            Main.game.map[p.row][p.col] = null;
        }
    }

    private void deselect(boolean zoldetIs) {
        for (Node node : csatater.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && ((ImageView) node).getImage() != null &&
                    (((ImageView) node).getImage().getUrl().contains("mezo") || ((ImageView) node).getImage().getUrl().contains("ellenseg") || (zoldetIs && ((ImageView) node).getImage().getUrl().contains("sajat")))) {
                ((ImageView) node).setImage(null);
            }
        }
        if (zoldetIs) {
            p1Hos.setImage(null);
            p2Hos.setImage(null);
        }
    }

    private void selectNext() throws IOException {
        if (!Main.game.player1.elMeg() || !Main.game.player2.elMeg()) {
            gameEnd();
            return;
        }
        deselect(true);
        disableInputs(4);
        Pont p = Main.game.getKovetkezo();
        for (Node node: csatater.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) == p.row &&
                    GridPane.getColumnIndex(node) == p.col && ((ImageView)node).getImage() == null) {
                if(Main.game.getEgyseg(p).getVezer() == Main.game.player1 || (Main.game.getEgyseg(p).getVezer() == Main.game.player2 && Main.game.player2.jatekos)){
                    ((ImageView)node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/sajat.png"));
                }
            }
        }
        if (Main.game.getEgyseg(p).getVezer() == Main.game.player1) {
            System.out.println("player1 kovetkezik");
            if (Main.game.player1.getUtolsoLepes() < Main.game.korSzam) p1Hos.setImage(new Image("file:src/main/resources/com/progegy/kotprog/sajat.png"));
        }
        else {
            System.out.println("player2 kovetkezik");
            if (Main.game.player2.jatekos) {
                if (Main.game.player2.getUtolsoLepes() < Main.game.korSzam) p2Hos.setImage(new Image("file:src/main/resources/com/progegy/kotprog/sajat.png"));
            }
            else {
                Main.game.botTevekenyseg();
                updateMap();
                selectNext();
            }
        }
    }

    private void updateMap() {
        for (Node node: csatater.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && ((ImageView) node).getImage() != null && !((ImageView) node).getImage().getUrl().contains("ures")) {
                ((ImageView) node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/ures.png"));
            }
        }
        for (Node node: csatater.getChildren()) {
            for (Pont e: Main.game.getEgysegek()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) == e.row &&
                        GridPane.getColumnIndex(node) == e.col && ((ImageView) node).getImage() != null && ((ImageView) node).getImage().getUrl().contains("ures")) {
                    kepForgato((ImageView) node, Main.game.getEgyseg(e).getVezer(), new Image("file:src/main/resources/com/progegy/kotprog/" + Main.game.getEgyseg(e).getNev() + ".png"));
                }
            }
        }
    }

    //region varazslatok
    @FXML
    protected void varakozasGomb(ActionEvent event) throws IOException {
        Main.game.getEgyseg(sEgyseg).lepett();
        selectNext();
    }

    @FXML
    protected void villamGomb(ActionEvent event) {
        sVarazs = 0;
        spellSelect(selectEllen(), false);
    }

    @FXML
    protected void tuzGomb(ActionEvent event) {
        sVarazs = 1;
        deselect(true);
        for (Node node : csatater.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && ((ImageView) node).getImage() == null) {
                ((ImageView) node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/mezo.png"));
            }
        }
    }

    @FXML
    protected void pajzsGomb(ActionEvent event) {
        sVarazs = 2;
        spellSelect(selectSajat(), true);
    }

    @FXML
    protected void eroGomb(ActionEvent event) {
        sVarazs = 3;
        spellSelect(selectSajat(), true);
    }

    @FXML
    protected void feltGomb(ActionEvent event) {
        sVarazs = 4;
        spellSelect(selectSajat(), true);
    }

    private List<Pont> selectSajat() {
        List<Pont> select = new ArrayList<>();
        for (Pont p: Main.game.getEgysegek()) {
            if (Main.game.getEgyseg(p).getVezer() == (sEgyseg.row == -1 ? Main.game.player1 : Main.game.player2)) select.add(p);
        }
        return select;
    }

    private List<Pont> selectEllen() {
        List<Pont> select = new ArrayList<>();
        for (Pont p: Main.game.getEgysegek()) {
            if (Main.game.getEgyseg(p).getVezer() != (sEgyseg.row == -1 ? Main.game.player1 : Main.game.player2)) select.add(p);
        }
        return select;
    }

    private void spellSelect(List<Pont> egysegek, boolean sajat) {
        deselect(true);
        for (Pont p: egysegek) {
            for (Node node : csatater.getChildren()) {
                if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) == p.row &&
                        GridPane.getColumnIndex(node) == p.col && ((ImageView) node).getImage() == null) {
                        ((ImageView) node).setImage(new Image("file:src/main/resources/com/progegy/kotprog/" + (sajat ? "sajat" : "ellenseg") + ".png"));
                }
            }
        }
    }
    //endregion

    @FXML
    protected void leirasStatus(MouseEvent event) {
        if (event.getSource().getClass() == ImageView.class) {
            if (event.getSource() == p1Hos) {
                leiras.setText(Main.game.player1.printStatus());
            }
            else if (event.getSource() == p2Hos) {
                leiras.setText(Main.game.player2.printStatus());
            }
            else {
                Egyseg e = Main.game.getEgyseg(new Pont(GridPane.getRowIndex((Node) event.getSource()), GridPane.getColumnIndex((Node) event.getSource())));
                if (e != null) leiras.setText(e.printStatus());
            }
        }
        else {
            String id = ((Node)event.getSource()).getId();
            if (id.contains("Varakozas")) leiras.setText("A kiválasztott egység kimarad egy körből");
            if (id.contains("p1")) {
                switch (id.split("1")[1]) {
                    case "Villam":
                        leiras.setText(Main.game.player1.getVarazslatok()[0].printStatus());
                        break;
                    case "Tuz":
                        leiras.setText(Main.game.player1.getVarazslatok()[1].printStatus());
                        break;
                    case "Pajzs":
                        leiras.setText(Main.game.player1.getVarazslatok()[2].printStatus());
                        break;
                    case "Ero":
                        leiras.setText(Main.game.player1.getVarazslatok()[3].printStatus());
                        break;
                    case "Felt":
                        leiras.setText(Main.game.player1.getVarazslatok()[4].printStatus());
                        break;
                }
            }
            else if (id.contains("p2")) {
                switch (id.split("2")[1]) {
                    case "Villam":
                        leiras.setText(Main.game.player2.getVarazslatok()[0].printStatus());
                        break;
                    case "Tuz":
                        leiras.setText(Main.game.player2.getVarazslatok()[1].printStatus());
                        break;
                    case "Pajzs":
                        leiras.setText(Main.game.player2.getVarazslatok()[2].printStatus());
                        break;
                    case "Ero":
                        leiras.setText(Main.game.player2.getVarazslatok()[3].printStatus());
                        break;
                    case "Felt":
                        leiras.setText(Main.game.player2.getVarazslatok()[4].printStatus());
                        break;
                }
            }
        }
    }

    //region vege
    @FXML
    Label gyoztes;

    private void gameEnd() throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("End.fxml"));
        stage = (Stage)csatater.getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void menubeVissza(ActionEvent event) throws IOException {
        Main.game = null;
        System.out.println("fomenu betoltese");
        fxmlLoader = new FXMLLoader(Main.class.getResource("Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @FXML
    protected void kilepesGomb(ActionEvent event) {
        System.out.println("kilepes a jatekbol");
        System.exit(0);
    }
    //endregion
}
