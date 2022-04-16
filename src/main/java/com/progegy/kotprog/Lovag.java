package com.progegy.kotprog;

public class Lovag extends Egyseg {

    public Lovag(Hos vezer, int db) {
        super("lovag", vezer, 10, 9, 14, 22, 3, 14, false, db);
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
        if (elMeg() && visszaT && Math.abs(Main.game.getPozicio(tamado).row - Main.game.getPozicio(this).row) + Math.abs(Main.game.getPozicio(tamado).col - Main.game.getPozicio(this).col) <= 1) {
            double sebzes = getSebzes() * (1 + 0.1 * getVezer().getTulajdonsagok()[0]);
            System.out.println(getNev() + " sebzett: " + sebzes);
            tamado.serul(sebzes, false, false, this);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nKépesség: az egység tetszőlegesen sok támadónak vissza tud támadni";
    }
}
