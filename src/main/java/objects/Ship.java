package objects;

import main.APoint;

public class Ship {
    APoint position;
    double rotation = 180;
    boolean rotatesLeft, rotatesRight, isThrust;
    int thrust;

    public Ship(APoint position, int thrust) {
        this.position = position;
        this.thrust = thrust;
    }

    public APoint getPosition() {
        return position;
    }

    public void setPosition(APoint position) {
        this.position = position;
    }

    public void setX(int posX){
        this.position = new APoint(posX, this.position.y);
    }

    public void setY(int posY){
        this.position = new APoint(this.position.x, posY);
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