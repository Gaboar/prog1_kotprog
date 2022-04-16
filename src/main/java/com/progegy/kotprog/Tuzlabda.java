package com.progegy.kotprog;

public class Tuzlabda extends Varazslat {

    public Tuzlabda(Hos tulaj, boolean van) {
        super(tulaj, 120, 9, van);
    }

    @Override
    public void Varazs(Pont celpont) {
        for (Pont p: Main.game.getEgysegekInArea(celpont, 1)) {
            Main.game.getEgyseg(p).serul(getTulaj().getTulajdonsagok()[2] * 20, true, false, null);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott mező körüli 3x3-as területen lévő összes (saját, illetve ellenséges) egységre (varázserő * 20) sebzés okozása";
    }
}
