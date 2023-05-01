package gui;

import main.APoint;
import main.Main;
import objects.AForce;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ShipDraw extends JPanel {
    int counter = 0;
    double resist = 0.0;
    ArrayList<AForce> forces = new ArrayList<>();

    public ShipDraw() {
        Timer timer = new Timer(5, (e) ->  {
            if(Main.ship.isRotatesRight()){
                double r = Main.ship.getRotation() + 2.5;
                Main.ship.setRotation(r % 360);
            }
            if(Main.ship.isRotatesLeft()){
                double r = Main.ship.getRotation() - 2.5;
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
                    resist = 20.0;
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

            wrapShip();
            counter++;
            counter = counter % 1000;
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);

        g2d.translate(Main.ship.getPosition().x,Main.ship.getPosition().y);
        g2d.rotate(Math.toRadians(Main.ship.getRotation()));
        g2d.drawLine(-7, -10, 7, -10);
        g2d.drawLine(-7, -10, 0, 15);
        g2d.drawLine(7, -10, 0, 15);

        if(Main.ship.isThrust()){
            g2d.drawLine(-6,-10,-4,-14);
            g2d.drawLine(-4,-14,-2,-10);
            g2d.drawLine(-2,-10,0,-14);
            g2d.drawLine(0,-14,2,-10);
            g2d.drawLine(2,-10,4,-14);
            g2d.drawLine(4,-14,6,-10);
        }

        repaint();
        if(Objects.equals(System.getProperty("os.name"), "Linux")){
            Toolkit.getDefaultToolkit().sync();
        }
    }

    void wrapShip(){
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
}