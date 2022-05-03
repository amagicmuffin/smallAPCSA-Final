package Entities;

import Logic.Environment;
import Logic.Player;

/**
 * An enemy that shifts leftwards every once in a while.
 */
public class Shifter extends Enemy {
    public Shifter(int j) {
        super(j);
        tile = '%';
    }

    /**
     * Move downwards. If about to hit player base, send to kill gutter and decrement player base HP.
     * Shifts left once every 3 ticks. If at edge, "teleport" to right side.
     * Gets killed in Environment.killCheck().
     */
    @Override
    public void tick() {
        if (iPos == 9) { // if about to smash into base
            jPos = -1; // send to kill gutter
            Player.homeBaseHP--;
        } else {
            iPos++;
            if(Environment.currentGameTick % 3 == 0) jPos--;
            // if going left moves shifter off screen, move back on
            if(jPos == -1) {
                jPos = 3;
            }
        }
    }
}
