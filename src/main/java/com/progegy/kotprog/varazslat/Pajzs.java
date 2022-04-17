package com.progegy.kotprog.varazslat;

import com.progegy.kotprog.Hos;
import com.progegy.kotprog.Main;
import com.progegy.kotprog.Pont;
import com.progegy.kotprog.Varazslat;
import javafx.scene.effect.ImageInput;

import java.util.ArrayList;
import java.util.List;

public class Pajzs extends Varazslat {

    public Pajzs(Hos tulaj, boolean van) {
        super(tulaj, 60, 6, van);
    }

    @Override
    public List<Pont> varazs(Pont celpont) {
        List<Pont> sebzett = new ArrayList<>();
        Main.game.getEgyseg(celpont).setPajzs(getTulaj().getTulajdonsagok()[2] * 50);
        getTulaj().setMana(getTulaj().getMana() - getMana());
        getTulaj().lepett();
        return sebzett;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott saját egység pajzzsal védése egy körig\nMaximális kivédhető sebzés: (varázserő * 50)";
    }

    public String printStatus() {
        return super.printStatus() + "\nLeírás: egy kiválasztott saját egység pajzzsal védése egy körig\nMaximális kivédhető sebzés: " + getTulaj().getTulajdonsagok()[2] * 50;
    }
}
