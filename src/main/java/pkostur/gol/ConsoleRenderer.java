package pkostur.gol;

public class ConsoleRenderer implements WorldRenderer {
    @Override
    public void render(World world) {
        clearConsole();

        System.out.println("---------------------------");
        for (int i = 0; i < world.dimension; i++) {
            for (int j = 0; j < world.dimension; j++) {
                System.out.print(world.isAlive(Cell.at(i, j)) ? "* " : "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
    }
}
