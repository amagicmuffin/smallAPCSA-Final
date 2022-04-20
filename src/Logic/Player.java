package Logic;

import Entities.Fireball;

public class Player {
    public static int jPos = 1;
    public static char facing = 'd';
    public static String lastAction = "tryMove";
    public static final char tile = '@';

    // Possible user actions
    public static void moveLeft() {
        lastAction = "tryMove";
        facing = 'a';
        jPos--;
        if(jPos == -1) jPos = 3;
    }

    public static void moveRight() {
        lastAction = "tryMove";
        facing = 'd';
        jPos++;
        if(jPos == 4) jPos = 0;
    }

    public static void fireball() {
        lastAction = "fireball";
        Environment.spawnFireball(new Fireball(jPos));
    }

    /**
     * does last action based on lastAction.
     * Does nothing if cannot find "tryMove", "fireball"
     */
    public static void last() {
        if(lastAction.equals("tryMove")) {
            if(facing == 'a') moveLeft();
            else if(facing == 'd') moveRight();
        }
        else if(lastAction.equals("fireball")) fireball();
    }

}
