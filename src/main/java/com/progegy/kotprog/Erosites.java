package com.progegy.kotprog;

public class Erosites extends Varazslat {

    public Erosites(Hos tulaj, boolean van) {
        super(tulaj, 120, 8, van);
    }

    @Override
    public void Varazs(Pont celpont) {
        Main.game.getEgyseg(celpont).setErosites(true);
    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott saját egység sebzésének növelése (varázserő * 10%) egy körig";
    }
}
