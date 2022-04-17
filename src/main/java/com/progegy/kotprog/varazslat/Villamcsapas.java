package com.progegy.kotprog.varazslat;

import com.progegy.kotprog.Hos;
import com.progegy.kotprog.Main;
import com.progegy.kotprog.Pont;
import com.progegy.kotprog.Varazslat;

import java.util.ArrayList;
import java.util.List;

public class Villamcsapas extends Varazslat {

    public Villamcsapas(Hos tulaj, boolean van) {
        super(tulaj, 60, 5, van);
    }

    @Override
    public List<Pont> varazs(Pont celpont) {
        List<Pont> sebzett = new ArrayList<>();
        Main.game.getEgyseg(celpont).serul(getTulaj().getTulajdonsagok()[2] * 30, true, false, null);
        sebzett.add(celpont);
        getTulaj().setMana(getTulaj().getMana() - getMana());
        getTulaj().lepett();
        return sebzett;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott ellenséges egységre (varázserő * 30) sebzés okozása";
    }

    public String printStatus() {
        return super.printStatus() + "\nLeírás: egy kiválasztott ellenséges egységre " + getTulaj().getTulajdonsagok()[2] * 30 + " sebzés okozása";
    }
}
