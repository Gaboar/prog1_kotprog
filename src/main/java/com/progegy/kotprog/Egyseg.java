package com.progegy.kotprog;

import java.util.Random;

public abstract class Egyseg {

    private final String nev;
    private final Hos vezer;
    private final int ar, sebzesMin, sebzesMax, eletero, sebesseg, kezdemenyezes;
    private final boolean tavolsagi;
    private int osszelet, eredetiDb;
    private int utolsoLepes = 0;    // utolso kor ahol csinalt barmit
    private int pajzs = 0;
    private boolean erosites = false;

    public abstract void tamad(Egyseg kit);

    public abstract void serul(double mennyit, boolean tipus, boolean visszaT, Egyseg tamado);  //type: true=truedamage

    public Egyseg(String nev, Hos vezer, int ar, int sebzesMin, int sebzesMax, int eletero, int sebesseg, int kezdemenyezes, boolean tavolsagi, int eredetiDb) {
        this.nev = nev;
        this.vezer = vezer;
        this.ar = ar;
        this.sebzesMin = sebzesMin;
        this.sebzesMax = sebzesMax;
        this.eletero = eletero;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
        this.tavolsagi = tavolsagi;
        this.eredetiDb = eredetiDb;
        this.osszelet = eletero * eredetiDb;
    }

    public String getNev() {
        return nev;
    }

    public Hos getVezer() {
        return vezer;
    }

    public int getAr() {
        return ar;
    }

    public int getSebzes() {
        Random r = new Random();
        int minDmg = (int)Math.round((1 + (erosites ? vezer.getTulajdonsagok()[2] * 0.1 : 0)) * getDb() * sebzesMin);
        int maxDmg = (int)Math.round((1 + (erosites ? vezer.getTulajdonsagok()[2] * 0.1 : 0)) * getDb() * sebzesMax);
        boolean krit = r.nextInt(20) < vezer.getTulajdonsagok()[5];
        if (krit) System.out.println("kritikus sebzes");
        return r.nextInt(maxDmg - minDmg + 1) + minDmg * (krit ? 2 : 1);
    }

    public int getUtolsoLepes() {
        return utolsoLepes;
    }

    public void lepett() {
        this.utolsoLepes = Main.game.korSzam;
    }

    public int getKezdemenyezes() {
        return kezdemenyezes + vezer.getTulajdonsagok()[4];
    }

    public int getSebesseg() {
        return sebesseg;
    }

    public boolean isTavolsagi() {
        return tavolsagi;
    }

    public int getOsszelet() {
        return osszelet;
    }

    public void setOsszelet(int osszelet) {
        this.osszelet = Math.min(Math.max(0, osszelet), eletero * eredetiDb);
    }

    public int getDb() {
        return (int)Math.ceil((double)osszelet / eletero);
    }

    public boolean elMeg() {
        return osszelet > 0;
    }

    public int getPajzs() {
        return pajzs;
    }

    public void setPajzs(int pajzs) {
        this.pajzs = Math.max(pajzs, 0);
    }

    public void setErosites(boolean erosites) {
        this.erosites = erosites;
    }

    @Override
    public String toString() {
        return "Ár: " + ar + ";  Sebzés: " + sebzesMin + "-" + sebzesMax + ";  Életerő: " + eletero + ";  Sebesség: " + sebesseg + ";  Kezdeményezés: " + kezdemenyezes;
    }

    public String printStatus() {
        return "Mennyiség: " + getDb() + "\nÉleterő: " + osszelet + "\nSebzés: " + (int)Math.round((erosites ? vezer.getTulajdonsagok()[2] * 1.1 : 1) * getDb() * sebzesMin) + "-" +
                (int)Math.round((erosites ? vezer.getTulajdonsagok()[2] * 1.1 : 1) * getDb() * sebzesMax) + "\nErősítés: " + (erosites ? "van" : "nincs") + "\nPajzs: " + pajzs +
                "\nKezdeményezés: " + getKezdemenyezes();
    }
}
