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
            System.out.println("yeah");
            Environment.despawnFireball(iPos,jPos);
        } else {
            iPos--;
        }
    }
}