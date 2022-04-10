package com.progegy.kotprog;

public abstract class Egyseg {

    private final int ar, sebzesMin, sebzesMax, eletero, sebesseg, kezdemenyezes;
    private int osszelet, db;

    public abstract void kepesseg();    //ez majd a tamadasba lesz implementalva

    public void serul(int mennyit, boolean tipus) {

    }

    public Egyseg(int ar, int sebzesMin, int sebzesMax, int eletero, int sebesseg, int kezdemenyezes, int db) {
        this.ar = ar;
        this.sebzesMin = sebzesMin;
        this.sebzesMax = sebzesMax;
        this.eletero = eletero;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
        this.db = db;
    }

    public int getAr() {
        return ar;
    }

    public int getSebzesMin() {
        return sebzesMin;
    }

    public int getSebzesMax() {
        return sebzesMax;
    }

    public int getEletero() {
        return eletero;
    }

    public int getSebesseg() {
        return sebesseg;
    }

    public int getKezdemenyezes() {
        return kezdemenyezes;
    }

    public int getOsszelet() {
        return osszelet;
    }
}
