import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * the winning class.
 */
public class Winning implements Animation {
    private KeyboardSensor keyboardSensor;
    private int score;
    private boolean keep;

    /**
     * constructor.
     *
     * @param score the score.
     */
    public Winning(int score) {
        //this.keyboardSensor = keyboardSensor;
        this.score = score;
        this.keep = false;
    }

    /**
     * draw the text.
     * @param dt delta time.
     * @param d used the drawSurface package.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.gray);
        d.drawText(800 / 4, 600 / 2, "You Win! Your score is " + this.score, 32);
    }

    /**
     * when it's should stop.
     *
     * @return a boolean.
     */
    public boolean shouldStop() {
        return this.keep;
    }
}
