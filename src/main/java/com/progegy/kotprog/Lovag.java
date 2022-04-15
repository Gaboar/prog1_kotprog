package com.progegy.kotprog;

public class Lovag extends Egyseg {

    public Lovag(Hos vezer, int db) {
        super("lovag", vezer, 10, 9, 14, 22, 3, 14, false, db);
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
    public String toString() {
        return super.toString() + "\nKépesség: az egység tetszőlegesen sok támadónak vissza tud támadni";
    }
}
