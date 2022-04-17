package com.progegy.kotprog.egyseg;

import com.progegy.kotprog.Egyseg;
import com.progegy.kotprog.Hos;

public class Foldmuves extends Egyseg {

    public Foldmuves(Hos vezer, int db) {
        super("foldmuves", vezer, 2, 1, 1, 3, 4, 8, false, db);
    }

    @Override
    public void tamad(Egyseg kit) {
        double sebzes = getSebzes() * (1 + 0.1 * getVezer().getTulajdonsagok()[0]);
        System.out.println(getNev() + " sebzett: " + sebzes);
        kit.serul(sebzes, false, true, this);
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
        return super.toString() + "\nKépesség: nincs";
    }
}
