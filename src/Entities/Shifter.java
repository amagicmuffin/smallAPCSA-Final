package Entities;

import Logic.Player;

public class Shifter extends Enemy {
    public Shifter(int j) {
        super(j);
        tile = '%';
    }

    @Override
    public void tick() {

        if (iPos == 9) { // if about to smash into base
            jPos = -1; // send to kill gutter
            Player.homeBaseHP--;
        } else {
            if(Environment.gameTick % 3 == 0) jPos--;
            // if going left moves shifter off screen, move back on
            if(jPos == -1) {
                jPos = 3;
            }
        }
    }
}
