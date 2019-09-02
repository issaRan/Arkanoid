import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author ISSA 10/06/18
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scoresTable;
    private KeyboardSensor keyboard;
    private String string;
    private boolean stop;
    //The screen size.
    static final int GUIWIDTH = 800;
    static final int GUIHEIGHT = 600;

    /**
     * .
     * Creates new instance of HighScoresAnimation.
     * The constructor of our class.
     *
     * @param scores an high score table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scoresTable = scores;
        // this.keyboard = keyboard;
        //this.string = string;
        this.stop = false;
    }

    /**
     * draw the animation.
     * @param d  to used the function to draw.
     * @param dt delta time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawImage(0, 0, ColorOrImage.parseImage("background_images/deadpool.jpg"));
        d.setColor(Color.BLUE);
        d.drawText(d.getWidth() / 16, d.getHeight() / 16, "High scores", 32);
        int names = d.getWidth() / 10;
        int scorePlayer = d.getWidth() / 2 + 10;
        int splitLine = d.getHeight() / 7;
        d.setColor(Color.black.darker());
        d.drawText(names, splitLine, "Player Name", 25);
        d.setColor(Color.black);
        d.drawText(scorePlayer, splitLine, "Score", 25);
        d.setColor(Color.white);
        d.drawLine(names, splitLine + 3, d.getWidth() / 2 + 100, splitLine);
        ArrayList<ScoreInfo> scores = new ArrayList<>(scoresTable.getHighScores());
        for (int i = 1; i <= scores.size(); i++) {
            d.setColor(Color.black.darker());
            d.drawText(names, splitLine + i * 30, scores.get(i - 1).getName(), 25);
            d.setColor(Color.CYAN.darker());
            d.drawText(scorePlayer, splitLine + i * 30, scores.get(i - 1).getScore() + "", 25);
        }
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 6, d.getHeight() - 60, "Press space to continue", 32);

    }

    /**
     * shouldStop.
     * The stopping conditions are for example no more balls or blocks.
     *
     * @return true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
