package com.progegy.kotprog;

public class Hos {
    private int tamadas, vedekezes, varazsero, tudas, moral, szerencse;
    private int arany, mana;
    private Egyseg[] egysegek = { new Foldmuves(0), new Ijasz(0), new Lovag(0), new Magus(0), new Griff(0) };
    private Varazslat[] varazslatok = {new Villamcsapas(0), new Tuzlabda(0), new Pajzs(0), new Erosites(0), new Feltamasztas(0)};

    public Hos(int arany) {
        this.arany = arany;
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
        this.egysegek = new Egyseg[] { new Foldmuves(foldm), new Ijasz(ijasz), new Lovag(lovag), new Magus(magus), new Griff(griff) };
    }

    public Varazslat[] getVarazslatok() {
        return varazslatok;
    }

    public void setVarazslatok(int villam, int tuz, int pajzs, int ero, int felt) {
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
