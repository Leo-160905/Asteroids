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
        polygons.get(1).addPoint(-33,-56);
        polygons.get(1).addPoint(-18,-31);
        polygons.get(1).addPoint(-25,-12);
        polygons.get(1).addPoint(-41,-11);
        polygons.get(1).addPoint(-62,-39);
        polygons.get(1).addPoint(-54,-59);
        polygons.get(1).addPoint(-44,-61);
        polygons.get(1).addPoint(-37,-54);

        // 3
        polygons.add(new Polygon());
        polygons.get(2).addPoint(-33,-58);
        polygons.get(2).addPoint(-26,-47);
        polygons.get(2).addPoint(-39,-20);
        polygons.get(2).addPoint(-55,-19);
        polygons.get(2).addPoint(-78,-49);
        polygons.get(2).addPoint(-74,-60);
        polygons.get(2).addPoint(-62,-62);
        polygons.get(2).addPoint(-54,-53);

        // 4
        polygons.add(new Polygon());
        polygons.get(3).addPoint(-38,-58);
        polygons.get(3).addPoint(-22,-32);
        polygons.get(3).addPoint(-35,-6);
        polygons.get(3).addPoint(-67,-3);
        polygons.get(3).addPoint(-84,-25);
        polygons.get(3).addPoint(-74,-50);
        polygons.get(3).addPoint(-60,-52);
        polygons.get(3).addPoint(-41,-31);

        // 5
        polygons.add(new Polygon());
        polygons.get(4).addPoint(-35,-56);
        polygons.get(4).addPoint(-18,-28);
        polygons.get(4).addPoint(-31,-1);
        polygons.get(4).addPoint(-69,2);
        polygons.get(4).addPoint(-88,-24);
        polygons.get(4).addPoint(-76,-54);
        polygons.get(4).addPoint(-50,-59);
        polygons.get(4).addPoint(-43,-51);

        // 6
        polygons.add(new Polygon());
        polygons.get(5).addPoint(-38,-57);
        polygons.get(5).addPoint(-29,-43);
        polygons.get(5).addPoint(-45,-12);
        polygons.get(5).addPoint(-65,-10);
        polygons.get(5).addPoint(-80,-30);
        polygons.get(5).addPoint(-73,-49);
        polygons.get(5).addPoint(-50,-53);
        polygons.get(5).addPoint(-38,-40);

        // 7
        polygons.add(new Polygon());
        polygons.get(6).addPoint(-31,-54);
        polygons.get(6).addPoint(-15,-28);
        polygons.get(6).addPoint(-20,-17);
        polygons.get(6).addPoint(-34,-16);
        polygons.get(6).addPoint(-55,-44);
        polygons.get(6).addPoint(-50,-57);
        polygons.get(6).addPoint(-19,-62);
        polygons.get(6).addPoint(-8,-49);

        // 8
        polygons.add(new Polygon());
        polygons.get(7).addPoint(-76,-110);
        polygons.get(7).addPoint(-52,-70);
        polygons.get(7).addPoint(-62,-48);
        polygons.get(7).addPoint(-122,-42);
        polygons.get(7).addPoint(-164,-98);
        polygons.get(7).addPoint(-146,-144);
        polygons.get(7).addPoint(-84,-154);
        polygons.get(7).addPoint(-42,-108);

        // 9
        polygons.add(new Polygon());
        polygons.get(8).addPoint(-50,-20);
        polygons.get(8).addPoint(-60,50);
        polygons.get(8).addPoint(-40,60);
        polygons.get(8).addPoint(-20,30);
        polygons.get(8).addPoint(0,50);
        polygons.get(8).addPoint(40,60);
        polygons.get(8).addPoint(40,0);
        polygons.get(8).addPoint(10,-40);

        // 10
        polygons.add(new Polygon());
        polygons.get(9).addPoint(-58,-63);
        polygons.get(9).addPoint(-35,-26);
        polygons.get(9).addPoint(-61,25);
        polygons.get(9).addPoint(-110,29);
        polygons.get(9).addPoint(-142,-13);
        polygons.get(9).addPoint(-124,-58);
        polygons.get(9).addPoint(-79,-66);
        polygons.get(9).addPoint(-59,-44);



        // add all colliders according to models
        colliders.add(new BoxedCollider(-15,-20,20,15)); // 1 e
        colliders.add(new BoxedCollider(-62,-61,18,-11)); // 2 e
        colliders.add(new BoxedCollider(-78,-60,-26,-19)); // 3 e
        colliders.add(new BoxedCollider(-84,-58,-22,-3)); // 4 e
        colliders.add(new BoxedCollider(-15,-20,20,15)); // 5
        colliders.add(new BoxedCollider(-15,-20,20,15)); // 6
        colliders.add(new BoxedCollider(-15,-20,20,15)); // 7
        colliders.add(new BoxedCollider(-164,-154,-42,-42)); // 8 e
        colliders.add(new BoxedCollider(-60,-40,40,60)); // 9 e
        colliders.add(new BoxedCollider(-142,-66,-35,29)); // 10 e
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
