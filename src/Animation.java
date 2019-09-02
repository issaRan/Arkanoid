import biuoop.DrawSurface;
/**
 * @author ISSA
 * 19/5/18
 */
public interface Animation {
    /**
     * draw the animatiom.
     *
     * @param d  used the drawSurface package.
     * @param dt delta time.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * when we stopped the action.
     *
     * @return return boolean.
     */
    boolean shouldStop();
}