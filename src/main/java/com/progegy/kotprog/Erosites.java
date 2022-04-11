package com.progegy.kotprog;

public class Erosites extends Varazslat {

    public Erosites(int db) {
        super(120, 8, db);
    }

    @Override
    public void Varazs(Egyseg celpont) {

    }

    @Override
    public String toString() {
        return "Ár: 120;  Mana: 8\nLeírás: egy kiválasztott saját egység sebzésének növelése (varázserő * 10%) egy körig";
    }
}
