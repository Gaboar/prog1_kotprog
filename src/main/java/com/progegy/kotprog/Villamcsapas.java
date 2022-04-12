package com.progegy.kotprog;

public class Villamcsapas extends Varazslat {

    public Villamcsapas(boolean van) {
        super(60, 5, van);
    }

    @Override
    public void Varazs(Egyseg celpont) {

    }

    @Override
    public String toString() {
        return super.toString() + "\nLeírás: egy kiválasztott ellenséges egységre (varázserő * 30) sebzés okozása";
    }
}
