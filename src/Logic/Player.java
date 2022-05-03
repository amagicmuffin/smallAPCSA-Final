package Logic;

import Entities.Fireball;

public class Player {
    public static int jPos = 1;
    public static char facing = 'd';
    public static String lastAction = "tryMove";
    public static final char tile = '@';
    public static int homeBaseHP = 1;

    // Possible user actions

    /**
     * Moves player to the left and updates facing and lastAction.
     * If at left side, go to right.
     */
    public static void moveLeft() {
        lastAction = "tryMove";
        facing = 'a';
        jPos--;
        if(jPos == -1) jPos = 3;
    }

    /**
     * Moves player to the right and updates facing and lastAction.
     * If at right side, go to left.
     */
    public static void moveRight() {
        lastAction = "tryMove";
        facing = 'd';
        jPos++;
        if(jPos == 4) jPos = 0;
    }

    /**
     * Spawns a fireball at Player's current jPos.
     */
    public static void fireball() {
        lastAction = "fireball";
        Environment.spawnFireball(new Fireball(jPos));
    }

    /**
     * does last action based on lastAction.
     * Does nothing if cannot find "tryMove" or "fireball"
     */
    public static void last() {
        if(lastAction.equals("tryMove")) {
            if(facing == 'a') moveLeft();
            else if(facing == 'd') moveRight();
        }
        else if(lastAction.equals("fireball")) fireball();
    }

}
