package com.progegy.kotprog;

import java.util.ArrayList;
import java.util.List;

public class Tuzlabda extends Varazslat {

    public Tuzlabda(Hos tulaj, boolean van) {
        super(tulaj, 120, 9, van);
    }

    @Override
    public List<Pont> varazs(Pont celpont) {
        List<Pont> sebzett = new ArrayList<>();
        for (Pont p: Main.game.getEgysegekInArea(celpont, 1)) {
            Main.game.getEgyseg(p).serul(getTulaj().getTulajdonsagok()[2] * 20, true, false, null);
            sebzett.add(p);
        }
        getTulaj().setMana(getTulaj().getMana() - getMana());
        getTulaj().lepett();
        return sebzett;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott mező körüli 3x3-as területen lévő összes (saját, illetve ellenséges) egységre (varázserő * 20) sebzés okozása";
    }

    public String printStatus() {
        return super.printStatus() + "\nLeírás: egy kiválasztott mező körüli 3x3-as területen lévő összes (saját, illetve ellenséges) egységre " + getTulaj().getTulajdonsagok()[2] * 20 + " sebzés okozása";
    }
}
