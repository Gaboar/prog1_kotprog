package com.progegy.kotprog;

import java.util.List;
import java.util.Random;

public class Magus extends Egyseg {

    public Magus(Hos vezer, int db) {
        super("magus", vezer, 10, 7, 11, 12, 4, 10, true, db);
    }

    @Override
    public void tamad(Egyseg kit) {
        double sebzes = getSebzes() * (1 + 0.1 * getVezer().getTulajdonsagok()[0]);
        System.out.println(getNev() + " sebzett: " + sebzes);
        kit.serul(sebzes, false, true, this);
        Random r = new Random();
        System.out.println("masodik varazslat");
        List<Egyseg> szomszedok = Main.game.getSzomszedok(kit);
        if (szomszedok.size() != 0) szomszedok.get(r.nextInt(szomszedok.size())).serul(sebzes, false, true, this);
        lepett();
    }

    @Override
    public void serul(double mennyit, boolean tipus, boolean visszaT, Egyseg tamado) { //type: true=truedamage
        int serules;
        if (tipus) serules = (int)Math.round(mennyit);
        else serules = (int)Math.round(mennyit * (1 - 0.05 * getVezer().getTulajdonsagok()[1]));
        setOsszelet(getOsszelet() - Math.max(0, serules - getPajzs()));
        setPajzs(getPajzs() - serules);
        System.out.println(getNev() + " serult: " + Math.max(0, serules - getPajzs()));
        System.out.println("maradek eletero: " + getOsszelet() + "; pajzs: " + getPajzs());
    }

    @Override
    public String toString() {
        return super.toString() + "\nKépesség: az egység távolsági támadást hajt végre, amely megsebez egy másik, közvetlen a célpont mellett áttó ellenséget is";
    }
}
