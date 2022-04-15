package com.progegy.kotprog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
    protected Hos player1, player2;
    protected Hos vasarol;

    protected Egyseg[][] map = new Egyseg[10][12];
    protected Egyseg letesz = null;

    protected int korSzam = 1;

    public GameController(Hos player1, Hos player2) {
        this.player1 = player1;
        this.player2 = player2;
        vasarol = this.player1;
        for (Egyseg[] sor: map) {
            for (Egyseg mezo: sor) {
                mezo = null;
            }
        }
    }

    public List<Pont> getEgysegek() {
        List<Pont> ret = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) ret.add(new Pont(i, j));
            }
        }
        return ret;
    }

    public Pont getKovetkezo() {
        Random r = new Random();
        List<Pont> egysegek = getEgysegek();
        List<Pont> lehetne = new ArrayList<>();
        int lnKezd = -1;
        System.out.println("--- Kezdemenyezesek: ---");
        for (Pont p: egysegek) {
            Egyseg e = getEgyseg(p);
            System.out.println((e.getVezer() == player1 ? "player1 " : "player2 ") + e.getNev() + ": " + e.getKezdemenyezes());
            if (e.getUtolsoLepes() < korSzam) {    // csak akik meg nem tamadtak
                if (lnKezd == -1) { // ez az elso
                    lnKezd = e.getKezdemenyezes();
                    lehetne.add(p);
                } else if (e.getKezdemenyezes() > lnKezd) { // ha van nagyobb
                    lnKezd = e.getKezdemenyezes();
                    lehetne = new ArrayList<>();
                    lehetne.add(p);
                } else if (e.getKezdemenyezes() == lnKezd) { // hozzaad
                    lehetne.add(p);
                }
            }
        }
        if (lehetne.size() == 0) {
            korSzam++;
            return getKovetkezo();
        }
        return lehetne.get(r.nextInt(lehetne.size()));
    }

    public Egyseg getEgyseg(Pont p) {
        if (p == null) return null;
        return map[p.row][p.col];
    }

    public Pont getPozicio(Egyseg e) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == e) return new Pont(i, j);
            }
        }
        return null;
    }

    public List<Egyseg> getSzomszedok(Egyseg e) {
        List<Egyseg> ret = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) ret.add(map[i][j]);
            }
        }
        return ret;
    }

    public void printMap() {
        for (Egyseg[] sor: map) {
            for (Egyseg mezo: sor) {
                System.out.print((mezo != null ? mezo.getNev() : "ures") + "\t\t");
            }
            System.out.println();
        }
    }

    public void botTevekenyseg() {
        System.out.println("Bot vasarol");
        Random r = new Random();
        //minden tulajdonsag random 1-3
        //vedekezes 3-4
        //tudas 2-3
        //maradek tamadas (ossz 11)
        int[] t = new int[] {0, r.nextInt(2) + 3, r.nextInt(3) + 1, r.nextInt(2) + 2, r.nextInt(3) + 1, r.nextInt(3) + 1};
        int o = 0;
        for (int i: t) {
            o += i;
        }
        player2.setTulajdonsagok(17 - o, t[1], t[2], t[3], t[4], t[5]);
        player2.setArany(player2.getArany() - 120);
        //1 random varazslat
        t = new int[] {0, 0, 0, 0, 0};
        t[r.nextInt(5)]++;
        player2.setVarazslatok(t[0] == 1, t[1] == 1, t[2] == 1, t[3] == 1, t[4] == 1);
        for (Varazslat v: player2.getVarazslatok()) {
            if (v.isVan()) player2.setArany(player2.getArany() - v.getAr());
        }
        //minden egyseg 10-20db random
        //griff 11-15 ha paratlan akkor -= 1
        //maradek penz foldmuves
        t = new int[] {0, r.nextInt(11) + 10, r.nextInt(11) + 10, r.nextInt(11) + 10, r.nextInt(5) + 11};
        if (t[4] % 2 != 0) t[4]--;
        for (int i = 0; i < t.length; i++) {
            player2.setArany(player2.getArany() - player2.getEgysegek()[i].getAr() * t[i]);
        }
        t[0] = player2.getArany() / player2.getEgysegek()[0].getAr();
        player2.setArany(0);
        player2.setEgysegek(t[0], t[1], t[2], t[3], t[4]);
        System.out.println("Bot pakol");
        for (int i = 0; i < 5; i++) {
            boolean siker = false;
            while (!siker) {
                int row = r.nextInt(10);
                int col = r.nextInt(2) + 10;
                if (map[row][col] == null) {
                    map[row][col] = player2.getEgysegek()[i];
                    siker = true;
                }
            }
        }
        printMap();
    }
}
