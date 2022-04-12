package com.progegy.kotprog;

public class Foldmuves extends Egyseg {

    public Foldmuves(Hos vezer, int db) {
        super("foldmuves", vezer, 2, 1, 1, 3, 4, 8, db);
    }

    @Override
    public void kepesseg() {}

    @Override
    public String toString() {
        return super.toString() + "\nKépesség: nincs";
    }
}
