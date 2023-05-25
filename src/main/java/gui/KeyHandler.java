package gui;
import main.APoint;
import main.Main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

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
        if(e.getKeyCode() == KeyEvent.VK_S) {
            Random r = new Random();
            Main.ship.setPosition(new APoint(r.nextInt(AFrame.frameDimension.width), r.nextInt(AFrame.frameDimension.height)));
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!Main.gameTimer.isRunning()) {
                Main.gameTimer.start();
                Main.fpsTimer.start();
            }
            else {
                Main.gameTimer.stop();
                Main.fpsTimer.stop();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_1){
            Main.fps = 1000/30;
            Main.fpsTimer.setDelay(Main.fps);
            System.out.println("changed delay to: " + 1000 / Main.fps);
        }
        if(e.getKeyCode() == KeyEvent.VK_2){
            Main.fps = 1000/60;
            Main.fpsTimer.setDelay(Main.fps);
            System.out.println("changed delay to: " + 1000 / Main.fps);
        }
        if(e.getKeyCode() == KeyEvent.VK_3){
            Main.fps = 1000/90;
            Main.fpsTimer.setDelay(Main.fps);
            System.out.println("changed delay to: " + 1000 / Main.fps);
        }
        if(e.getKeyCode() == KeyEvent.VK_4){
            Main.fps = 1000/140;
            Main.fpsTimer.setDelay(Main.fps);
            System.out.println("changed delay to: " + 1000 / Main.fps);
        }
        if(e.getKeyCode() == KeyEvent.VK_5){
            Main.fps = 1000/200;
            Main.fpsTimer.setDelay(Main.fps);
            System.out.println("changed delay to: " + 1000 / Main.fps);
        }

    }
}