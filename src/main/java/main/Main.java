package main;

import gui.AFrame;
import objects.Ship;

public class Main {
    public static Ship ship = new Ship(new APoint((double) AFrame.frameDimension.width / 2, (double) AFrame.frameDimension.height / 2),0);
    public static void main(String[] args) {
        new AFrame();
    }
}
