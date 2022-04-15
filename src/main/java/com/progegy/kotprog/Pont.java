package com.progegy.kotprog;

public class Pont {
    public int row, col;

    public Pont(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "{" + (row + 1) + ',' + (col + 1) + '}';
    }
}
