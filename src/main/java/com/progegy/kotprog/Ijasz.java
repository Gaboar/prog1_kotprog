package com.progegy.kotprog;

public class Ijasz extends Egyseg {

    public Ijasz(int db) {
        super(6, 2, 4, 7, 4, 9, db);
    }

    @Override
    public void kepesseg() {}

    @Override
    public String toString() {
        return "Ár: 6; Sebzés: 2-4; Életerő: 7; Sebesség: 4; Kezdeményezés: 9\nKépesség: az egység távolsági támadást tud végrehajtani, de csak abban az\nesetben, ha nincs ellenséges egység a közvetlen közelében";
    }
}
