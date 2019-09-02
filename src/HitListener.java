/**
 * @author ISSA
 * 19/5/18
 * hitlistener interface
 */
public interface HitListener {
    /**
     * // This method is called whenever the beingHit object is hit.
     // The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block
     * @param hitter the ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}