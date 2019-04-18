package pkostur.gol;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class WorldTest {

    @Test
    public void shouldCreateNotNullWorld() {
        Assert.assertThat(World.create(10), Matchers.notNullValue());
    }

    @Test
    public void shouldBeEqualByCells() {
        World first = World.create(10, Cell.at(0, 0));
        World second = World.create(10, Cell.at(0, 0));
        Assert.assertThat(first, equalTo(second));
        Assert.assertThat(first, not(equalTo(World.create(10, Cell.at(0, 1)))));
    }

    @Test
    public void shouldReturnTrueForAliveCell() {
        World world = World.create(4, Cell.at(1, 1));
        Assert.assertTrue(world.isAlive(Cell.at(1, 1)));
    }

    @Test
    public void shouldReturnFalseForDeadCell() {
        World world = World.create(4, Cell.at(1, 1));
        Assert.assertFalse(world.isAlive(Cell.at(0, 0)));
    }

    @Test
    public void shouldCountZeroWithoutNeighbours() {
        // c _ _ _
        // _ _ _ _
        // _ x _ X
        // _ _ _ _

        World world = World.create(4, Cell.at(2, 1), Cell.at(2, 3));
        Assert.assertThat(world.aliveNeighbours(Cell.at(0, 0)), is(0));
    }

    @Test
    public void shouldCountVerticalNeighbours() {
        // _ _ _ _
        // _ c _ _
        // _ x _ x
        // _ _ _ _

        World world = World.create(4, Cell.at(2, 1), Cell.at(2, 3));
        Assert.assertThat(world.aliveNeighbours(Cell.at(1, 1)), is(1));
    }

    @Test
    public void shouldCountHorizontalNeighbours() {
        // _ _ _ _
        // _ _ _ _
        // _ x c x
        // _ _ _ _

        World world = World.create(4, Cell.at(2, 1), Cell.at(2, 3));
        Assert.assertThat(world.aliveNeighbours(Cell.at(2, 2)), is(2));
    }

    @Test
    public void shouldCountDiagonalNeighbours() {
        // _ _ _ _
        // _ _ c _
        // _ x _ x
        // _ _ _ _

        World world = World.create(4, Cell.at(2, 1), Cell.at(2, 3));
        Assert.assertThat(world.aliveNeighbours(Cell.at(1, 2)), is(2));
    }

    @Test
    public void shouldNotCountItselfInNeighbours() {
        // _ _ _ _
        // _ _ _ _
        // _ x x _
        // _ _ x x

        World world = World.create(4, Cell.at(2, 1), Cell.at(2, 3), Cell.at(3, 3));
        Assert.assertThat(world.aliveNeighbours(Cell.at(3, 2)), is(3));
    }

    @Test
    public void cellWithZeroNeighboursShouldDie() {
        // _ _ _ _
        // _ _ _ _
        // _ _ _ _
        // _ _ _ _

        World world = World.create(4);

        // expected:
        // _ _ _ _
        // _ _ _ _
        // _ _ _ _
        // _ _ _ _

        Assert.assertThat(world.nextGeneration(), Matchers.equalTo(World.create(4)));

    }


    @Test
    public void cellWithOneNeighboursShouldDie() {
        // _ _ _ _
        // _ x _ _
        // _ _ _ _
        // _ _ _ _

        World world = World.create(4, Cell.at(1, 1));

        // expected:
        // _ _ _ _
        // _ _ _ _
        // _ _ _ _
        // _ _ _ _

        Assert.assertThat(world.nextGeneration(), Matchers.equalTo(World.create(4)));
    }


    @Test
    public void cellWithTwoNeighboursShouldSurvive() {
        // x _ _ _
        // _ x _ _
        // _ x _ _
        // _ _ _ _

        World world = World.create(4,
                Cell.at(0, 0),
                Cell.at(1, 1),
                Cell.at(2, 1));

        // expected:
        // _ _ _ _
        // x X _ _
        // _ _ _ _
        // _ _ _ _

        World next = world.nextGeneration();
        Assert.assertTrue(next.isAlive(Cell.at(1, 1)));
    }

    @Test
    public void cellWithThreeNeighboursShouldSurvive() {
        // _ _ _ _
        // x _ x _
        // _ x _ _
        // x _ _ _

        World world = World.create(4, Cell.at(1, 0), Cell.at(1, 2), Cell.at(2, 1), Cell.at(3, 0));

        // expected:
        // _ _ _ _
        // _ _ _ _
        // x X _ _
        // _ _ _ _

        World next = world.nextGeneration();
        Assert.assertTrue(next.isAlive(Cell.at(2, 1)));
    }


    @Test
    public void deadCellWithThreeNeighboursShouldBecomeAlive() {
        // _ _ _ _
        // x _ x _
        // _ x _ _
        // _ _ _ _

        World world = World.create(4, Cell.at(1, 0), Cell.at(1, 2), Cell.at(2, 2), Cell.at(3, 0));

        // expected:
        // _ _ _ _
        // _ X _ _
        // _ x _ _
        // _ _ _ _

        World next = world.nextGeneration();
        Assert.assertTrue(next.isAlive(Cell.at(1, 1)));
    }


}
