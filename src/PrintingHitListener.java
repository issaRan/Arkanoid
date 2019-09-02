/**
 * Printing HitListener.
 */
public class PrintingHitListener implements HitListener {
    /**
     * if we got collision it's send a message.
     * @param beingHit the block
     * @param hitter the ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}