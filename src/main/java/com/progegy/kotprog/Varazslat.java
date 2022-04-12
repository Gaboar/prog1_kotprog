package com.progegy.kotprog;

public abstract class Varazslat {

    private final int ar, mana;
    private final boolean van;

    public abstract void Varazs(Egyseg celpont);

    public Varazslat(int ar, int mana, boolean van) {
        this.ar = ar;
        this.mana = mana;
        this.van = van;
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
