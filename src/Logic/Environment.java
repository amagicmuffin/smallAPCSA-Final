package Logic;

import Entities.*;
import java.util.ArrayList;

/**
 * This class stores all the data and methods to simulate an environment.
 * This does not include methods to render the environment.
 * All things that have to do with what a user can see are in UI.java.
 */
public class Environment {
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

    public static int currentGameTick = 0;

    public static int enemyBaseHP = 15;

    public static boolean frozen = false;  // if true, no enemies will be spawned

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
     * Changes a tile on the main map (not backup map)
     */
    public static void setTile(int i, int j, char c) {
        map[i][j] = c;
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

    /**
     * Updates all non-player Entities by one game tick.
     * Updates all Enemies first, then all Fireballs. This is because of how game logic works.
     * (To prevent Tomatoes from "phasing through" Fireballs)
     */
    public static void update() {
        currentGameTick++;

        // ticks all enemies, then checks if anyone needs to be killed
        for(Enemy enemy : enemyList) enemy.tick();
        killcheck();

        // ticks all fireballs, then checks if anyone needs to be killed
        for(Fireball fireball : fireballList) fireball.tick();
        killcheck();

        // redraws map by copying blankMap onto the visible map and then putting all entities on
        for(int i = 0; i < map.length; i++) {
            System.arraycopy(blankMap[i], 0, map[i], 0, map[0].length);
        }
        for(Enemy enemy : enemyList) map[enemy.iPos][enemy.jPos] = enemy.tile;
        for(Fireball fireball : fireballList) map[fireball.iPos][fireball.jPos] = fireball.tile;
        map[10][Player.jPos] = Player.tile;

        // if spawning isn't frozen, spawn enemies as needed
        if(!frozen) {
            if ((currentGameTick + 2) % 5 == 0) spawnEnemy(new Tomato(randjPos()));
            if (currentGameTick % 5 == 0) spawnEnemy(new Bouncer(randjPos()));
            if ((currentGameTick + 3) % 5 == 0) spawnEnemy(new Shifter(randjPos()));
        }
    }

    /**
     * If any Fireball overlaps any Enemy, send both to kill gutter. also kill all in kill gutter.
     */
    private static void killcheck() {
        for (Fireball f : fireballList) {
            for (Enemy e : enemyList) {
                if (f.iPos == e.iPos && f.jPos == e.jPos) {
                    e.jPos = -1;
                    f.jPos = -1;
                }
            }
        }

        fireballList.removeIf(f -> f.jPos == -1);
        enemyList.removeIf(e -> e.jPos == -1);
    }

    /**
     * Returns a random possible jPos. Currently, 0 to 3 inclusive
     */
    private static int randjPos() {
        return (int) (Math.random() * 4);
    }
}


