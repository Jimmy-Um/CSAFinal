/**
 * A class that sets up the graphical assets of the program and continuously repaints
 * the screen to update the tiles and keys
 *
 * @author Jimmy Um
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener, KeyListener {
    private boolean[] pressed = {false, false, false, false, false, false};
    private ArrayList<Tiles> list = new ArrayList<Tiles>();
    private ArrayList<Tiles> activeTiles = new ArrayList<Tiles>();
    private String[] keys = {"Q", "3", "R", "I", "0", "="};
    private int numMissed = 0;
    private int numPerfect = 0;
    private int numLateEarly = 0;
    private int numVeryLateEarly = 0;
    private double accuracy;
    private int gameEnded;
    private String score;

    //the beat at which each tile should fall. Each column is linked to a key
    double[] col1 = {};
    double[] col2 = {93.5, 94.5, 98.5, 101.5, 106.5, 108.5, 126.5, 127.5};
    double[] col3 = {0.5, 1.5, 2.5, 6.5, 7.5, 8.5, 13.5, 14.5, 16.5, 17.5, 18.5, 19.5, 20.5, 21.5, 22.5, 23.5, 24.5, 25.5, 26.5, 30.5, 31.5, 32.5, 37.5, 38.5, 40.5, 41.5, 42.5, 43.5, 44.5, 45.5, 46.5, 47.5, 49.5, 53.5, 55.5, 64.5, 65.5, 66.5, 67.6, 68.6, 69.6, 70.6, 71.6, 72.6,  73.6,  74.6, 77.5,  79.5,  80.5, 83.5,  85.5, 86.5, 88.5, 88.5, 89.5, 90.5, 91.5, 92.5, 93.75, 94.75, 95.5, 96.5, 97.5, 98.75, 99.5, 100.5, 101.75, 102.5, 103.5, 104.5, 105.5, 106.75, 107.5, 108.75, 109.5, 110.5, 111.5, 112.5, 113.5, 114.5, 115.5, 116.25, 116.75, 117.5, 118.5, 119.25, 119.75, 120.5, 121.25, 121.75, 122.5, 123.5, 124.25, 124.75, 125.5, 126.75, 127.75, 128.5, 129.25, 129.75, 130.25, 130.75, 131.25, 131.75, 132.25,132.75, 133.25, 133.75, 134.5, 135.25, 135.75, 136.25, 136.75, 137.5, 138.25, 138.75, 139.25, 139.75, 140.25, 140.75};
    double[] col4 = {0.166666667, 1, 2, 3.166666667, 4, 6.166666667, 7, 8, 9.166666667, 10, 11, 12.166666667,13, 14, 15.166666667, 16, 17, 18.166666667, 19, 20, 21.166666667, 22, 23, 24.166666667, 25, 26, 27.166666667, 28, 30.166666667, 31, 32, 33.166666667, 34, 35, 36.166666667, 37, 38, 39.166666667, 40, 41, 42.166666667, 43, 44, 45.166666667, 46, 47, 48, 49, 50, 51, 52, 53, 54, 56, 57, 59, 60, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 80, 81, 82, 83, 84, 86, 87, 87, 89, 90, 91, 92, 93, 94, 95, 96.166666667, 97, 98, 99.166666667, 100, 101, 102.166666667, 103, 104, 105.166666667, 106, 107, 108.166666667, 109, 110, 111.166666667, 112, 113, 114.166666667, 115, 116, 116.5, 117.166666667, 118, 119, 119.5, 120.166666667, 121, 121.5, 122, 123.166666667, 124, 124.5, 125, 126.166666667, 127, 128, 129.166666667, 129.5, 130, 130.5, 131, 131.5, 132.166666667, 132.5, 133, 133.5, 134, 135.166666667, 135.5, 136, 136.5, 137, 138.166666667, 138.5, 139, 139.5, 140, 140.5, 141.166666667};
    double[] col5 = {0.0833333333, 3.0833333333, 6.0833333333, 9.0833333333, 12.0833333333, 15.0833333333, 18.0833333333, 21.0833333333, 24.0833333333, 27.0833333333, 30.0833333333, 33.0833333333, 36.0833333333, 39.0833333333, 42.0833333333, 45.0833333333, 96.0833333333, 99.0833333333, 102.0833333333, 105.0833333333, 108.0833333333, 111.0833333333, 114.0833333333, 117.0833333333, 120.0833333333, 123.0833333333, 126.0833333333, 129.0833333333, 132.0833333333, 135.0833333333, 138.0833333333, 141.0833333333};
    double[] col6 = {0, 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 96, 99, 102, 105, 108, 111, 114, 117, 120, 123, 126, 129, 132, 135, 138, 141};

    double[][] tiles = {col1, col2, col3, col4, col5, col6};

    Timer t = new Timer(1, this);

    /**
     * Initializes the game, and sets up the variables needed for the class
     */
    public Game() {

        //adding all tiles to "list"
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                list.add(new Tiles(180, i, tiles[i][j]));
            }
        }

        //sorting list by which tile comes first in the song
        list.sort((a, b) -> (int) (b.getY() * 10 - a.getY() * 10));

        //an active list which keeps track of up to 10 current tiles falling. Reduces delay as
        // repainting every tile takes time
        for (int i = 0; i < 10; i++) {
            activeTiles.add(list.get(i));
        }

        gameEnded = 0;

        setBackground(Color.WHITE);
        t.start();
        addKeyListener(this);
        this.setFocusable(true);

        Tiles.start();
    }

    /**
     * paints the graphical components of the class, and updates based on the timer object
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Column setup
        g.setColor(Color.BLACK);
        g.drawLine(10, 0, 10, 600);
        g.drawLine(80, 0, 80, 600);
        g.drawLine(150, 0, 150, 600);
        g.drawLine(220, 0, 220, 600);
        g.drawLine(290, 0, 290, 600);
        g.drawLine(360, 0, 360, 600);
        g.drawLine(430, 0, 430, 600);
        g.drawLine(0, 500, 440, 500);

        //Accuracy system:
        //accuracy = (300 * number of perfect judgements + 100 * number of early/late judgements +
        //50 * number of very early/late judgement) / (num of perfects + num of early/late +
        //num of very early/late + num of missed tiles)
        if (numLateEarly + numPerfect + numVeryLateEarly + numMissed > 0) {
            accuracy = (double) (300 * numPerfect + 100 * numLateEarly + 50 * numVeryLateEarly) / (300 * (numLateEarly + numPerfect + numVeryLateEarly + numMissed));
            g.drawString("Accuracy: " + (int) (accuracy * 100) + "%", 10, 50);
        }
        else {
            g.drawString("Accuracy: N/A", 10, 50);
        }

        score(g);

        //repainting the tiles
        for (Tiles t : activeTiles) {
            t.draw(g);
        }

        //shows which keys are pressed
        keyRepaint(g);

        //keeps track of missed tiles and removes tiles which have already passed
        if (!activeTiles.isEmpty()) {
            checkIfClicked();

            int j = 0;

            for (int i = 0; j < activeTiles.size(); i++) {
                if (activeTiles.get(j).getY() > 680) {
                    if (!activeTiles.isEmpty() && !activeTiles.get(j).isClicked()) {
                        numMissed++;
                        System.out.println("missed");
                    }

                    activeTiles.remove(j);
                    list.remove(j);
                    if (list.size() > activeTiles.size()) {
                        Tiles t = list.get(activeTiles.size());
                        t.changeY(activeTiles.get(j).getY() - activeTiles.get(j).getInitialY());
                        activeTiles.add(t);
                    }

                }
                else {
                    j++;
                }
            }
        }

        //if the list is empty, gameEnded is increased to 1.
        if(list.isEmpty()) {
            gameEnded++;
            if(gameEnded > 3){
                gameEnded = 2;
            }
        }

        //gameEnded equals 1, the message dialog pops up. I did this using a int variable instead of
        //as the dialog box needs to only show up once, which is when gameEnded first gets added 1 to its value.
        if (gameEnded == 1) {
            JOptionPane.showMessageDialog(this, "Game Over\nAccuracy: " + (int)(accuracy * 100) + "\nScore: " + score, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            t.stop();
        }

    }

    /**
     * moves the tiles down
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Tiles t : activeTiles) {
            t.move();
        }
        repaint();
    }

    /**
     * not used
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * detects whether the user clicked a specific key corresponding to a column
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) {
            pressed[0] = true;
            repaint();
        }
        if (code == KeyEvent.VK_3) {
            pressed[1] = true;
            repaint();
        }
        if (code == KeyEvent.VK_R) {
            pressed[2] = true;
            repaint();
        }
        if (code == KeyEvent.VK_I) {
            pressed[3] = true;
            repaint();
        }
        if (code == KeyEvent.VK_0) {
            pressed[4] = true;
            repaint();
        }
        if (code == KeyEvent.VK_EQUALS) {
            pressed[5] = true;
            repaint();
        }
    }

    /**
     * detects whether the user released a specific key corresponding to a column
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) {
            pressed[0] = false;
            repaint();
        }
        if (code == KeyEvent.VK_3) {
            pressed[1] = false;
            repaint();
        }
        if (code == KeyEvent.VK_R) {
            pressed[2] = false;
            repaint();
        }
        if (code == KeyEvent.VK_I) {
            pressed[3] = false;
            repaint();
        }
        if (code == KeyEvent.VK_0) {
            pressed[4] = false;
            repaint();
        }
        if (code == KeyEvent.VK_EQUALS) {
            pressed[5] = false;
            repaint();
        }

    }

    /**
     * updates the keys to change color when clicked
     * @param g Graphics object provided by the paintComponent method
     */
    public void keyRepaint(Graphics g) {
        //if the key is pressed, repaint the box to black
        for (int i = 0; i < pressed.length; i++) {
            if (pressed[i]) {
                g.setColor(Color.BLACK);
                g.fillRect(70 * i + 10, 500, 70, 100);
                g.setColor(Color.WHITE);
                g.drawString(keys[i], 40 + 70 * i, 550);
            }
        //if the key is released, repaint the box to gray.
            else {
                g.setColor(Color.GRAY);
                g.fillRect(70 * i + 10, 500, 70, 100);
                g.setColor(Color.BLACK);
                g.drawString(keys[i], 40 + 70 * i, 550);
            }
        }
    }

    /**
     * Checks of a tile was clicked, and how accurately it was clicked. Updates the number of perfect clicks,
     * late/early clicks, and very late/early clicks.
     */
    public void checkIfClicked() {
        for (int i = 0; i < activeTiles.size(); i++) {

            //a perfect judgement is achieved if a tile is clicked on within 80 pixels of y = 500
            if (Math.abs((activeTiles.get(i).getY() + 40) - 500) < 80 && !activeTiles.get(i).isClicked() && pressed[activeTiles.get(i).getColumn()]) {
                numPerfect++;
                //System.out.println("perfect: " + ((activeTiles.get(i).getY() + 40) - 500));
                activeTiles.get(i).clicked();
            }
            //a early/late judgement is achieved if a tile is clicked on within 120 pixels of y = 500
            else if (Math.abs((activeTiles.get(i).getY() + 40) - 500) < 120 && !activeTiles.get(i).isClicked() && pressed[activeTiles.get(i).getColumn()]) {
                numLateEarly++;
                //System.out.println("early/late: " + ((activeTiles.get(i).getY() + 40) - 500));
                activeTiles.get(i).clicked();
            }
            //a very early/late judgement is achieved if a tile is clicked on within 140 pixels of y = 500
            else if (Math.abs((activeTiles.get(i).getY() + 40) - 500) < 140 && !activeTiles.get(i).isClicked() && pressed[activeTiles.get(i).getColumn()]) {
                numVeryLateEarly++;
                //System.out.println("very early/late: " + ((activeTiles.get(i).getY() + 40) - 500));
                activeTiles.get(i).clicked();
            }
        }
    }

    /**
     * calculates the score of the player and paints the score
     * @param g Graphics object provided by the paintComponent method
     */
    public void score(Graphics g) {

        // if the player has 100%, the score is SS
        if (accuracy * 100 == 100) {
            g.drawString("Score: SS", 10, 70);
            score = "SS";
        }
        // if the player has more than 95%, the score is S
        else if (accuracy * 100 > 95) {
            g.drawString("Score: S", 10, 70);
            score = "S";
        }
        // if the player has more than 90%, the score is A
        else if (accuracy * 100 > 90) {
            g.drawString("Score: A", 10, 70);
            score = "A";
        }
        // if the player has more than 80%, the score is B
        else if (accuracy * 100 > 80) {
            g.drawString("Score: B", 10, 70);
            score = "B";
        }
        // if the player has more than 70%, the score is C
        else if (accuracy * 100 > 70) {
            g.drawString("Score: C", 10, 70);
            score = "C";
        }
        // anything 70 or lower is a D
        else {
            g.drawString("Score: D", 10, 70);
            score = "D";
        }
    }

}
