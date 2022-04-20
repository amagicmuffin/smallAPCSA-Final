package Logic;

import Entities.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class stores all the data and methods to simulate an environment.
 * This does not include methods to render the environment.
 * All things that have to do with what a user can see are in UI.java.
 */
public class Environment {
    public static final char FLOOR_TILE = ' ';
    private static char[][] map = new char[][]{
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

    public static void printMap() {
        String output = "";

        output += "V V V V";

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
     * Removes the enemy at coords
     */
    public static void despawnEnemy(int i, int j) {
//        enemyList.removeIf(e -> e.iPos == i && e.jPos == j);

        for (int k = 0; k < enemyList.size(); k++) {
            if (enemyList.get(k).iPos == i && enemyList.get(k).jPos == j) {
                enemyList.remove(k);
                break;
            }
        }
    }



    /**
     * Removes the fireball at coords
     */
    public static void despawnFireball(int i, int j) {
//        fireballList.removeIf(f -> f.iPos == i && f.jPos == j);

//        long cringe way
//        Iterator<Fireball> itr = fireballList.iterator();
//        while (itr.hasNext()) {
//            Fireball f = itr.next();
//            if (f.iPos == i && f.jPos == j) {
//                itr.remove();
//            }
//        }

        // TODO this will crash later, use the first commented out block
        for (int k = 0; k < fireballList.size(); k++) {
            if (fireballList.get(k).iPos == i && fireballList.get(k).jPos == j) {
                fireballList.remove(k);
                break;
            }
        }


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

        for(Fireball fireball : fireballList) fireball.tick();  // TODO THIS CRASHES
        // TODO you cant kill fireball while its ticking. instead, implement instance var shouldKill with getter method.
        // in tick(), update shouldKill as needed. killcheck will pick up needed kills.
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

    }

    /**
     * If any Fireball overlaps any Enemy, despawn both
     * TODO UPDATE WITH THIS INSTEAD:
     * If any Fireball overlaps any Enemy, queue them for death then kill them
     */
    private static void killcheck() {
        for (Fireball f : fireballList) {
            for (Enemy e : enemyList) {
                if (f.iPos == e.iPos && f.jPos == e.jPos) {
                    e.qDeath();
                    f.qDeath();
                }
            }
        }
        
        enemyList.removeIf(e -> e.shouldDie());
        fireballList.removeIf(f -> f.shouldDie());
    }
}


