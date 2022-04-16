package com.progegy.kotprog;

public class Pajzs extends Varazslat {

    public Pajzs(Hos tulaj, boolean van) {
        super(tulaj, 60, 6, van);
    }

    @Override
    public void Varazs(Pont celpont) {
        Main.game.getEgyseg(celpont).setPajzs(getTulaj().getTulajdonsagok()[2] * 50);
    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott saját egység pajzzsal védése egy körig\nMaximális kivédhető sebzés: (varázserő * 50)";
    }
}
