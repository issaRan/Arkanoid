import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author ISSA
 * 19/5/18
 * this class used to count numbers till the beginning of the game.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int theFrames;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int flag = 0;
    private int rise = 0;
    private int limit;
    private int screenWidth;
    private int screenHeight;
    private boolean newNum;

    /**
     * constructor.
     *
     * @param numOfSeconds seconds.
     * @param countFrom    the height number.
     * @param gameScreen   the screen will played on.
     * @param screenWidth  width of the screen.
     * @param screenHeight height of the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen
            , int screenWidth, int screenHeight) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.theFrames = 60;
        this.gameScreen = gameScreen;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.limit = (screenHeight / 3 + screenWidth / 3) / 3;

    }

    /**
     * used to draw the counting.
     *
     * @param d  used the DrawSurface package.
     * @param dt delta time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int millSecondFrames = this.theFrames / 1000;
        if (newNum) {
            this.countFrom--;
            this.rise = 0;
            newNum = false;
        }
        if (this.rise >= limit) {
            this.newNum = true;
        }

        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Color backgroundColor = new Color(0.000f, 0.420f, 0.000f);
        d.setColor(backgroundColor);
        d.fillRectangle(0, 0, this.screenWidth, this.screenHeight);
        d.setColor(Color.black);
        d.fillOval((int) (screenWidth / 2), (int) (screenHeight * 0.8), 20, 10);
        d.setColor(Color.magenta);
        d.drawOval((int) screenWidth / 2, (int) (screenHeight * 0.8), 20, 10);

        this.gameScreen.drawAllOn(d);

        d.setColor(Color.RED);
        if (this.countFrom <= 0) {
            d.drawText((int) (screenWidth * 0.45), (int) (screenHeight * 0.65), "Go", 50);
            this.rise = this.rise + 3;

        } else {
            d.drawText((int) (screenWidth * 0.485), (int) (screenHeight * 0.65), Integer.toString(countFrom), 50);
            this.rise = this.rise + 3;

        }
        if (flag != 0 && this.newNum) {
            //sleeper.sleepFor(MillSecondFrames);
            System.currentTimeMillis();

        } else {
            flag++;
        }

    }

    /**
     * used to stop the action.
     *
     * @return true to finish the program, and false to make the program activate.
     */
    public boolean shouldStop() {
        if (this.countFrom == -1) {
            return true;
        }
        return false;
    }
}