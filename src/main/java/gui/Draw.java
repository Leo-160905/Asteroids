package gui;

import main.APoint;
import main.Collider;
import main.Main;
import objects.*;
import sun.font.FontFamily;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Draw extends JPanel {
    int counter = 0;
    double resist = 0.0;
    ArrayList<AForce> forces = new ArrayList<>();

    public Draw() {
        Timer timer = new Timer(5, (e) ->  {
            if(Main.ship.isRotatesRight()){
                double r = Main.ship.getRotation() + 1.0;
                Main.ship.setRotation(r % 360);
            }
            if(Main.ship.isRotatesLeft()){
                double r = Main.ship.getRotation() - 1.0;
                Main.ship.setRotation(r % 360);
            }
            if(counter % 10 == 0){
                if(Main.ship.isThrust()){
                    boolean newObject = true;
                    for(AForce f : forces){
                        if(f.getRotation() == Main.ship.getRotation()){
                            f.setLength(f.getLength() + 1);
                            newObject = false;
                        }
                    }
                    if(newObject) {
                        forces.add(new AForce(Main.ship.getRotation(), 2));
                    }
                    resist = 10.0;
                }
                if(!Main.ship.isThrust() && counter % 50 == 0){

                    double biggest = 0.0;
                    for (AForce f : forces){
                        if (f.getLength() > biggest){
                            biggest = f.getLength();
                        }
                    }
                    for (AForce f : forces) {
                        f.setLength(f.getLength() - (f.getLength() / resist));
                    }
                    resist--;
                }
            }
            if((Main.ship.isShooter() && counter % 100 == 0 || Main.ship.isShoot()) && Main.bullets.size() < 15){
                Main.ship.setShoot(false);
                Main.bullets.add(new Bullet(20.0, Main.ship.getRotation(),Main.ship.getPosition()));

                boolean newObject = true;
                for(AForce f : forces){
                    if(f.getRotation() == 180 + Main.ship.getRotation()){
                        f.setLength(f.getLength() + 0.2);
                        newObject = false;
                    }
                }
                if(newObject) {
                    forces.add(new AForce(Main.ship.getRotation() + 180, 0.2));
                }
                resist = 10.0;
            }
            moveShip();
            moveBullets();
            moveAsteroids();

            if(counter % 2 == 0){
                Collider collider = new Collider();
                if(collider.bulletAsteroids()){
                    Random r = new Random();
                    Main.asteroids.add(new Asteroid(Main.recentlyDestroyed.getPosition(), r.nextInt(10) + Main.ship.getRotation() - 5,r.nextInt(10) + 5,0));
                    Main.asteroids.add(new Asteroid(Main.recentlyDestroyed.getPosition(), r.nextInt(50) + Main.ship.getRotation() - 25,r.nextInt(10) + 5,0));
                }
                else {
                    if(Main.asteroids.size() == 0){// start a new level when all asteroids are destroyed
                        Main.startALevel();
                        Main.level += 1;
                        if(Main.level % 3 == 0 && Main.lives < 5){
                            Main.lives += 1;
                        }
                    }
                }
                if(collider.shipAsteroid()){
                    Main.lives -= 1;
                    Main.ship.setPosition(new APoint((double) AFrame.frameDimension.width / 2, (double) AFrame.frameDimension.height / 2));
                    forces.clear();
                    if(Main.lives <= 0){
                        System.out.println("failed");
                        System.exit(0);
                    }
                }
            }
            counter++;
            counter = counter % 1000;
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw Points
        g2d.setColor(Color.white);
        g2d.translate(20 * 5,50);
        g2d.setFont(new Font("Arial", Font.PLAIN,15));
        g2d.drawString("Points: " + Main.points, 0,0);
        g2d.drawString("Level: " + Main.level, 7,15);
        g2d.translate(-20 * 5,-50);

        // Draw Lives on left edge
        g2d.setColor(Color.gray);
        for(int i = 1; i < 5; i++){
            transLives(g2d, i);
        }

        // Draw Lives on left edge
        g2d.setColor(Color.white);
        for(int i = 1; i < Main.lives; i++){
            transLives(g2d, i);
        }


        // Draw asteroids
        if(Main.asteroids.size() > 0){
            g2d.setColor(Color.white);
            for(Asteroid a : Main.asteroids){
                g2d.translate(a.getPosition().x + 2,a.getPosition().y);
                g2d.drawPolygon(a.getPolygon());
                g2d.drawString(String.valueOf(a.getHitCount()),0,0);
                g2d.translate(-(a.getPosition().x + 2),-(a.getPosition().y));
            }
        }

        // Draw bullets
        if(Main.bullets.size() > 0){
            g2d.setColor(Color.decode("#39EEFF"));
            for(Bullet b : Main.bullets){
                g2d.translate(b.getPosition().x + 1,b.getPosition().y);
                g2d.fillRect(-2,-2,2,2);
                g2d.translate(-(b.getPosition().x + 1),-(b.getPosition().y));
            }
        }

        // Draw spaceship
        g2d.setColor(Color.white);
        g2d.translate(Main.ship.getPosition().x,Main.ship.getPosition().y);
        g2d.rotate(Math.toRadians(Main.ship.getRotation()));
        g2d.drawLine(-7, -10, 7, -10);
        g2d.drawLine(-7, -10, 0, 15);
        g2d.drawLine(7, -10, 0, 15);

        // Draw spaceship booster
        if(Main.ship.isThrust()){
            g2d.drawLine(-6,-10,-4,-14);
            g2d.drawLine(-4,-14,-2,-10);
            g2d.drawLine(-2,-10,0,-14);
            g2d.drawLine(0,-14,2,-10);
            g2d.drawLine(2,-10,4,-14);
            g2d.drawLine(4,-14,6,-10);
        }

        repaint();
        if(Objects.equals(System.getProperty("os.name"), "Linux")){// Fixing lags on Linux systems
            Toolkit.getDefaultToolkit().sync();
        }
    }

    private void transLives(Graphics2D g2d, int i) {
        g2d.translate(20 * i,50);
        g2d.rotate(Math.toRadians(180));
        g2d.drawLine(-7, -10, 7, -10);
        g2d.drawLine(-7, -10, 0, 15);
        g2d.drawLine(7, -10, 0, 15);
        g2d.rotate(Math.toRadians(180));
        g2d.translate(-20 * i,-50);
    }

    void moveShip(){
        for (int i = forces.size() - 1; i >= 0; i--){
            if(forces.get(i).getLength() == 0){
                forces.remove(i);
            }
        }

        if (forces.size() > 0){
            double x = 0;
            double y = 0;
            for (AForce f : forces){
                x += (f.getLength() / 10.0) * Math.sin(Math.toRadians(f.getRotation()));
                y += (f.getLength() / 10.0) * Math.cos(Math.toRadians(f.getRotation()));
            }
            Main.ship.setPosition(new APoint(Main.ship.getPosition().x - x,Main.ship.getPosition().y + y));
        }
        // Screen wrap
        if(Main.ship.getPosition().x <= 0){// Left Edge
            Main.ship.setX(AFrame.frameDimension.width - 2);
        }
        if(Main.ship.getPosition().x >= AFrame.frameDimension.width){// Right edge
            Main.ship.setX(2);
        }
        if(Main.ship.getPosition().y <= 0){// Top edge
            Main.ship.setY(AFrame.frameDimension.height - 2);
        }
        if(Main.ship.getPosition().y >= AFrame.frameDimension.height){// Bottom edge
            Main.ship.setY(2);
        }

    }

    void moveBullets(){
        for (int i = Main.bullets.size() - 1; i >= 0; i--){
            if(Main.bullets.get(i).getLife() == 0){
                Main.bullets.remove(i);
            }
            else {
                Main.bullets.get(i).setLife(Main.bullets.get(i).getLife() - 1);
            }
        }
        if (Main.bullets.size() > 0){
            for (Bullet b : Main.bullets){
                double x = (b.getSpeed() / 10.0) * Math.sin(Math.toRadians(b.getRotation()));
                double y = (b.getSpeed() / 10.0) * Math.cos(Math.toRadians(b.getRotation()));

                b.setPosition(new APoint(b.getPosition().x - x, b.getPosition().y + y));

                if(b.getPosition().x <= 0){// Left Edge
                    b.setX(AFrame.frameDimension.width - 2);
                }
                if(b.getPosition().x >= AFrame.frameDimension.width){// Right edge
                    b.setX(2);
                }
                if(b.getPosition().y <= 0){// Top edge
                    b.setY(AFrame.frameDimension.height - 2);
                }
                if(b.getPosition().y >= AFrame.frameDimension.height){// Bottom edge
                    b.setY(2);
                }

            }
        }
    }

    void moveAsteroids(){
        if (Main.asteroids.size() > 0){
            for (Asteroid a : Main.asteroids){
                double x = (a.getSpeed() / 10.0) * Math.sin(Math.toRadians(a.getRotation()));
                double y = (a.getSpeed() / 10.0) * Math.cos(Math.toRadians(a.getRotation()));

                a.setPosition(new APoint(a.getPosition().x - x, a.getPosition().y + y));

                if(a.getPosition().x <= 0){// Left Edge
                    a.setX(AFrame.frameDimension.width - 2);
                }
                if(a.getPosition().x >= AFrame.frameDimension.width){// Right edge
                    a.setX(2);
                }
                if(a.getPosition().y <= 0){// Top edge
                    a.setY(AFrame.frameDimension.height - 2);
                }
                if(a.getPosition().y >= AFrame.frameDimension.height){// Bottom edge
                    a.setY(2);
                }
            }
        }
    }
}