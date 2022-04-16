package com.progegy.kotprog;

public class Villamcsapas extends Varazslat {

    public Villamcsapas(Hos tulaj, boolean van) {
        super(tulaj, 60, 5, van);
    }

    @Override
    public void Varazs(Pont celpont) {
        Main.game.getEgyseg(celpont).serul(getTulaj().getTulajdonsagok()[2] * 30, true, false, null);
    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott ellenséges egységre (varázserő * 30) sebzés okozása";
    }
}
