package objects;

import main.APoint;

public class FlyingThing {// Parent class for all flying objects
    double speed;
    APoint position;
    double rotation;

    public double getSpeed() {
        return speed;
    }
    public APoint getPosition() {
        return position;
    }

    public void setPosition(APoint position) {
        this.position = position;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void setX(int posX){
        this.position = new APoint(posX, this.position.y);
    }

    public void setY(int posY){
        this.position = new APoint(this.position.x, posY);
    }
}
