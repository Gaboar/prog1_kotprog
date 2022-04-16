package com.progegy.kotprog;

public class Ijasz extends Egyseg {

    public Ijasz(Hos vezer, int db) {
        super("ijasz", vezer, 6, 2, 4, 7, 4, 9, true, db);
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
    public boolean isTavolsagi() {
        for (Egyseg e: Main.game.getSzomszedok(this)) {
            if (getVezer() != e.getVezer()) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "\nKépesség: az egység távolsági támadást tud végrehajtani, de csak abban az esetben, ha nincs ellenséges egység a közvetlen közelében";
    }
}
