package com.progegy.kotprog;

public class Ijasz extends Egyseg {

    public Ijasz(Hos vezer, int db) {
        super("ijasz", vezer, 6, 2, 4, 7, 4, 9, true, db);
    }

    @Override
    public void tamad(Egyseg kit) {
        double sebzes = getSebzes() * (1 + 0.1 * getVezer().getTulajdonsagok()[0]);
        System.out.println(getNev() + " sebzett: " + sebzes);
        kit.serul(sebzes, false);
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
