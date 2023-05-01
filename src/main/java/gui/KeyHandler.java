package gui;

import main.Main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_A){
            Main.ship.setRotatesLeft(true);
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            Main.ship.setRotatesRight(true);
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            Main.ship.setThrust(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(!Main.ship.isShooter()){
                Main.ship.setShoot(true);
            }
            Main.ship.setShooter(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        if(e.getKeyCode() == KeyEvent.VK_A){
            Main.ship.setRotatesLeft(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            Main.ship.setRotatesRight(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            Main.ship.setThrust(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Main.ship.setShooter(false);
            Main.ship.setShoot(false);
        }
    }
}