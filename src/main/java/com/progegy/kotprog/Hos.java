package com.progegy.kotprog;

public class Hos {
    public final boolean jatekos;
    private int tamadas, vedekezes, varazsero, tudas, moral, szerencse;
    private int arany, mana;
    private Egyseg[] egysegek = { new Foldmuves(this, 0), new Ijasz(this, 0), new Lovag(this, 0), new Magus(this, 0), new Griff(this,0) };
    private Varazslat[] varazslatok = {new Villamcsapas(false), new Tuzlabda(false), new Pajzs(false), new Erosites(false), new Feltamasztas(false)};

    public Hos(int arany, boolean jatekos) {
        this.arany = arany;
        this.jatekos = jatekos;
    }

    public int getArany() {
        return arany;
    }

    public void setArany(int arany) {
        this.arany = arany;
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
        this.varazslatok = new Varazslat[] {new Villamcsapas(villam), new Tuzlabda(tuz), new Pajzs(pajzs), new Erosites(ero), new Feltamasztas(felt)};
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
}
