package pkostur.gol;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameOfLifeApp {

    public static void main(String[] args) throws InterruptedException {
        WorldRenderer renderer = new ConsoleRenderer();

        World world = World.create(25,
                Cell.at(1,2),
                Cell.at(2,3),
                Cell.at(3,1),
                Cell.at(3,2),
                Cell.at(3,3)

        );

        for (int i = 0; i < 100; i++) {
            renderer.render(world);
            if (!world.isAlive()) {
                break;
            }
            world = world.nextGeneration();
            Thread.sleep(250);
        }

    }

}
