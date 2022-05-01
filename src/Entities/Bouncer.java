package Entities;

import Logic.Player;

public class Bouncer extends Enemy {
    public Bouncer(int j) {
        super(j);
        pickDirection(); // sets super.tile
    }

    public void pickDirection() {
        int direction = (int)(Math.random() * 2); // 0 is left, 1 is right
        if (direction == 0) tile = '<';
        else tile = '>';
    }

    @Override
    public void tick() {

        if (iPos == 9) { // if about to smash into base
            jPos = -1; // send to kill gutter
            Player.homeBaseHP--;
        } else {
            iPos++;

            // this prevents crash on instantiation
            // if spawn on left edge, moving left, won't crash
            if(tile == '<') {
                jPos--;
                // if going left moves bouncer off screen, move back on and change direction
                if(jPos == -1) {
                    jPos += 2;
                    tile = '>';
                }
            } else if(tile == '>') {
                jPos++;
                // if going right moves bouncer off screen, move back on and change direction
                if(jPos == 4) {
                    jPos -= 2;
                    tile = '<';
                }
            }
        }
    }
}
