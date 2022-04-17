package com.progegy.kotprog;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pont pont = (Pont) o;
        return row == pont.row && col == pont.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
