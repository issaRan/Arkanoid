import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ISSA
 * 21/4/18
 * this class collect all the sprite.
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    //private double dt = 60;
    /**
     * created Arrays list of the sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * the function adds a sprite.
     *
     * @param s parameter in type of Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);

    }

    /**
     * remove the sprite.
     * @param s a sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
    // call timePassed() on all sprites.

    /**
     * call timePassed() on all sprites.
     * @param dt delta time.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed(dt);
        }
    }

    // call drawOn(d) on all sprites.

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d to use the relevant function
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);
        }
    }
}
