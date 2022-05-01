package Entities;

import Logic.Player;

public class Tomato extends Enemy {
    public Tomato(int j) {
        super(j);
        tile = 'o';
    }

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
