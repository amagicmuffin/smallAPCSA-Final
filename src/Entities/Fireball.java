package Entities;

import Logic.Environment;

public class Fireball extends Entity {
    public Fireball(int j) {
        iPos = 10;
        jPos = j;
        tile = '.';
    }

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
