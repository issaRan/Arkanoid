import biuoop.DrawSurface;

import java.awt.Color;

/**
 * lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * constructor.
     *
     * @param lives lives of the game.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    /**
     * @param dt delta time.
     */
    public void timePassed(double dt) {

    }

    /**
     * draw the text of the screen.
     *
     * @param d to used the relevant function
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(100, 15, "lives: " + Integer.toString(lives.getValue()), 20);
    }

    /**
     * add the draw to the game.
     *
     * @param g the the gamelevel.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
