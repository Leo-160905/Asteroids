package gui;

import main.APoint;
import main.Collider;
import main.Main;
import objects.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Draw extends JPanel {
    int counter = 0;
    double resist = 0.0;
    Color shipColor = Color.white;
    Font atariFont;
    ArrayList<AForce> forces = new ArrayList<>();

    public Draw() {
        InputStream is = getClass().getResourceAsStream("/Fonts/AtariFont.ttf");
        try {
            assert is != null;
            atariFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        Main.gameTimer = new Timer(5, (e) -> {// Timer with 5ms delay
            if (Main.ship.isRotatesRight()) {// Rotate ship right if key is pressed
                double r = Main.ship.getRotation() + 1.0;
                Main.ship.setRotation(r % 360);
            }
            if (Main.ship.isRotatesLeft()) {// Rotate ship left if key is pressed
                double r = Main.ship.getRotation() - 1.0;
                Main.ship.setRotation(r % 360);
            }
            if (counter % 10 == 0) {// Add force to forces arraylist if ship is thrusting
                if (Main.ship.isThrust()) {
                    boolean newObject = true;
                    for (AForce f : forces) {// Check if force with same rotation already exists
                        if (f.getRotation() == Main.ship.getRotation()) {
                            f.setLength(f.getLength() + 1);
                            newObject = false;
                        }
                    }
                    if (newObject) {// If not, create new force
                        forces.add(new AForce(Main.ship.getRotation(), 2));
                    }
                    resist = 10.0;// Reset resistance
                }
                if (!Main.ship.isThrust() && counter % 50 == 0) {// If ship is not thrusting, reduce length of forces
                    for (AForce f : forces) {// Reduce length of forces by resist to simulate friction and all forces expire at the same time
                        f.setLength(f.getLength() - (f.getLength() / resist));
                    }
                    resist--;// Make resist smaller for stronger resistance
                }
            }
            if (((Main.ship.isShooter() && counter % 100 == 0 || Main.ship.isShoot()) && Main.bullets.size() < 15) && Main.coolDown <= 0) {// Shoot bullet if key is pressed and there are less than 15 bullets
                // Makes pattern, that you can shout more bullets after each other but if you hold it will just shoot one every 100 ticks
                Main.ship.setShoot(false);
                Main.bullets.add(new Bullet(25.0, Main.ship.getRotation(), Main.ship.getPosition()));

                boolean newObject = true;
                for (AForce f : forces) {// Check if force with same rotation already exists
                    if (f.getRotation() == 180 + Main.ship.getRotation()) {// If yes, increase length of force when shooting as recoil
                        f.setLength(f.getLength() + 0.2);
                        newObject = false;
                    }
                }
                if (newObject) {// If not, create new force with recoil
                    forces.add(new AForce(Main.ship.getRotation() + 180, 0.2));
                }
                resist = 10.0;
            }
            moveShip();
            moveBullets();
            moveAsteroids();

            if ((counter % 4 == 0) && Main.coolDown <= 0) {// Move forces every 4th tick
                Collider collider = new Collider();
                TwoCondition shipCollides = collider.shipAsteroid();
                if (shipCollides.condition1) {// Check if ship collides with an asteroid and if yes, reset ship position and remove one life
                    Main.lives -= 1;
                    Main.coolDown = 10;
                    Main.ship.setPosition(new APoint((double) AFrame.frameDimension.width / 2, (double) AFrame.frameDimension.height / 2));
                    forces.clear();
                    if (Main.lives <= 0) {
                        System.exit(0);
                    }
                }
                if (shipCollides.condition2) {
                    breakAsteroid();
                }
                if (collider.bulletAsteroids()) {
                    breakAsteroid();
                } else {
                    if (Main.asteroids.size() == 0) {// If all asteroids are destroyed, start new level
                        Main.level += 1;
                        Main.startALevel();
                        if (Main.level % 3 == 0 && Main.lives < 5) {
                            Main.lives += 1;
                        }
                    }
                }
            }
            if (Main.coolDown % 2 == 0 && Main.coolDown > 0) {
                shipColor = Color.white;
            } else if (Main.coolDown % 2 == 1) {
                shipColor = Color.decode("#39EEFF");
            } else if (shipColor != Color.white) {
                shipColor = Color.white;
            }

            if (Main.coolDown > 0 && counter % 25 == 0) {
                Main.coolDown--;
            }

            counter++;
            counter = counter % 1000;
        });

    }

    @Override
    protected void paintComponent(Graphics g) {// Draw everything on screen
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw Info on top left corner
        g2d.setColor(Color.white);
        g2d.translate(20 * 5, 50);
        g2d.setFont(atariFont.deriveFont(Font.PLAIN, 13));
//        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 13));
        g2d.drawString("Points: " + Main.points, 0, 0);
        g2d.drawString(" Level: " + Main.level, 0, 15);
        g2d.translate(-20 * 5, -50);

        // Draw Start Dialog
        if(!Main.gameTimer.isRunning()) {
            g2d.setColor(Color.white);
            g2d.translate(AFrame.frameDimension.width / 2 - 269, AFrame.frameDimension.height / 2 + 100);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35));
            g2d.drawString("Press Enter To Start",0,0);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 15));
            g2d.translate(-AFrame.frameDimension.width / 2 + 269, -AFrame.frameDimension.height / 2 - 100);
        }

        // Draw Lives on left corner
        g2d.setColor(Color.gray);
        for (int i = 1; i < 5; i++) {
            transLives(g2d, i);
        }

        // Draw Lives on left corner
        g2d.setColor(Color.white);
        for (int i = 1; i < Main.lives; i++) {
            transLives(g2d, i);
        }


        // Draw asteroids
        if (Main.asteroids.size() > 0) {
            g2d.setColor(Color.white);
            for (Asteroid a : Main.asteroids) {
                g2d.translate(a.getPosition().x + 2, a.getPosition().y);
                g2d.drawPolygon(a.getPolygon());
//                g2d.drawString(String.valueOf(a.getHitCount()), 0, 0);
                g2d.translate(-(a.getPosition().x + 2), -(a.getPosition().y));
            }
        }

        // Draw bullets
        if (Main.bullets.size() > 0) {
            g2d.setColor(Color.decode("#39EEFF"));
            for (Bullet b : Main.bullets) {
                g2d.translate(b.getPosition().x + 1, b.getPosition().y);
                g2d.fillRect(-2, -2, 2, 2);
                g2d.translate(-(b.getPosition().x + 1), -(b.getPosition().y));
            }
        }

        // Draw spaceship
        g2d.setColor(shipColor);
        g2d.translate(Main.ship.getPosition().x, Main.ship.getPosition().y);
        g2d.rotate(Math.toRadians(Main.ship.getRotation()));
        g2d.drawLine(-7, -10, 7, -10);
        g2d.drawLine(-7, -10, 0, 15);
        g2d.drawLine(7, -10, 0, 15);

        // Draw spaceship booster
        if (Main.ship.isThrust()) {
            g2d.drawLine(-6, -10, -4, -14);
            g2d.drawLine(-4, -14, -2, -10);
            g2d.drawLine(-2, -10, 0, -14);
            g2d.drawLine(0, -14, 2, -10);
            g2d.drawLine(2, -10, 4, -14);
            g2d.drawLine(4, -14, 6, -10);
        }

        repaint();
        if (Objects.equals(System.getProperty("os.name"), "Linux")) {// Fixing lags on Linux systems
            Toolkit.getDefaultToolkit().sync();
        }
    }

    private void transLives(Graphics2D g2d, int i) {// Draw lives on left corner
        g2d.translate(20 * i, 50);
        g2d.rotate(Math.toRadians(180));
        g2d.drawLine(-7, -10, 7, -10);
        g2d.drawLine(-7, -10, 0, 15);
        g2d.drawLine(7, -10, 0, 15);
        g2d.rotate(Math.toRadians(180));
        g2d.translate(-20 * i, -50);
    }

    void moveShip() {// Move ship according to forces
        for (int i = forces.size() - 1; i >= 0; i--) {// Remove forces with length 0
            if (forces.get(i).getLength() == 0) {
                forces.remove(i);
            }
        }

        if (forces.size() > 0) {// Calculate new position of ship according to the vectors of the forces and rotation to get the new position of the ship
            double x = 0;
            double y = 0;
            for (AForce f : forces) {
                x += (f.getLength() / 10.0) * Math.sin(Math.toRadians(f.getRotation()));
                y += (f.getLength() / 10.0) * Math.cos(Math.toRadians(f.getRotation()));
            }
            Main.ship.setPosition(new APoint(Main.ship.getPosition().x - x, Main.ship.getPosition().y + y));
        }
        // Screen wrap
        if (Main.ship.getPosition().x <= 0) {// Left Edge
            Main.ship.setX(AFrame.frameDimension.width);
        }
        if (Main.ship.getPosition().x >= AFrame.frameDimension.width + 2) {// Right edge
            Main.ship.setX(2);
        }
        if (Main.ship.getPosition().y <= 0) {// Top edge
            Main.ship.setY(AFrame.frameDimension.height);
        }
        if (Main.ship.getPosition().y >= AFrame.frameDimension.height + 2) {// Bottom edge
            Main.ship.setY(2);
        }

    }

    void moveBullets() {// Move bullets according to their speed and rotation
        for (int i = Main.bullets.size() - 1; i >= 0; i--) {// Remove bullets with life 0
            if (Main.bullets.get(i).getLife() == 0) {
                Main.bullets.remove(i);
            } else {// Decrease life of bullets to remove them after a certain time
                Main.bullets.get(i).setLife(Main.bullets.get(i).getLife() - 1);
            }
        }
        if (Main.bullets.size() > 0) {// Calculate new position of bullets according to their speed and rotation
            for (Bullet b : Main.bullets) {
                double x = (b.getSpeed() / 10.0) * Math.sin(Math.toRadians(b.getRotation()));
                double y = (b.getSpeed() / 10.0) * Math.cos(Math.toRadians(b.getRotation()));

                b.setPosition(new APoint(b.getPosition().x - x, b.getPosition().y + y));

                // Screen wrap
                if (b.getPosition().x <= 0) {// Left Edge
                    b.setX(AFrame.frameDimension.width - 2);
                }
                if (b.getPosition().x >= AFrame.frameDimension.width) {// Right edge
                    b.setX(2);
                }
                if (b.getPosition().y <= 0) {// Top edge
                    b.setY(AFrame.frameDimension.height - 2);
                }
                if (b.getPosition().y >= AFrame.frameDimension.height) {// Bottom edge
                    b.setY(2);
                }

            }
        }
    }

    void moveAsteroids() {
        if (Main.asteroids.size() > 0) {
            for (Asteroid a : Main.asteroids) {// Calculate new position of asteroids according to their speed and rotation
                double x = (a.getSpeed() / 10.0) * Math.sin(Math.toRadians(a.getRotation()));
                double y = (a.getSpeed() / 10.0) * Math.cos(Math.toRadians(a.getRotation()));

                a.setPosition(new APoint(a.getPosition().x - x, a.getPosition().y + y));

                // Screen wrap
                if (a.getPosition().x <= 0) {// Left Edge
                    a.setX(AFrame.frameDimension.width - 2);
                }
                if (a.getPosition().x >= AFrame.frameDimension.width) {// Right edge
                    a.setX(2);
                }
                if (a.getPosition().y <= 0) {// Top edge
                    a.setY(AFrame.frameDimension.height - 2);
                }
                if (a.getPosition().y >= AFrame.frameDimension.height) {// Bottom edge
                    a.setY(2);
                }
            }
        }
    }

    void breakAsteroid() {
        double r1 = Main.lastBullet.getRotation();

        Random r = new Random();
        for (int i = 0; i < r.nextInt(3) + 1; i++) {
            Main.asteroids.add(new Asteroid(Main.recentlyDestroyed.getPosition(), r.nextInt(10) + r1 - 5, r.nextInt(10) + 5, 0));

        }
    }
}