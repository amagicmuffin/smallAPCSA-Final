package Entities;

import Logic.Environment;

public class Tomato extends Enemy {
    public Tomato(int j) {
        super(j);
        tile = 'o';
    }

    @Override
    public void tick() {
//        System.out.println(this.getId() + " was ticked");
        if (iPos == 9) { // if about to smash into base
            Environment.despawnEnemy(iPos,jPos);
        } else {
            iPos++;
        }
    }
}
