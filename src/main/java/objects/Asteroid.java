package objects;

import main.APoint;

import java.awt.*;
import java.util.ArrayList;

public class Asteroid extends FlyingThing{
    int model, hitCount;// model: 0 - small, 1 - big
    ArrayList<Polygon> polygons = new ArrayList<>();// all models of asteroids
    ArrayList<BoxedCollider> colliders = new ArrayList<>();// colliders according to models to detect collisions

    public Asteroid(APoint position, double rotation, double speed, int model, int hitCount) {
        this.position = position;
        this.rotation = rotation;
        this.speed = speed;
        this.model = model;
        this.hitCount = hitCount;

        // add all models of asteroids

        // 1
        polygons.add(new Polygon());
        polygons.get(0).addPoint(-15,10);
        polygons.get(0).addPoint(-10,-15);
        polygons.get(0).addPoint(5,-10);
        polygons.get(0).addPoint(10,-20);
        polygons.get(0).addPoint(20,0);
        polygons.get(0).addPoint(20,5);
        polygons.get(0).addPoint(5,15);

        // 2
        polygons.add(new Polygon());
        polygons.get(1).addPoint(11, -6);
        polygons.get(1).addPoint(26, 19);
        polygons.get(1).addPoint(19, 38);
        polygons.get(1).addPoint(3, 39);
        polygons.get(1).addPoint(-18, 11);
        polygons.get(1).addPoint(-10, -9);
        polygons.get(1).addPoint(0, -11);
        polygons.get(1).addPoint(7, -4);

        // 3
        polygons.add(new Polygon());
        polygons.get(2).addPoint(19, -15);
        polygons.get(2).addPoint(26, -4);
        polygons.get(2).addPoint(13, 23);
        polygons.get(2).addPoint(-3, 24);
        polygons.get(2).addPoint(-26, -6);
        polygons.get(2).addPoint(-22, -17);
        polygons.get(2).addPoint(-10, -19);
        polygons.get(2).addPoint(-2, -10);

        // 4
        polygons.add(new Polygon());
        polygons.get(3).addPoint(24, -3);
        polygons.get(3).addPoint(40, 23);
        polygons.get(3).addPoint(27, 49);
        polygons.get(3).addPoint(-5, 52);
        polygons.get(3).addPoint(-22, 30);
        polygons.get(3).addPoint(-12, 5);
        polygons.get(3).addPoint(2, 3);
        polygons.get(3).addPoint(21, 24);

        // 5
        polygons.add(new Polygon());
        polygons.get(4).addPoint(35, 5);
        polygons.get(4).addPoint(52, 33);
        polygons.get(4).addPoint(39, 60);
        polygons.get(4).addPoint(1, 63);
        polygons.get(4).addPoint(-18, 37);
        polygons.get(4).addPoint(-6, 7);
        polygons.get(4).addPoint(20, 2);
        polygons.get(4).addPoint(27, 10);

        // 6
        polygons.add(new Polygon());
        polygons.get(5).addPoint(13, -10);
        polygons.get(5).addPoint(22, 4);
        polygons.get(5).addPoint(6, 35);
        polygons.get(5).addPoint(-14, 37);
        polygons.get(5).addPoint(-29, 17);
        polygons.get(5).addPoint(-22, -2);
        polygons.get(5).addPoint(1, -6);
        polygons.get(5).addPoint(13, 7);

        // 7
        polygons.add(new Polygon());
        polygons.get(6).addPoint(16, -8);
        polygons.get(6).addPoint(32, 18);
        polygons.get(6).addPoint(27, 29);
        polygons.get(6).addPoint(13, 30);
        polygons.get(6).addPoint(-8, 2);
        polygons.get(6).addPoint(-3, -11);
        polygons.get(6).addPoint(28, -16);
        polygons.get(6).addPoint(39, -3);

        // 8
        polygons.add(new Polygon());
        polygons.get(7).addPoint(46, 2);
        polygons.get(7).addPoint(70, 42);
        polygons.get(7).addPoint(60, 64);
        polygons.get(7).addPoint(0, 70);
        polygons.get(7).addPoint(-42, 14);
        polygons.get(7).addPoint(-24, -32);
        polygons.get(7).addPoint(38, -42);
        polygons.get(7).addPoint(80, 4);

        // 9
        polygons.add(new Polygon());
        polygons.get(8).addPoint(-50, -20);
        polygons.get(8).addPoint(-60, 50);
        polygons.get(8).addPoint(-40, 60);
        polygons.get(8).addPoint(-20, 30);
        polygons.get(8).addPoint(0, 50);
        polygons.get(8).addPoint(40, 60);
        polygons.get(8).addPoint(40, 0);
        polygons.get(8).addPoint(10, -40);

        // 10
        polygons.add(new Polygon());
        polygons.get(9).addPoint(49, -63);
        polygons.get(9).addPoint(72, -26);
        polygons.get(9).addPoint(46, 25);
        polygons.get(9).addPoint(-3, 29);
        polygons.get(9).addPoint(-35, -13);
        polygons.get(9).addPoint(-17, -58);
        polygons.get(9).addPoint(28, -66);
        polygons.get(9).addPoint(48, -44);



        // add all colliders according to models
        colliders.add(new BoxedCollider(-15,-20,20,15)); // 1 e
        colliders.add(new BoxedCollider(-18,-11,26,39)); // 2 e
        colliders.add(new BoxedCollider(-23,-18,30,26)); // 3 e
        colliders.add(new BoxedCollider(-22,-3,40,52));  // 4 e
        colliders.add(new BoxedCollider(-18,2,52,63));   // 5 e
        colliders.add(new BoxedCollider(-29,-10,22,37)); // 6 e
        colliders.add(new BoxedCollider(-8,-16,32,30));  // 7 e
        colliders.add(new BoxedCollider(-42,-32,80,70)); // 8 e
        colliders.add(new BoxedCollider(-60,-30,40,60)); // 9 e
        colliders.add(new BoxedCollider(-35,-66,72,29)); // 10 e
    }

    public Polygon getPolygon() {
        return polygons.get(model);
    }

    public BoxedCollider getCollider() {
        return colliders.get(this.model);
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount() {
        this.hitCount -= 1;
    }

    public int getModel() {
        return model;
    }
}
