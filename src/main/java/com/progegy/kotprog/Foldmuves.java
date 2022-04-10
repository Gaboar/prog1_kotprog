package com.progegy.kotprog;

public class Foldmuves extends Egyseg {

    public Foldmuves(int db) {
        super(2, 1, 1, 3, 4, 8, db);
    }

    @Override
    public void kepesseg() {}

    @Override
    public String toString() {
        return "Ár: 2; Sebzés: 1-1; Életerő: 3; Sebesség: 4; Kezdeményezés: 8\nKépesség: nincs";
    }
}
