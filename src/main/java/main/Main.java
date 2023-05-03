package main;
import gui.AFrame;
import objects.Asteroid;
import objects.Bullet;
import objects.Ship;
import java.util.*;

public class Main {
    public static Ship ship = new Ship(new APoint((double) AFrame.frameDimension.width / 2, (double) AFrame.frameDimension.height / 2));// new Ship(new APoint(100,100));

    public static ArrayList<Bullet> bullets = new ArrayList<>();// ArrayList of bullets that are currently on the screen
    public static ArrayList<Asteroid> asteroids = new ArrayList<>();// ArrayList of asteroids that are currently on the screen

    public static Asteroid recentlyDestroyed;// the asteroid that was most recently destroyed to hand over more information than in a boolean return statement
    public static int lives = 5;
    public static int points = 0;
    public static int level = 0;

    public static int coolDown = 20;// 3 seconds for cool down after starting a new level or crashing

//    public static ArrayList<File> files = new ArrayList<>();
//    public static ArrayList<AudioInputStream> audioInputStreams = new ArrayList<>();

    public static void main(String[] args) {
        startALevel();
        new AFrame();
    }

    public static void startALevel(){// method to start a new level with a certain amount of asteroids
        coolDown = 20;
        Random r = new Random();
            for(int i = 0; i < level + 2; i++){
                double x;
                double y;
                int sX = Math.toIntExact(Math.round(ship.getPosition().x));
                int sY = Math.toIntExact(Math.round(ship.getPosition().y));
                do{
                    x = r.nextInt(sX);
                    y = r.nextInt(sY);
                }while(x < sX / 2.0 - 100 && x > sX / 2.0 + 100 && y < sY / 2.0 - 100 && y > sY / 2.0 + 100);
//            Main.asteroids.add(new Asteroid(new APoint(x,y),r.nextInt(360),r.nextInt(15) + 5,1));
            Main.asteroids.add(new Asteroid(new APoint(x,y),r.nextInt(360),0,1));
        }
    }

//    public static void initialiseSounds() throws URISyntaxException, UnsupportedAudioFileException, IOException {
//        files.add(new File(Main.class.getResource("/fire.wav").toURI()));
//        files.add(new File(Main.class.getResource("/thrust.wav").toURI()));
//        files.add(new File(Main.class.getResource("/bangSmall.wav").toURI()));
//        files.add(new File(Main.class.getResource("/bangLarge.wav").toURI()));
//        files.add(new File(Main.class.getResource("/beat1.wav").toURI()));
//        files.add(new File(Main.class.getResource("/beat2.wav").toURI()));
//
//        audioInputStreams.add(AudioSystem.getAudioInputStream(files.get(0)));
//        audioInputStreams.add(AudioSystem.getAudioInputStream(files.get(1)));
//        audioInputStreams.add(AudioSystem.getAudioInputStream(files.get(2)));
//        audioInputStreams.add(AudioSystem.getAudioInputStream(files.get(3)));
//        audioInputStreams.add(AudioSystem.getAudioInputStream(files.get(4)));
//        audioInputStreams.add(AudioSystem.getAudioInputStream(files.get(5)));
//    }
}
