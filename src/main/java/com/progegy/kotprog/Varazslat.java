package com.progegy.kotprog;

public abstract class Varazslat {

    private final int ar, mana;
    private final boolean van;
    private final Hos tulaj;

    public abstract void Varazs(Pont celpont);

    public Varazslat(Hos tulaj, int ar, int mana, boolean van) {
        this.tulaj = tulaj;
        this.ar = ar;
        this.mana = mana;
        this.van = van;
    }

    public Hos getTulaj() {
        return tulaj;
    }

    public int getAr() {
        return ar;
    }

    public int getMana() {
        return mana;
    }

    public boolean isVan() {
        return van;
    }

    @Override
    public String toString() {
        return "Ár: " + ar + ";  Mana: " + mana;
    }
}
