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
        addKeyListener(new KeyHandler());

        Draw mainScreen = new Draw();
        mainScreen.setLocation(0, 0);
        mainScreen.setSize(frameDimension);
        mainScreen.setBackground(Color.black);

//        BulletDraw bulletPanel = new BulletDraw();
//        bulletPanel.setLocation(0, 0);
//        bulletPanel.setSize(frameDimension);
//        bulletPanel.setBackground(null);

        Container cp = getContentPane();
        cp.add(mainScreen);
//        cp.add(bulletPanel);
        setVisible(true);
    }
}