package com.progegy.kotprog;

import java.util.Random;

public abstract class Egyseg {

    private final String nev;
    private final Hos vezer;
    private final int ar, sebzesMin, sebzesMax, eletero, sebesseg, kezdemenyezes;
    private final boolean tavolsagi;
    private int osszelet, db;
    private int utolsoLepes = 0;    // utolso kor ahol csinalt barmit

    public abstract void tamad(Egyseg kit);

    public abstract void serul(double mennyit, boolean tipus);  //type: true=truedamage

    public Egyseg(String nev, Hos vezer, int ar, int sebzesMin, int sebzesMax, int eletero, int sebesseg, int kezdemenyezes, boolean tavolsagi, int db) {
        this.nev = nev;
        this.vezer = vezer;
        this.ar = ar;
        this.sebzesMin = sebzesMin;
        this.sebzesMax = sebzesMax;
        this.eletero = eletero;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
        this.tavolsagi = tavolsagi;
        this.db = db;
        this.osszelet = eletero * db;
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
        if (getDb() == 0) return 0;
        Random r = new Random();
        return r.nextInt(getDb() * (sebzesMax - sebzesMin) + 1) + getDb() * sebzesMin;
    }

    public int getUtolsoLepes() {
        return utolsoLepes;
    }

    public void lepett() {
        this.utolsoLepes++;
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
        this.osszelet = Math.max(osszelet, 0);
    }

    public int getDb() {
        return (int)Math.ceil((double)osszelet / eletero);
    }

    public boolean elMeg() {
        return osszelet > 0;
    }

    @Override
    public String toString() {
        return "Ár: " + ar + ";  Sebzés: " + sebzesMin + "-" + sebzesMax + ";  Életerő: " + eletero + ";  Sebesség: " + sebesseg + ";  Kezdeményezés: " + kezdemenyezes;
    }
}
