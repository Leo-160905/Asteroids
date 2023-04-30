package objects;

import java.awt.*;

public class Ship {
    Point position;
    boolean rotatesLeft, rotatesRight;
    int thrust;

    public Ship(Point position, int thrust) {
        this.position = position;
        this.thrust = thrust;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getThrust() {
        return thrust;
    }

    public void setThrust(int thrust) {
        this.thrust = thrust;
    }

    public boolean isRotatesLeft() {
        return rotatesLeft;
    }

    public void setRotatesLeft(boolean rotatesLeft) {
        this.rotatesLeft = rotatesLeft;
    }

    public boolean isRotatesRight() {
        return rotatesRight;
    }

    public void setRotatesRight(boolean rotatesRight) {
        this.rotatesRight = rotatesRight;
    }
}
