import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Lose.
 */
public class Lose implements Animation {
    private KeyboardSensor keyboardSensor;
    private int score;
    private boolean keep;

    /**
     * constructor.
     *
     * @param score the score we got until now.
     */
    public Lose(int score) {
        //this.keyboardSensor = keyboardSensor;
        this.keep = false;
        this.score = score;
    }

    /**
     * draw the massage.
     *
     * @param dt delta time.
     * @param d  used the drawSurface package.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.gray);
        d.drawText(800 / 4, 600 / 2, "Game Over ! your score is " + score, 32);
        //if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
        //this.keep = true;
        //}
    }

    /**
     * when it's should stop.
     *
     * @return boolean.
     */
    public boolean shouldStop() {
        return this.keep;
    }
}
