package gui;

import javax.swing.*;
import java.awt.*;

public class AFrame extends JFrame {
    static int shipLength = 35;
    public static Dimension frameDimension = new Dimension(36 * shipLength, 25 * shipLength);

    public AFrame() {// creates the JFrame and adds the Draw class to it as well as the KeyHandler
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenDimension = getToolkit().getScreenSize();
        setLocation((screenDimension.width - frameDimension.width) / 2, (screenDimension.height - frameDimension.height) / 2);
        setLayout(new BorderLayout());
        setTitle("Asteroids");
        addKeyListener(new KeyHandler());

        Draw mainScreen = new Draw();
        mainScreen.setLocation(0, 0);
        mainScreen.setPreferredSize(frameDimension);
        mainScreen.setBackground(Color.black);

        Container cp = getContentPane();
        cp.add(mainScreen);
        pack();
        setVisible(true);
    }
}