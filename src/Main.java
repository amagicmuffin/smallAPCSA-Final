import Logic.*;

import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        setup();
        
        boolean gaming = true;
        while (gaming) {
            String userInput = scan.nextLine();

            // clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // process input
            if(Konami.check(userInput)) break;
            else if (userInput.equals("a")) Player.moveLeft();
            else if (userInput.equals("d")) Player.moveRight();
            else if (userInput.equals(" ")) Player.fireball();
            else if (userInput.equals("freeze")) Environment.frozen = !Environment.frozen; // cheat code
            else Player.last();

            // do things
            Environment.update();
            if (Environment.enemyBaseHP == 0) break;
            if (Player.homeBaseHP == 0) break;

            Environment.printMap();
        }

        if (Player.homeBaseHP == 0) {
            youLost();
        } else {
            youWon();
        }
    }

    private static void setup() {
        // place player on the screen
        Environment.setTile(10, Player.jPos, Player.tile);

        // clear screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // print the map
        Environment.printMap();
    }

    private static void youWon() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("You\nWon!\n\n\n\n\n");
    }

    private static void youLost() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("You\nAre\nBad\nAt\nThis\nGame\n<3\n\n\n\n\n");
    }
}
