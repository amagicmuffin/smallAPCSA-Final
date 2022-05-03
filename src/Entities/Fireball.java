package Entities;

import Logic.Environment;

/**
 * An entity that files upward to beat up the enemy base or enemies.
 */
public class Fireball extends Entity {
    public Fireball(int j) {
        iPos = 10;
        jPos = j;
        tile = '.';
    }

    /**
     * Move upwards. If about to hit enemy base, send to kill gutter and decrement enemy base HP.
     * Gets killed in Environment.killCheck().
     */
    @Override
    public void tick() {
        if (iPos == 0) { // if about to smash into V
            jPos = -1; // send to kill gutter
            Environment.enemyBaseHP--;
        } else { // if not about to, move fireball up.
            iPos--;
        }
    }
}
