package com.progegy.kotprog;

public class Feltamasztas extends Varazslat {

    public Feltamasztas(Hos tulaj, boolean van) {
        super(tulaj, 120, 6, van);
    }

    @Override
    public void Varazs(Pont celpont) {
        Main.game.getEgyseg(celpont).setOsszelet(Main.game.getEgyseg(celpont).getOsszelet() + getTulaj().getTulajdonsagok()[2] * 50);
    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott saját egység feltámasztása\nMaximális gyógyítás mértéke: (varázserő * 50)";
    }
}
