package com.progegy.kotprog;

public class GameController {
    protected Hos player1, player2;
    protected Hos vasarol;

    protected Egyseg[][] map = new Egyseg[10][12];
    protected Egyseg letesz;

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

    public void printMap() {
        for (Egyseg[] sor: map) {
            for (Egyseg mezo: sor) {
                System.out.print((mezo != null ? mezo.getName() : "ures") + "\t");
            }
            System.out.println();
        }
    }
}
