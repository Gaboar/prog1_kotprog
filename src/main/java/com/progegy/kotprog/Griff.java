package com.progegy.kotprog;

public class Griff extends Egyseg {

    public Griff(Hos vezer, int db) {
        super("griff", vezer, 15, 5, 10, 30, 7, 15, db);
    }

    @Override
    public void kepesseg() {}

    @Override
    public String toString() {
        return "Ár: 15;  Sebzés: 5-10;  Életerő: 30;  Sebesség: 7;  Kezdeményezés: 15\nKépesség: az egység tetszőlegesen sok támadónak vissza tud támadni";
    }
}
