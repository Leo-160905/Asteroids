package gui;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Draw extends JPanel {
    double rotation = 0;

    public Draw() {
        Timer timer = new Timer(10, (e) ->  {
                if(Main.ship.isRotatesRight()){
                    rotation += 5.0;
                }
                if(Main.ship.isRotatesLeft()){
                    rotation -= 5.0;
                }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);

        g2d.translate(Main.ship.getPosition().x,Main.ship.getPosition().y);
        g2d.rotate(rotation);
        g2d.drawLine(-7, -10, 7, -10);
        g2d.drawLine(-7, -10, 0, 15);
        g2d.drawLine(7, -10, 0, 15);

        repaint();
    }
}
