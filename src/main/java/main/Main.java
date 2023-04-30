package main;

import gui.AFrame;
import objects.Ship;

import java.awt.*;

public class Main {
    public static Ship ship = new Ship(new Point(AFrame.frameDimension.width / 2, AFrame.frameDimension.height / 2),0);
    public static void main(String[] args) {
        new AFrame();
    }
}
