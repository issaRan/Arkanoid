import biuoop.DrawSurface;

/**
 * @author ISSA
 * 21/04/18
 * interface of sprite
 */
public interface Sprite {
    // draw the sprite to the screen

    /**
     * draw the sprite to the screen.
     * @param d to used the relevant function
     */
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed

    /**
     * notify the sprite that time has passed.
     * @param dt delta time.
     */
    void timePassed(double dt);
}
