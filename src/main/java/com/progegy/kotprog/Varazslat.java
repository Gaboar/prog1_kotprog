package com.progegy.kotprog;

public abstract class Varazslat {

    private final int ar, mana;
    int db;

    public abstract void Varazs(Egyseg celpont);

    public Varazslat(int ar, int mana, int db) {
        this.ar = ar;
        this.mana = mana;
        this.db = db;
    }

    public int getAr() {
        return ar;
    }

    public int getMana() {
        return mana;
    }
}
