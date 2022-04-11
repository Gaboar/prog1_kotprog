package com.progegy.kotprog;

public class Tuzlabda extends Varazslat {

    public Tuzlabda(boolean van) {
        super(120, 9, van);
    }

    @Override
    public void Varazs(Egyseg celpont) {

    }

    @Override
    public String toString() {
        return "Ár: 120;  Mana: 9\nLeírás: egy kiválasztott mező körüli 3x3-as területen lévő összes (saját, illetve ellenséges) egységre (varázserő * 20) sebzés okozása";
    }
}
