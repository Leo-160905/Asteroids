package objects;

import main.APoint;

import java.awt.*;
import java.util.ArrayList;

public class Asteroid extends FlyingThing{
    int model, hitCount;// model: 0 - small, 1 - big
    ArrayList<Polygon> polygons = new ArrayList<>();// all models of asteroids
    ArrayList<BoxedCollider> colliders = new ArrayList<>();// colliders according to models to detect collisions

    public Asteroid(APoint position, double rotation, double speed, int model) {
        this.position = position;
        this.rotation = rotation;
        this.speed = speed;
        this.model = model;
        this.hitCount = model + 2;

        // add all models of asteroids
        polygons.add(new Polygon());
        polygons.get(0).addPoint(-15,10);
        polygons.get(0).addPoint(-10,-15);
        polygons.get(0).addPoint(5,-10);
        polygons.get(0).addPoint(10,-20);
        polygons.get(0).addPoint(20,0);
        polygons.get(0).addPoint(20,5);
        polygons.get(0).addPoint(5,15);

        polygons.add(new Polygon());
        polygons.get(1).addPoint(-50,-20);
        polygons.get(1).addPoint(-60,50);
        polygons.get(1).addPoint(-40,60);
        polygons.get(1).addPoint(-20,30);
        polygons.get(1).addPoint(0,50);
        polygons.get(1).addPoint(40,60);
        polygons.get(1).addPoint(40,0);
        polygons.get(1).addPoint(10,-40);

        // add all colliders according to models
        colliders.add(new BoxedCollider(-15,-20,20,15));
        colliders.add(new BoxedCollider(-60,-40,40,60));
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
