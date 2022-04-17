package com.progegy.kotprog;

import java.util.ArrayList;
import java.util.List;

public class Erosites extends Varazslat {

    public Erosites(Hos tulaj, boolean van) {
        super(tulaj, 120, 8, van);
    }

    @Override
    public List<Pont> varazs(Pont celpont) {
        List<Pont> sebzett = new ArrayList<>();
        Main.game.getEgyseg(celpont).setErosites(true);
        getTulaj().setMana(getTulaj().getMana() - getMana());
        getTulaj().lepett();
        return sebzett;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott saját egység sebzésének növelése (varázserő * 10%) egy körig";
    }

    public String printStatus() {
        return super.printStatus() + "\nLeírás: egy kiválasztott saját egység sebzésének növelése " + getTulaj().getTulajdonsagok()[2] * 10 + "% egy körig";
    }
}
