package pkostur.gol;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class World {
    final int dimension;
    private final Set<Cell> aliveCells;

    public World(int dimension, Collection<Cell> aliveCells) {
        this.dimension = dimension;
        this.aliveCells = new HashSet<>(aliveCells);
    }

    public static World create(int dimension, Cell... cells) {
        return new World(dimension, Arrays.asList(cells));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return dimension == world.dimension &&
                Objects.equals(aliveCells, world.aliveCells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimension, aliveCells);
    }

    public int aliveNeighbours(Cell at) {

        int verticalTop = at.getRow() - 1;
        int verticalBottom = at.getRow() + 1;

        int count = 0;
        for (int i = verticalTop; i <= verticalBottom; i++) {
            for (int j = at.getColumn() - 1; j <= at.getColumn() + 1; j++) {
                Cell neighbour = Cell.at(i, j);
                if (!Objects.equals(neighbour, at) && isAlive(neighbour)) {
                    count++;
                }
            }

        }

        return count;
    }

    public boolean isAlive(Cell cell) {
        return aliveCells.contains(cell);
    }

    public boolean isAlive() {
        return !aliveCells.isEmpty();
    }

    public World nextGeneration() {
        Set<Cell> newCells = new HashSet<>();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                Cell cell = Cell.at(i, j);
                int neighbours = aliveNeighbours(cell);
                if (isAlive(cell)) {
                    if (neighbours == 2 || neighbours == 3) {
                        newCells.add(cell);
                    }
                }

                if (neighbours == 3) {
                    newCells.add(cell);
                }

            }
        }
        return new World(dimension, newCells);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sb.append(isAlive(Cell.at(i, j)) ? "x " : "_ ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
