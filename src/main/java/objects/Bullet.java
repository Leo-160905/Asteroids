package objects;

import main.APoint;

public class Bullet extends FlyingThing{
    int life = 400; // 400 ticks (bullet lifestyle)
    public Bullet(double speed, double rotation, APoint position) {
        this.speed = speed;
        this.rotation = rotation;
        this.position = position;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
