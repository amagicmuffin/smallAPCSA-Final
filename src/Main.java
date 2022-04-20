import Entities.*;
import Logic.*;

import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        setup();
        Environment.printMap();
        boolean gaming = true;
        while (gaming) {
            String userInput = scan.nextLine();
            if(konami(userInput)) break;
            else if (userInput.equals("a")) Player.moveLeft();
            else if (userInput.equals("d")) Player.moveRight();
            else if (userInput.equals(" ")) Player.fireball();
            else Player.last();

            Environment.update();

            Environment.printMap();
        }

        youWon();
    }

    private static void setup() {
        Environment.setTile(10, Player.jPos, Player.tile);
    }

    private static boolean konami(String input) {
        //TODO
        return false;
    }

    private static void youWon() {
        // TODO
    }
}
