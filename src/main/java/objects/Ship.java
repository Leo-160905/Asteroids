package objects;

import main.APoint;

public class Ship extends FlyingThing{// Ship class extends FlyingThing class
    double rotation = 180;
    boolean rotatesLeft, rotatesRight, isThrust, shooter, shoot;

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean isShooter() {
        return shooter;
    }

    public void setShooter(boolean shooter) {
        this.shooter = shooter;
    }

    public Ship(APoint position) {
        this.position = position;
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

    public boolean isThrust() {
        return isThrust;
    }

    public void setThrust(boolean thrust) {
        this.isThrust = thrust;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}