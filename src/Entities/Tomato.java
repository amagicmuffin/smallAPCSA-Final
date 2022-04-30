package Entities;

public class Tomato extends Enemy {
    public Tomato(int j) {
        super(j);
        tile = 'o';
    }

    @Override
    public void tick() {

        if (iPos == 9) { // if about to smash into base
            jPos = -1; // send to kill gutter
            // TODO del 1 hp?
        } else {
            iPos++;
        }
    }
}
