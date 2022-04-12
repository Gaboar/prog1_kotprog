package com.progegy.kotprog;

public class Ijasz extends Egyseg {

    public Ijasz(Hos vezer, int db) {
        super("ijasz", vezer, 6, 2, 4, 7, 4, 9, db);
    }

    @Override
    public void kepesseg() {}

    @Override
    public String toString() {
        return super.toString() + "\nKépesség: az egység távolsági támadást tud végrehajtani, de csak abban az esetben, ha nincs ellenséges egység a közvetlen közelében";
    }
}
