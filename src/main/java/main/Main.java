package main;
import gui.AFrame;
import objects.Asteroid;
import objects.Bullet;
import objects.Ship;
import javax.sound.sampled.*;
import java.io.File;
import java.util.*;

public class Main {
    public static Ship ship = new Ship(new APoint((double) AFrame.frameDimension.width / 2, (double) AFrame.frameDimension.height / 2));

    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static ArrayList<Asteroid> asteroids = new ArrayList<>();

    public static Asteroid recentlyDestroyed;
    public static int lives = 5;
    public static int points = 0;
    public static int level = 0;

//    public static ArrayList<File> files = new ArrayList<>();
//    public static ArrayList<AudioInputStream> audioInputStreams = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Hello an welcome to Asteroids");
        System.out.print("please indicate your level you would like to start (0 for default): ");
        boolean rightInput = false;
        while(!rightInput){
            String input = scan.next();
            try {
                level = Integer.parseInt(input);
                rightInput = true;
            }
            catch (Exception e) {
                System.out.print("False input please try again: ");
            }
        }
        startALevel();
        new AFrame();
    }

    public static void startALevel(){
        Random r = new Random();
            for(int i = 0; i < level + 2; i++){
                double x;
                double y;
                int fw = AFrame.frameDimension.width;
                int fh = AFrame.frameDimension.width;
                do{
                    x = r.nextInt(fw);
                    y = r.nextInt(fh);
                }while(x < fw / 2.0 - 50 && x > fw / 2.0 + 50 && y < fh / 2.0 - 50 && y > fh / 2.0 + 50);

            Main.asteroids.add(new Asteroid(new APoint(x,y),r.nextInt(360),r.nextInt(15) + 5,1));
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
