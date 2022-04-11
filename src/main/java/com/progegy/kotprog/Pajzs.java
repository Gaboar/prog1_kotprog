package com.progegy.kotprog;

public class Pajzs extends Varazslat {

    public Pajzs(boolean van) {
        super(60, 6, van);
    }

    @Override
    public void Varazs(Egyseg celpont) {

    }

    @Override
    public String toString() {
        return "Ár: 60;  Mana: 6\nLeírás: egy kiválasztott saját egység pajzzsal védése egy körig\nMaximális kivédhető sebzés: (varázserő * 50)";
    }
}
