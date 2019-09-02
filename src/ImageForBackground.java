import biuoop.DrawSurface;

import java.awt.Image;

/**
 * background to level in image.
 */
public class ImageForBackground implements Sprite {
    private Image image;

    /**
     * constructor.
     *
     * @param image image.
     */
    public ImageForBackground(Image image) {
        this.image = image;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }

    @Override
    public void timePassed(double dt) {

    }
}
