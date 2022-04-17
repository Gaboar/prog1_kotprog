package com.progegy.kotprog;

import java.util.ArrayList;
import java.util.List;

public class Feltamasztas extends Varazslat {

    public Feltamasztas(Hos tulaj, boolean van) {
        super(tulaj, 120, 6, van);
    }

    @Override
    public List<Pont> varazs(Pont celpont) {
        List<Pont> sebzett = new ArrayList<>();
        Main.game.getEgyseg(celpont).setOsszelet(Main.game.getEgyseg(celpont).getOsszelet() + getTulaj().getTulajdonsagok()[2] * 50);
        getTulaj().setMana(getTulaj().getMana() - getMana());
        getTulaj().lepett();
        return sebzett;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott saját egység feltámasztása\nMaximális gyógyítás mértéke: (varázserő * 50)";
    }

    public String printStatus() {
        return super.printStatus() + "\nLeírás: egy kiválasztott saját egység feltámasztása\nMaximális gyógyítás mértéke: " + getTulaj().getTulajdonsagok()[2] * 50;
    }
}
