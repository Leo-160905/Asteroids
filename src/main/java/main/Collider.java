package main;

import objects.Asteroid;
import objects.Bullet;
import objects.TwoCondition;

public class Collider {

    public boolean bulletAsteroids(){
        boolean returnable = false;
        int removable = -1;
        for (int i = Main.asteroids.size() - 1; i >= 0; i--) {
            double x1 = Main.asteroids.get(i).getCollider().getPoint1().x + Main.asteroids.get(i).getPosition().x;
            double y1 = Main.asteroids.get(i).getCollider().getPoint1().y + Main.asteroids.get(i).getPosition().y;
            double x2 = Main.asteroids.get(i).getCollider().getPoint2().x + Main.asteroids.get(i).getPosition().x;
            double y2 = Main.asteroids.get(i).getCollider().getPoint2().y + Main.asteroids.get(i).getPosition().y;

            if (Main.bullets.size() > 0) {
                for (int j = Main.bullets.size() - 1; j >= 0; j--) {
                    Bullet b = Main.bullets.get(j);
                    double bx = b.getPosition().x;
                    double by = b.getPosition().y;

                    if (x1 < bx && x2 > bx && y1 < by && y2 > by) {// Bullet collides with Asteroid
                        Main.asteroids.get(i).setHitCount();
                        if (Main.asteroids.get(i).getHitCount() <= 0) {// Asteroid is dead
                            if (Main.asteroids.get(i).getModel() > 6) {// is it a big or a small one
                                Main.recentlyDestroyed = Main.asteroids.get(i);
                                Main.lastBullet = Main.bullets.get(j);
                                returnable = true;
                                Main.points += 30;
                            }
                            else {
                                Main.points += 70;
                            }
                            removable = i;
                        }
                        Main.bullets.remove(j);
                    }
                }
                if(removable != -1){
                    Main.asteroids.remove(removable);
                    removable = -1;
                }
            }
        }
        return returnable;
    }

    public TwoCondition shipAsteroid() {
        int removable = -1;
        boolean hit = false;
        boolean isBigAsteroid = false;
        if (Main.asteroids.size() > 0) {
            for (int i = Main.asteroids.size() - 1; i >= 0; i--) {
                Asteroid a = Main.asteroids.get(i);
                double x1 = a.getCollider().getPoint1().x + a.getPosition().x;
                double y1 = a.getCollider().getPoint1().y + a.getPosition().y;
                double x2 = a.getCollider().getPoint2().x + a.getPosition().x;
                double y2 = a.getCollider().getPoint2().y + a.getPosition().y;

                double shipX = Main.ship.getPosition().x;
                double shipY = Main.ship.getPosition().y;
                if (x1 - 3 < shipX && x2 + 3 > shipX && y1 - 3 < shipY && y2 + 3 > shipY) {
                    a.setHitCount();
                    if (a.getModel() > 6) {// is it a big or a small one
                        Main.recentlyDestroyed = Main.asteroids.get(i);
                        Main.lastBullet = new Bullet(Main.ship.getSpeed(), Main.ship.getRotation(), new APoint(0,0));
                        isBigAsteroid = true;
                        Main.points += 30;
                    }
                    else {
                        Main.points += 70;
                    }
                    removable = i;
                    hit = true;
                }
                if(removable != -1){
                    Main.asteroids.remove(removable);
                    removable = -1;
                }
                if (hit) {
                    return new TwoCondition(true, isBigAsteroid);
                }
            }
        }
        return new TwoCondition(false, false);
    }
}
