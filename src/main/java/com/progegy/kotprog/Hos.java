package com.progegy.kotprog;

import com.progegy.kotprog.egyseg.*;
import com.progegy.kotprog.varazslat.*;

public class Hos {
    public final boolean jatekos;
    private int tamadas, vedekezes, varazsero, tudas, moral, szerencse;
    private int arany, mana;
    private int utolsoLepes = 0;
    private Egyseg[] egysegek = { new Foldmuves(this, 0), new Ijasz(this, 0), new Lovag(this, 0), new Magus(this, 0), new Griff(this,0) };
    private Varazslat[] varazslatok = {new Villamcsapas(this, false), new Tuzlabda(this, false), new Pajzs(this, false), new Erosites(this, false), new Feltamasztas(this, false)};

    public Hos(int arany, boolean jatekos) {
        this.arany = arany;
        this.jatekos = jatekos;
    }

    public boolean elMeg() {
        for (Egyseg e: egysegek) {
            if (e.elMeg()) return true;
        }
        return false;
    }

    public void lepett() {
        this.utolsoLepes = Main.game.korSzam;
    }

    public void tamad(Egyseg kit) {
        kit.serul(tamadas * 10, true, false, null);
        lepett();
    }

    public int getArany() {
        return arany;
    }

    public void setArany(int arany) {
        this.arany = arany;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getUtolsoLepes() {
        return utolsoLepes;
    }

    public Egyseg[] getEgysegek() {
        return egysegek;
    }

    public void setEgysegek(int foldm, int ijasz, int lovag, int magus, int griff) {
        this.egysegek = new Egyseg[] { new Foldmuves(this, foldm), new Ijasz(this, ijasz), new Lovag(this, lovag), new Magus(this, magus), new Griff(this, griff) };
    }

    public Varazslat[] getVarazslatok() {
        return varazslatok;
    }

    public void setVarazslatok(boolean villam, boolean tuz, boolean pajzs, boolean ero, boolean felt) {
        this.varazslatok = new Varazslat[] {new Villamcsapas(this, villam), new Tuzlabda(this, tuz), new Pajzs(this, pajzs), new Erosites(this, ero), new Feltamasztas(this, felt)};
    }

    public int[] getTulajdonsagok() {
        return new int[] {tamadas, vedekezes, varazsero, tudas, moral, szerencse};  //0: tamadas; 1: vedekezes; 2: varazsero; 3: tudas; 4: moral; 5: szerencse
    }

    public void setTulajdonsagok(int tamadas, int vedekezes, int varazsero, int tudas, int moral, int szerencse) {
        this.tamadas = tamadas;
        this.vedekezes = vedekezes;
        this.varazsero = varazsero;
        this.tudas = tudas;
        this.moral = moral;
        this.szerencse = szerencse;
        this.mana = tudas * 10;
    }

    public String printStatus() {
        return "Támadás: " + tamadas + "\nVédekezés: " + vedekezes + "\nVarázserő: " + varazsero + "\nSzenercse: " + szerencse + "\n\nMana: " + mana;
    }
}
