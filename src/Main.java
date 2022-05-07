import Logic.*;

import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static int konamiI = 0;
    public static final char[] kCode = {'w', 'w', 's', 's', 'a', 'd', 'a', 'd', 'b', 'a'};

    public static void main(String[] args) {
        setup();
        
        boolean gaming = true;
        while (gaming) {
            String userInput = scan.nextLine();
            ClearScreen.clear();
            if(konami(userInput)) break;
            else if (userInput.equals("a")) Player.moveLeft();
            else if (userInput.equals("d")) Player.moveRight();
            else if (userInput.equals(" ")) Player.fireball();
            else if (userInput.equals("freeze")) Environment.frozen = !Environment.frozen; // cheat code
            else Player.last();

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
        Environment.setTile(10, Player.jPos, Player.tile);
        ClearScreen.clear();
        Environment.printMap();
    }

    // konamiI and kCode
    private static boolean konami(String input) {
        if (input.length() == 0) return false;

        if(kCode[konamiI] == input.charAt(0)) {
            konamiI++;
            return konamiI == kCode.length;
        }
        return false;
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
