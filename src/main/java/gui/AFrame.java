package gui;

import javax.swing.*;
import java.awt.*;

public class AFrame extends JFrame {
    static int shipLength = 35;
    public static Dimension frameDimension = new Dimension(36 * shipLength, 25 * shipLength);

    public AFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenDimension = getToolkit().getScreenSize();
        setLocation((screenDimension.width - frameDimension.width) / 2, (screenDimension.height - frameDimension.height) / 2);
        setSize(frameDimension);
        setBackground(Color.BLACK);
        addKeyListener(new KeyHandler());

        ShipDraw background = new ShipDraw();
        background.setLocation(0, 0);
        background.setSize(frameDimension);
        background.setBackground(Color.black);

        Container cp = getContentPane();
        cp.add(background);
        setVisible(true);
    }
}