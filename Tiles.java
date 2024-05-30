/**
 * A class that represents the falling tiles
 *
 * @Jimmy Um
 */
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tiles {
    private final int column;
    private static double velY;
    private final double x;
    private double y;
    private double initialY;
    private final Image image;
    private boolean clicked;

    /**
     * Initializes a tiles abject with a given bpm, column, and second
     * @param bpm the beats per minute of the song
     * @param column the column in which the tiles should fall
     * @param beat the specific beat that the tile should appear at
     */
    public Tiles(double bpm, int column, double beat) {
        this.column = column;

        //calculates the milliseconds per beat
        double mspb = 1000 / (bpm / 60);

        //calculates how far up the tile should appear
        double offset = mspb * (beat * 1.0465);

        clicked = false;
        velY = 0;
        x = column * 70 + 10;
        int songOffset = 820;

        //calculates the y position of the tile based on the offset and delay offset
        y = (-1 * offset) - 600 - songOffset;
        initialY = y;

        //creates an image object which represents what the tile should look like.
        image = new BufferedImage(70, 40, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 70, 40);
        g.dispose();
    }

    /**
     * Returns the column of the tile
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * returns the y position of the tile
     * @return y position
     */
    public double getY() {
        return y;
    }

    /**
     * returns the initial y position of a tile
     * @return initial y position
     */
    public double getInitialY() {
        return initialY;
    }

    /**
     * changes the y position downward by changeY
     * @param changeY the number of pixels the tile should be pushed downward
     */
    public void changeY(double changeY) {
        this.y += changeY;
    }

    /**
     * returns whether the tile was clicked on
     * @return if the tile was clicked
     */
    public boolean isClicked() {
        return clicked;
    }

    /**
     * updates the tile y position, and changes y position by velY
     */
    public void move() {
        y += velY;
    }

    /**
     * starts the program, and sets velY to 1.33333333333
     */
    public static void start() {
        velY = 1.33333333333333333333333;
    }

    /**
     * sets clicked to true
     */
    public void clicked() {
        clicked = true;
    }

    /**
     * draws the tile
     * @param g the graphics object provided by the paintComponent method
     */
    public void draw(Graphics g) {
        int x = (int) this.x;
        int y = (int) this.y;
        g.drawImage(image, x, y, null);
    }
}
