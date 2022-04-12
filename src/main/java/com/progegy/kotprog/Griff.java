package com.progegy.kotprog;

public class Griff extends Egyseg {

    public Griff(Hos vezer, int db) {
        super("griff", vezer, 15, 5, 10, 30, 7, 15, db);
    }

    @Override
    public void kepesseg() {}

    @Override
    public String toString() {
        return super.toString() + "\nKépesség: az egység tetszőlegesen sok támadónak vissza tud támadni";
    }
}
