package com.progegy.kotprog;

import java.util.List;

/**
 * Varazslat class
 * @author Nagy Gábor
 * @version 1.0
 */

public abstract class Varazslat {

    private final int ar, mana;
    private final boolean van;
    private final Hos tulaj;

    /**
     * varazs
     *
     * @param celpont mezo amit celoz a varazslat
     * @return megsebzett egysegek
     */

    public abstract List<Pont> varazs(Pont celpont);

    /**
     * Varazslat
     *
     * @param tulaj a hos akie a varazslat
     * @param ar mennyibe kerul
     * @param mana mennyi a manakoltsege
     * @param van a jatekos megvette-e a varazslatot
     */

    public Varazslat(Hos tulaj, int ar, int mana, boolean van) {
        this.tulaj = tulaj;
        this.ar = ar;
        this.mana = mana;
        this.van = van;
    }

    /**
     * getTulaj
     *
     * @return a hos akie a varazslat
     */

    public Hos getTulaj() {
        return tulaj;
    }

    /**
     * getAr
     *
     * @return mennyibe kerul
     */

    public int getAr() {
        return ar;
    }

    /**
     * getMana
     *
     * @return mennyi a manakoltsege
     */

    public int getMana() {
        return mana;
    }

    /**
     * isVan
     *
     * @return a jatekos megvette-e a varazslatot
     */

    public boolean isVan() {
        return van;
    }

    @Override
    public String toString() {
        return "Ár: " + ar + ";  Mana: " + mana;
    }

    public String printStatus() {
        return "Mana: " + mana;
    }
}
