package pkostur;

import java.awt.*;
import java.util.Random;

public class ConsoleTest {

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            printBoard();
            Thread.sleep(50);

        }
    }

    private static void printBoard() {
        clearConsole();

        System.out.println("---------------------------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print((random.nextInt(3) % 3 == 0) ? "  " : "* ");
            }
            System.out.println();
        }
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
    }
}
