import biuoop.DrawSurface;

import java.awt.Color;

/**
 * // draw the sprite to the screen.
 */
public class NameIndicator implements Sprite {
    private String string;

    /**
     * constructor.
     *
     * @param string a string to the level.
     */
    public NameIndicator(String string) {
        this.string = string;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d to used the relevant function
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(480, 15, "level Name: " + this.string, 18);
    }
    // notify the sprite that time has passed

    /**
     * notify the sprite that time has passed.
     * @param dt delta time.
     */
    public void timePassed(double dt) {

    }

    /**
     * add the nameIndicator to the game.
     *
     * @param g an object to the game level.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
