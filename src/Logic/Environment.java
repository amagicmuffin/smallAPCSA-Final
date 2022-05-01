package Logic;

import Entities.*;
import java.util.ArrayList;

/**
 * This class stores all the data and methods to simulate an environment.
 * This does not include methods to render the environment.
 * All things that have to do with what a user can see are in UI.java.
 */
public class Environment {
    public static final char FLOOR_TILE = ' ';
    private static char[][] map = new char[][]{  // rightmost thing is kill gutter
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {'#','#','#','#'},
    };
    private static char[][] blankMap = new char[][]{
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {' ',' ',' ',' '},
            {'#','#','#','#'},
    };

    private static ArrayList<Enemy> enemyList = new ArrayList<>();
    private static ArrayList<Fireball> fireballList = new ArrayList<>();

    private static int currentGameTick = 0;

    public static int enemyBaseHP = 15;

    public static void printMap() {
        String output = "";

        output += "Home Base HP: " + Player.homeBaseHP + "\n";
        output += "Enemy Base HP: " + Environment.enemyBaseHP + "\n";

        output += "V V V V\n";

        for(char[] row : map) {
            for(char tile : row) {
                output += tile;
                output += " ";
            }
            output += "\n";
        }

        System.out.println(output);
    }

    /**
     * sets main map (not backup map)
     */
    public static void setTile(int i, int j, char c) {
        map[i][j] = c;
    }

    /**
     * Returns true if the tile at i,j is empty.
     * This is used to check if an entity can move to it.
     */
    public static boolean emptyTileAt(int i, int j) {
        return map[i][j] == FLOOR_TILE;
    }

    /**
     * Adds an enemy to enemyList and puts it on the map.
     */
    public static void spawnEnemy(Enemy enemy) {
        enemyList.add(enemy);
    }

    /**
     * Adds a fireball to fireballList and puts it on the map.
     */
    public static void spawnFireball(Fireball fireball) {
        fireballList.add(fireball);
    }

    // MISC METHODS ////////////////////
    /**
     * Updates all non-player Entities by one game tick.
     * Updates all Enemies first, then all Fireballs. This is because of how game logic works.
     * (To prevent Tomatoes from "phasing through" Fireballs)
     */
    public static void update() {
        currentGameTick++;

        // ticks all entities
        for(Enemy enemy : enemyList) enemy.tick();
        killcheck();

<<<<<<< HEAD
        for(Fireball fireball : fireballList) fireball.tick();
=======
        for(Fireball fireball : fireballList) fireball.tick();  // TODO THIS CRASHES
        // TODO you cant kill fireball while its ticking. instead, implement instance var shouldKill with getter method.
        // in tick(), update shouldKill as needed. killcheck will pick up needed kills.
>>>>>>> 17acc52f48f2db3acc62fcf7497c3627aa455ea3
        killcheck();

        // redraws map by clearing it and then putting all entities on
        for(int i = 0; i < map.length; i++) { // TODO possible error point
            for(int j = 0; j < map[0].length; j++) {
                map[i][j] = blankMap[i][j];
            }
        }
        for(Enemy enemy : enemyList) map[enemy.iPos][enemy.jPos] = enemy.tile;
        for(Fireball fireball : fireballList) map[fireball.iPos][fireball.jPos] = fireball.tile;
        map[10][Player.jPos] = Player.tile;

        // environmental things below
        if((currentGameTick + 2) % 4 == 0) spawnEnemy(new Tomato(randjPos()));
        if(currentGameTick % 4 == 0) spawnEnemy(new Bouncer(randjPos()));
    }

    /**
<<<<<<< HEAD
     * If any Fireball overlaps any Enemy, send both to kill gutter. also kill all in kill gutter.
=======
     * If any Fireball overlaps any Enemy, despawn both
     * TODO UPDATE WITH THIS INSTEAD:
     * If any Fireball overlaps any Enemy, queue them for death then kill them
>>>>>>> 17acc52f48f2db3acc62fcf7497c3627aa455ea3
     */
    private static void killcheck() {
        for (Fireball f : fireballList) {
            for (Enemy e : enemyList) {
                if (f.iPos == e.iPos && f.jPos == e.jPos) {
<<<<<<< HEAD
                    e.jPos = -1;
                    f.jPos = -1;
                }
            }
        }

        fireballList.removeIf(f -> f.jPos == -1);
        enemyList.removeIf(e -> e.jPos == -1);
    }

    /**
     * returns a random possible jPos. currently, 0 to 3 inclusive
     */
    private static int randjPos() {
        return (int) (Math.random() * 4);
=======
                    e.qDeath();
                    f.qDeath();
                }
            }
        }
        
        enemyList.removeIf(e -> e.shouldDie());
        fireballList.removeIf(f -> f.shouldDie());
>>>>>>> 17acc52f48f2db3acc62fcf7497c3627aa455ea3
    }
}


