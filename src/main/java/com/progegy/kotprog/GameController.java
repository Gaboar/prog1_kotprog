package com.progegy.kotprog;

import java.util.*;

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

    public List<Pont> getEgysegekInArea(Pont center, int r) {
        List<Pont> ret = new ArrayList<>();
        for (int i = Math.max(0, center.row - r); i <= Math.min(center.row + r, 9); i++) {
            for (int j = Math.max(0, center.col - r); j <= Math.min(center.col + r, 11); j++) {
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
        for (Pont p: egysegek) {
            Egyseg e = getEgyseg(p);
            //System.out.println((e.getVezer() == player1 ? "player1 " : "player2 ") + e.getNev() + ": " + e.getKezdemenyezes());
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
            System.out.println("=== Uj kor kovetkezik ===");
            for (Pont p: getEgysegek()) {
                getEgyseg(p).setPajzs(0);
                getEgyseg(p).setErosites(false);
            }
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
        Pont p = getPozicio(e);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null && Math.abs(p.row - i) + Math.abs(p.col - j) == 1) ret.add(map[i][j]);
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

    public void automataPenzkolto() {
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
        //1 random varazslat villam/tuz
        t = new int[] {0, 0, 0, 0, 0};
        t[r.nextInt(2)]++;
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

    public void botTevekenyseg() {
        Random r = new Random();
        Pont e = null;
        for (Pont p: getEgysegek()) {
            if (e == null || (getEgyseg(p).getOsszelet() < getEgyseg(e).getOsszelet() && getEgyseg(p).getVezer() != player2)) e = p;
        }
        if (player2.getUtolsoLepes() < korSzam) {
            if (r.nextInt(2) == 0 && ((player2.getVarazslatok()[0].isVan() && player2.getMana() >= 5) || player2.getMana() >= 9)) {
                System.out.println("hos varazsol");
                if (player2.getVarazslatok()[0].isVan()) player2.getVarazslatok()[0].varazs(e);
                else if (player2.getVarazslatok()[1].isVan()) player2.getVarazslatok()[1].varazs(e);
            }
            else {
                System.out.println("hos tamad: " + getEgyseg(e).getNev());
                player2.tamad(getEgyseg(e));
            }
        }
        else {
            Pont kovi = getKovetkezo();
            while (getEgyseg(kovi).getVezer() != player2) {
                kovi = getKovetkezo();
            }
            System.out.println(getEgyseg(kovi).getNev() + " tamad");
            if (getEgyseg(kovi).isTavolsagi()) getEgyseg(kovi).tamad(getEgyseg(e));
            else {
                Egyseg ellen = null;
                for (Egyseg egys: getSzomszedok(getEgyseg(kovi))) {
                    if (egys.getVezer() != player2 && (ellen == null || egys.getOsszelet() < ellen.getOsszelet())) ellen = egys;
                }
                if (ellen != null) System.out.println(ellen.getNev());
                if (ellen != null) getEgyseg(kovi).tamad(ellen);
                else {
                    for (Pont p: getEgysegek()) {
                        if (getEgyseg(p).getOsszelet() > getEgyseg(e).getOsszelet() && getEgyseg(p).getVezer() != player2) e = p;
                    }
                    System.out.println((getEgyseg(e).getVezer() == player2 ? "p2" : "p1") + " " + getEgyseg(e).getNev());
                    System.out.println("megse tamad csak odamegy");
                    if (hovaLephet(kovi, getEgyseg(kovi).getSebesseg()).size() == 0) getEgyseg(kovi).lepett();
                    else {
                        Pont lk = null;
                        for (Pont lephet : hovaLephet(kovi, getEgyseg(kovi).getSebesseg())) {
                            if (lk == null || Math.abs(e.row - lephet.row) + Math.abs(e.col - lephet.col) < Math.abs(e.row - lk.row) + Math.abs(e.col - lk.col))
                                lk = lephet;
                        }
                        map[lk.row][lk.col] = map[kovi.row][kovi.col];
                        map[kovi.row][kovi.col] = null;
                        kovi = lk;
                        System.out.println(lk + " " + kovi);
                        getEgyseg(lk).lepett();
                    }
                }
            }
            if (!getEgyseg(kovi).elMeg()) map[kovi.row][kovi.col] = null;
        }
        if (!getEgyseg(e).elMeg()) map[e.row][e.col] = null;
    }

    public Set<Pont> hovaLephet(Pont start, int maxTav) {
        Set<Pont> sor = new HashSet<>();
        sor.add(start);
        int tav = 0;
        while (tav < maxTav) {
            sor = leptet(sor);
            tav++;
        }
        return sor;
    }

    public Set<Pont> leptet(Set<Pont> halmaz) {
        Set<Pont> masik = new HashSet<>();
        for (Pont p: halmaz) {
            if (!getEgysegek().contains(new Pont(Math.max(0, p.row - 1), p.col))) masik.add(new Pont(Math.max(0, p.row - 1), p.col));
            if (!getEgysegek().contains(new Pont(Math.min(p.row + 1, 9), p.col))) masik.add(new Pont(Math.min(p.row + 1, 9), p.col));
            if (!getEgysegek().contains(new Pont(p.row, Math.max(0, p.col - 1)))) masik.add(new Pont(p.row, Math.max(0, p.col - 1)));
            if (!getEgysegek().contains(new Pont(p.row, Math.min(p.col + 1, 11)))) masik.add(new Pont(p.row, Math.min(p.col + 1, 11)));
        }
        masik.addAll(halmaz);
        return masik;
    }
}
