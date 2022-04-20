package Entities;

public abstract class Entity {
    public int iPos;
    public int jPos;
    public char tile;

    public abstract void tick();
}
