package gui;

import main.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AFrame extends JFrame {
    static int shipLength = 35;
    public static Dimension frameDimension = new Dimension(36 * shipLength, 25 * shipLength);
    public static Container cp;

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

        cp = getContentPane();
        cp.add(mainScreen);
        pack();

        setVisible(true);
    }

    public static void changeScene(){
        cp.remove(0);
        cp.revalidate();
        cp.repaint();

        Main.gameTimer.stop();

        JPanel p = new JPanel();
        p.setSize(frameDimension);
        p.setLocation(0,0);
        p.setBackground(Color.black);
        p.setLayout(null);

        JLabel l = new JLabel("enter name");
        l.setBounds(160,frameDimension.height / 2 + 300,500,50);
        l.setFont(Main.atariFont.deriveFont(Font.BOLD, 25));
        l.setForeground(Color.white);

        JTextField tf = new JTextField();
        tf.setBounds(frameDimension.width / 2 - 250,frameDimension.height / 2 + 300,500,50);
        tf.setBackground(null);
        tf.setForeground(Color.white);
        tf.setFont(Main.atariFont.deriveFont(Font.BOLD, 25));
        tf.setBorder(new LineBorder(Color.white));
        tf.requestFocus();
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String playerName = tf.getText();
                    System.out.println(playerName + ": " + Main.points);
                    tf.setText("");
                }
            }
        });

        p.add(tf);
        p.add(l);
        cp.add(p);
    }
}