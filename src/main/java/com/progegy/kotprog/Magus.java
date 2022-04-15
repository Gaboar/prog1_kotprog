package com.progegy.kotprog;

import java.util.Random;

public class Magus extends Egyseg {

    public Magus(Hos vezer, int db) {
        super("magus", vezer, 10, 7, 11, 12, 4, 10, true, db);
    }

    @Override
    public void tamad(Egyseg kit) {
        double sebzes = getSebzes() * (1 + 0.1 * getVezer().getTulajdonsagok()[0]);
        System.out.println(getNev() + " sebzett: " + sebzes);
        kit.serul(sebzes, false);
        Random r = new Random();
        Main.game.getSzomszedok(kit).get(Main.game.getSzomszedok(kit).size() != 0 ? r.nextInt(Main.game.getSzomszedok(kit).size()) : 0).serul(sebzes, false);
        lepett();
    }

    @Override
    public void serul(double mennyit, boolean tipus) { //type: true=truedamage
        if (tipus) {
            setOsszelet(getOsszelet() - (int)Math.round(mennyit));
            System.out.println(getNev() + " serult: " + (int)Math.round(mennyit));
        }
        else {
            setOsszelet(getOsszelet() - (int)Math.round(mennyit * (1 - 0.05 * getVezer().getTulajdonsagok()[1])));
            System.out.println(getNev() + " serult: " + (int)Math.round(mennyit * (1 - 0.05 * getVezer().getTulajdonsagok()[1])));
        }
        System.out.println("maradek eletero: " + getOsszelet());
    }

    @Override
    public String toString() {
        return super.toString() + "\nKépesség: az egység távolsági támadást hajt végre, amely megsebez egy másik, közvetlen a célpont mellett áttó ellenséget is";
    }
}
