package Entities;

public abstract class Enemy extends Entity {
    public Enemy(int j) {
        iPos = -1;
        jPos = j;
        tile = 'o';
    }

    public abstract void tick();

}
