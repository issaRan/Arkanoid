import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Pause interface.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     *
     * @param k a keyboardSensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * draw the massage when we press p.
     *
     * @param d used the drawSurface package.
     * @param dt delta time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawImage(0, 0, ColorOrImage.parseImage("background_images/chess.jpg"));
        d.setColor(Color.white);
        d.drawText(10, 250, "May the force be with you", 40);
        d.drawText(200, d.getHeight() / 2, "Paused - Press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * function the stop the action.
     *
     * @return boolean.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
