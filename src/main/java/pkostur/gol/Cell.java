package pkostur.gol;

import java.util.Objects;

public class Cell {
    private final int i;
    private final int j;

    private Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public static Cell at(int i, int j) {
        return new Cell(i, j);
    }

    public int getRow() {
        return i;
    }

    public int getColumn() {
        return j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return i == cell.i &&
                j == cell.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
