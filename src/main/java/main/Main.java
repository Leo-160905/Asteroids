package main;

import gui.AFrame;
import objects.Asteroid;
import objects.Bullet;
import objects.Ship;

import javax.swing.Timer;
import java.awt.*;
import java.util.*;

public class Main {
    public static Ship ship = new Ship(new APoint((double) AFrame.frameDimension.width / 2, (double) AFrame.frameDimension.height / 2));// new Ship(new APoint(100,100));

    public static ArrayList<Bullet> bullets = new ArrayList<>();// ArrayList of bullets that are currently on the screen
    public static ArrayList<Asteroid> asteroids = new ArrayList<>();// ArrayList of asteroids that are currently on the screen

    public static Asteroid recentlyDestroyed;// the asteroid that was most recently destroyed to hand over more information than in a boolean return statement
    public static Bullet lastBullet;
    public static int lives = 5;
    public static int points = 0;
    public static int level = 0;
    public static Timer gameTimer;
    public static Font atariFont;

    public static Timer fpsTimer;
    public static int fps = 1000 / 60;
    public static int timerTickRate = 200;
    public static boolean showFps = false;


    public static int coolDown = 10;// 10 ticks for cool down after starting a new level or crashing

    public static void main(String[] args) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        startALevel();
        new AFrame();
    }

    public static void startALevel() {// method to start a new level with a certain amount of asteroids
        coolDown = 10;
        Random r = new Random();
        for (int i = 0; i < level + 2; i++) {
            double x;
            double y;
            int sX = Math.toIntExact(Math.round(ship.getPosition().x));
            int sY = Math.toIntExact(Math.round(ship.getPosition().y));
            do {
                x = r.nextInt(sX);
                y = r.nextInt(sY);
            } while (x < sX / 2.0 - 100 && x > sX / 2.0 + 100 && y < sY / 2.0 - 100 && y > sY / 2.0 + 100);
            Main.asteroids.add(new Asteroid(new APoint(x, y), r.nextInt(360), r.nextInt(15) + 5, r.nextInt(3) + 7, r.nextInt(5) + 2));
            // 0 = small
            // 1 = small
            // 2 = small
            // 3 = small
            // 4 = small
            // 5 = small
            // 6 = small

            // 7 = big
            // 8 = big
            // 9 = big
        }
    }
}
