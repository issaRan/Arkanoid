import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author ISSA
 * 19/5/18
 * draw the background.
 */
public class Background1 implements Sprite {
    // draw the sprite to the screen

    /**
     * draw the background to the screen.
     *
     * @param d to used the relevant function
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 165, 50);
        d.drawCircle(400, 165, 80);
        d.drawCircle(400, 165, 110);
        d.drawLine(400, 0, 400, 140);
        d.drawLine(420, 165, 575, 165);
        d.drawLine(400, 190, 400, 330);
        d.drawLine(220, 165, 375, 165);
    }
    // notify the sprite that time has passed

    /**
     * notify the sprite that time has passed.
     * @param dt delta time
     */
    public void timePassed(double dt) {

    }
}
