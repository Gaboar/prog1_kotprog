package com.progegy.kotprog;

public class Feltamasztas extends Varazslat {

    public Feltamasztas(boolean van) {
        super(120, 6, van);
    }

    @Override
    public void Varazs(Egyseg celpont) {

    }

    @Override
    public String toString() {
        return "Ár: 120;  Mana: 6\nLeírás: egy kiválasztott saját egység feltámasztása\nMaximális gyógyítás mértéke: (varázserő * 50)";
    }
}
