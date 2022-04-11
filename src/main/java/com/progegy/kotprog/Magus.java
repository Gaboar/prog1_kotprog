package com.progegy.kotprog;

public class Magus extends Egyseg {

    public Magus(Hos vezer, int db) {
        super("magus", vezer, 10, 7, 11, 12, 4, 10, db);
    }

    @Override
    public void kepesseg() {}

    @Override
    public String toString() {
        return "Ár: 10;  Sebzés: 7-11;  Életerő: 12;  Sebesség: 4;  Kezdeményezés: 10\nKépesség: az egység távolsági támadást hajt végre, amely megsebez egy másik, közvetlen a célpont mellett áttó ellenséget is";
    }
}
