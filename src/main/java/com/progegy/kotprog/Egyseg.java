package com.progegy.kotprog;

public abstract class Egyseg {

    private final String name;
    private final Hos vezer;
    private final int ar, sebzesMin, sebzesMax, eletero, sebesseg, kezdemenyezes;
    private int osszelet, db;

    public abstract void kepesseg();    //ez majd a tamadasba lesz implementalva

    public void serul(int mennyit, boolean tipus) {

    }

    public Egyseg(String name, Hos vezer, int ar, int sebzesMin, int sebzesMax, int eletero, int sebesseg, int kezdemenyezes, int db) {
        this.name = name;
        this.vezer = vezer;
        this.ar = ar;
        this.sebzesMin = sebzesMin;
        this.sebzesMax = sebzesMax;
        this.eletero = eletero;
        this.sebesseg = sebesseg;
        this.kezdemenyezes = kezdemenyezes;
        this.db = db;
        this.osszelet = eletero * db;
    }

    public String getName() {
        return name;
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
