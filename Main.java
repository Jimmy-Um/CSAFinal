/**
 * Plays the audio and creates and tests the Game class
 * @author Jimmy Um
*/
import javax.sound.sampled.*;
import javax.swing.JFrame;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Timeline - Plum");
        Game g = new Game();
        frame.add(g);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(440, 620);
        playSound();
    }

    /**
     * plays the song
     * @throws Exception throws the IOException, LineUnavailableException, and UnsupportedAudioFileException
     */
    public static void playSound () throws Exception {
        URL url = new URL("file:///Users/jimmyum/IdeaProjects/CSAFinal/Timeline.wav");
        Clip clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
        clip.open(inputStream);
        clip.start();
    }
}