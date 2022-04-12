package com.progegy.kotprog;

public class Lovag extends Egyseg {

    public Lovag(Hos vezer, int db) {
        super("lovag", vezer, 10, 9, 14, 22, 3, 14, db);
    }

    @Override
    public void kepesseg() {}

    @Override
    public String toString() {
        return super.toString() + "\nKépesség: az egység tetszőlegesen sok támadónak vissza tud támadni";
    }
}
