package Entities;

import Logic.Player;

/**
 * The basic enemy class that only goes downward.
 */
public class Tomato extends Enemy {
    public Tomato(int j) {
        super(j);
        tile = 'o';
    }

    /**
     * Move downwards.
     */
    @Override
    public void tick() {
        if (iPos == 9) { // if about to smash into base
            jPos = -1; // send to kill gutter
            Player.homeBaseHP--;
        } else {
            iPos++;
        }
    }
}
